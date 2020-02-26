package io.github.l0llygag.poscaweb.tasks;

import io.github.adorableskullmaster.pw4j.PoliticsAndWar;
import io.github.adorableskullmaster.pw4j.domains.WarAttacks;
import io.github.adorableskullmaster.pw4j.domains.Wars;
import io.github.adorableskullmaster.pw4j.domains.subdomains.SWarContainer;
import io.github.adorableskullmaster.pw4j.enums.ResourceType;
import io.github.l0llygag.poscaweb.database.LogRepository;
import io.github.l0llygag.poscaweb.database.NationRepository;
import io.github.l0llygag.poscaweb.database.models.Log;
import io.github.l0llygag.poscaweb.database.models.Nation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.time.Instant;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/*
  Database will store only top 5000 active nations above 500 score
  Every 15 min:
  Nations API : 1 call
  Nation-Military API : 1 call
  Wars API : 1 call
  war-attacks API : can vary. generally 10 - 100 calls

  For beige days left:
  Simple  way : Nation API : 5 - 60? calls every time you use aa-nations
  Harder way : Nations API v2 : 1 call every 15 min.

  L0 = original loot (10% of stockpile)
  L = actual loot
  WT = war type modifier
  WPW = winner war policy
  WPL = loser war policy

  L0 = (L / (1 + WPW + WPL)) / WT

  WPW    WPL
  att    -0.2    0
  tur    0    0.2
  mon    0    -0.4
  pir    0.4    0
  gua    0    0.2

  policy
  0 -> attrition
  1 -> turtle
  2 -> moneybags
  3 -> pirate
  4 -> guardian
*/

@Component
public class LootUpdateTask {

    private final NationRepository nationRepository;
    private final LogRepository logRepository;
    private final PoliticsAndWar politicsAndWar;
    private final HashMap<Integer, HashMap<String, Double>> changedLootValues = new HashMap<>();
    private final Queue<Nation> saveQueue = new ArrayDeque<>();
    private final String[] rss = {"Coal", "Oil", "Uranium", "Iron", "Bauxite", "Lead", "Gasoline", "Munitions", "Steel", "Aluminum", "Food"};
    private final HashMap<String, Double> rssPrice = new HashMap<>();
    private final Logger logger = LoggerFactory.getLogger(LootUpdateTask.class);

    @Autowired
    public LootUpdateTask(PoliticsAndWar politicsAndWar, NationRepository nationRepository, LogRepository logRepository) {
        this.politicsAndWar = politicsAndWar;
        this.nationRepository = nationRepository;
        this.logRepository = logRepository;
    }

