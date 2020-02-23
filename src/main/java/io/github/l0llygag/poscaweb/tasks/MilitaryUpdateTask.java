package io.github.l0llygag.poscaweb.tasks;

import io.github.adorableskullmaster.pw4j.PoliticsAndWar;
import io.github.adorableskullmaster.pw4j.domains.subdomains.NationMilitaryContainer;
import io.github.adorableskullmaster.pw4j.domains.subdomains.SNationContainer;
import io.github.l0llygag.poscaweb.Utilities;
import io.github.l0llygag.poscaweb.database.LogRepository;
import io.github.l0llygag.poscaweb.database.NationRepository;
import io.github.l0llygag.poscaweb.database.models.Log;
import io.github.l0llygag.poscaweb.database.models.Nation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.time.Instant;
import java.util.List;
import java.util.Optional;

@Component
public class MilitaryUpdateTask {

    private final PoliticsAndWar politicsAndWar;
    private final NationRepository nationRepository;
    private final LogRepository logRepository;
    private final Logger logger = LoggerFactory.getLogger(MilitaryUpdateTask.class);

    public MilitaryUpdateTask(PoliticsAndWar politicsAndWar, NationRepository nationRepository, LogRepository logRepository) {
        this.politicsAndWar = politicsAndWar;
        this.nationRepository = nationRepository;
        this.logRepository = logRepository;
    }

    @Scheduled(fixedRate = 900000L, initialDelay = 300000L)
    private void update() throws IOException {
        logger.info("MilitaryUpdateTask update started");
        List<SNationContainer> nations = politicsAndWar.getNations(true).getNationsContainer();
        List<NationMilitaryContainer> allMilitaries = politicsAndWar.getAllMilitaries().getNationMilitaries();
        logger.info("MilitaryUpdateTask data fetched");
        for (SNationContainer sNationContainer : nations) {
            NationMilitaryContainer nationMilitaryContainer = allMilitaries.stream()
                    .filter(container -> container.getNationId().equals(sNationContainer.getNationid()))
                    .findFirst()
                    .orElse(new NationMilitaryContainer());

            Optional<Nation> nation = nationRepository.findById(nationMilitaryContainer.getNationId());

            Nation toSave;
            toSave = nation.map(value -> Utilities.getNation(value, sNationContainer, nationMilitaryContainer))
                    .orElseGet(() -> Utilities.getNation(sNationContainer, nationMilitaryContainer));

            nationRepository.save(toSave);
        }
        String s = nations.size() + " nations updated.";
        logRepository.save(new Log("SER-UPDATE-MUT", s, Instant.now()));
        logger.info("MilitaryUpdateTask update finished");
    }

}