    @Scheduled(fixedRate = 7200000L, initialDelay = 900000L)
    public void update() throws IOException {

        Optional<Log> last = logRepository.findFirstByTypeOrderByInstantDesc("SER-UPDATE-LUT");

        logger.info("LootUpdateTracker update started");

        int amount = 5000;
        if (last.isPresent() && Instant.now().toEpochMilli() - last.get().getInstant().toEpochMilli() < 43200000L)
            amount = 3000;

        logger.info("LootUpdateTracker amount being fetched: {} ", amount);

        initializePrices();

        Wars recentWars = politicsAndWar.getWarsByAmount(amount);
        recentWars.getWars().sort(Comparator.comparing(SWarContainer::getDate));

        int i = 0;
        AtomicInteger c = new AtomicInteger();
        for (SWarContainer war : recentWars.getWars()) {

            nationRepository.findById(war.getAttackerID());

            if (war.getStatus().contains("Defender Victory")) {
                logger.info("Defender Victory - " + i);
                WarAttacks warAttacksByWarId = politicsAndWar.getWarAttacksByWarId(war.getWarID());

                warAttacksByWarId.getWarAttacksContainers()
                        .stream()
                        .filter(warAttacksContainer -> warAttacksContainer.getAttackType().equals("victory"))
                        .findFirst()
                        .ifPresent(warAttacksContainer -> {
                            try {
                                changedLootValues.put(
                                        war.getAttackerID(),
                                        parseNoteResources(
                                                fixNote(warAttacksContainer.getNote()),
                                                getWarPolicy(war.getDefenderID()),
                                                getWarPolicy(war.getAttackerID()),
                                                getWarType(war.getWarType())
                                        ));
                                addToSaveQueue(war.getAttackerID());
                                c.getAndIncrement();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        });

            } else if (war.getStatus().contains("Attacker Victory")) {
                logger.info("Attacker Victory - " + i);
                WarAttacks warAttacksByWarId = politicsAndWar.getWarAttacksByWarId(war.getWarID());

                warAttacksByWarId.getWarAttacksContainers()
                        .stream()
                        .filter(warAttacksContainer -> warAttacksContainer.getAttackType().equals("victory"))
                        .findFirst()
                        .ifPresent(warAttacksContainer -> {
                            try {
                                changedLootValues.put(
                                        war.getDefenderID(),
                                        parseNoteResources(
                                                fixNote(warAttacksContainer.getNote()),
                                                getWarPolicy(war.getAttackerID()),
                                                getWarPolicy(war.getAttackerID()),
                                                getWarType(war.getWarType())
                                        ));
                                addToSaveQueue(war.getDefenderID());
                                c.getAndIncrement();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        });
            }
            i++;
        }
        executeSave();
        logger.info("MilitaryUpdateTask update finished");
        logRepository.save(new Log("SER-UPDATE-LUT", saveQueue.size() + " wars updated.", Instant.now()));
    }

    private void executeSave() {
        nationRepository.saveAll(saveQueue);
    }

    private void addToSaveQueue(int id) {
        nationRepository.findById(id).ifPresent(nation -> {
            nation.setCoal(changedLootValues.get(id).get(rss[0]));
            nation.setOil(changedLootValues.get(id).get(rss[1]));
            nation.setUranium(changedLootValues.get(id).get(rss[2]));
            nation.setIron(changedLootValues.get(id).get(rss[3]));
            nation.setBauxite(changedLootValues.get(id).get(rss[4]));
            nation.setLead(changedLootValues.get(id).get(rss[5]));
            nation.setGasoline(changedLootValues.get(id).get(rss[6]));
            nation.setMunitions(changedLootValues.get(id).get(rss[7]));
            nation.setSteel(changedLootValues.get(id).get(rss[8]));
            nation.setAluminum(changedLootValues.get(id).get(rss[9]));
            nation.setFood(changedLootValues.get(id).get(rss[10]));
            nation.setMoney(changedLootValues.get(id).get("Money"));
            nation.setLootValue(getWorth(changedLootValues.get(id)));
            saveQueue.add(nation);
        });
    }

    private Double getWorth(HashMap<String, Double> nationLoot) {
        double sum = 0.0d;
        for (String rs : rss) {
            if (rs.equalsIgnoreCase("Money")) sum += nationLoot.get(rs) * 0.14;
            else sum += rssPrice.get(rs) * nationLoot.get(rs) * 0.14;
        }
        return sum;
    }

    private void initializePrices() {
        try {
            rssPrice.put("Munitions", Double.parseDouble(politicsAndWar.getTradeprice(ResourceType.MUNITIONS).getLowestbuy().getPrice()));
            rssPrice.put("Aluminum", Double.parseDouble(politicsAndWar.getTradeprice(ResourceType.ALUMINUM).getLowestbuy().getPrice()));
            rssPrice.put("Steel", Double.parseDouble(politicsAndWar.getTradeprice(ResourceType.STEEL).getLowestbuy().getPrice()));
            rssPrice.put("Gasoline", Double.parseDouble(politicsAndWar.getTradeprice(ResourceType.GASONLINE).getLowestbuy().getPrice()));
            rssPrice.put("Food", Double.parseDouble(politicsAndWar.getTradeprice(ResourceType.FOOD).getLowestbuy().getPrice()));
            rssPrice.put("Iron", Double.parseDouble(politicsAndWar.getTradeprice(ResourceType.IRON).getLowestbuy().getPrice()));
            rssPrice.put("Bauxite", Double.parseDouble(politicsAndWar.getTradeprice(ResourceType.BAUXITE).getLowestbuy().getPrice()));
            rssPrice.put("Uranium", Double.parseDouble(politicsAndWar.getTradeprice(ResourceType.URANIUM).getLowestbuy().getPrice()));
            rssPrice.put("Lead", Double.parseDouble(politicsAndWar.getTradeprice(ResourceType.LEAD).getLowestbuy().getPrice()));
            rssPrice.put("Oil", Double.parseDouble(politicsAndWar.getTradeprice(ResourceType.OIL).getLowestbuy().getPrice()));
            rssPrice.put("Coal", Double.parseDouble(politicsAndWar.getTradeprice(ResourceType.COAL).getLowestbuy().getPrice()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private int getWarPolicy(int id) throws IOException {
        String warPolicy;

        Optional<Nation> byId = nationRepository.findById(id);

        if (byId.isPresent()) warPolicy = byId.get().getWarPolicy();
        else warPolicy = politicsAndWar.getNation(id).getWarPolicy();

        if (warPolicy.equalsIgnoreCase("attrition"))
            return 0;
        else if (warPolicy.equalsIgnoreCase("turtle"))
            return 1;
        else if (warPolicy.equalsIgnoreCase("moneybags"))
            return 2;
        else if (warPolicy.equalsIgnoreCase("pirate"))
            return 3;
        else if (warPolicy.equalsIgnoreCase("guardian"))
            return 4;
        else
            return 5;
    }

    private double getWarType(String warType) {
        if (warType.equalsIgnoreCase("raid")) return 1;
        else if (warType.equalsIgnoreCase("ord")) return 0.5;
        else return 0.25;
    }


    private Double getEstimateWarChest(double modifiedLoot, int winnerPolicy, int loserPolicy, double warType) {
        double[] winnerModifiers = {-0.2, 0, 0, 0.4, 0, 0}, loserModifiers = {0, 0.2, -0.4, 0, 0.2, 0};
        return modifiedLoot / (1 + winnerModifiers[winnerPolicy] + loserModifiers[loserPolicy]) / warType / 0.1;
    }


    private String fixNote(String note) {
        return Pattern.compile("\\s{2,}").matcher(note).replaceAll(" ");
    }

    private HashMap<String, Double> parseNoteResources(String note, int winnerPolicy, int loserPolicy, double warType) {
        HashMap<String, Double> rssMap = new HashMap<>();


        for (String rs : rss) {
            Double valueForRss = getValueForRss(rs, note);
            rssMap.put(rs, getEstimateWarChest(valueForRss, winnerPolicy, loserPolicy, warType));
        }

        rssMap.put("Money", getValueForMoney(note));

        return rssMap;
    }

    private Double getValueForRss(String rs, String note) {
        Pattern pattern = Pattern.compile(String.format("((:?^|\\s)(?=.)((?:0|(?:[1-9](?:\\d*|\\d{0,2}(?:,\\d{3})*)))?(?:\\.\\d*[1-9])?)(?!\\S)(?= %s))", rs));
        Matcher matcher = pattern.matcher(note);

        if (matcher.find())
            return Double.parseDouble(matcher.group(1).replaceAll(",", "").trim());
        return 0.0d;
    }

    private Double getValueForMoney(String note) {
        Pattern pattern = Pattern.compile("(?:\\$)([1-9]\\d{0,2}((?:,)\\d{3})*)");
        Matcher matcher = pattern.matcher(note);
        if (matcher.find()) return Double.parseDouble(matcher.group(1).replaceAll(",", "").trim());
        return 0.0d;
    }
}
