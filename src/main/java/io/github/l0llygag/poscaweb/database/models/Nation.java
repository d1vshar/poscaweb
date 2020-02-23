package io.github.l0llygag.poscaweb.database.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "nations")
public class Nation {
    @Id
    private Integer nationId;
    private String nation;
    private String leader;
    private String continent;
    private String warPolicy;
    private String color;
    private String beigeLeft;
    private String alliance;
    private Integer allianceId;
    private Integer alliancePosition;
    private Integer cities;
    private Integer offensiveWars;
    private Integer defensiveWars;
    private Double score;
    private Integer rank;
    private Integer vacMode;
    private Double infrastructure;
    private String minutesSinceActive;
    private Integer soldiers;
    private Integer soldiersPercentage;
    private Integer tanks;
    private Integer tanksPercentage;
    private Integer aircraft;
    private Integer aircraftPercentage;
    private Integer ships;
    private Integer shipsPercentage;
    private Integer missiles;
    private Integer nukes;
    private Double money;
    private Double coal;
    private Double oil;
    private Double uranium;
    private Double lead;
    private Double iron;
    private Double bauxite;
    private Double gasoline;
    private Double munitions;
    private Double steel;
    private Double aluminum;
    private Double food;
    private Double lootValue;

    public Double getLootValue() {
        return lootValue;
    }

    public void setLootValue(Double lootValue) {
        this.lootValue = lootValue;
    }

    public Integer getNationId() {
        return nationId;
    }

    public void setNationId(Integer nationId) {
        this.nationId = nationId;
    }

    public String getNation() {
        return nation;
    }

    public void setNation(String nation) {
        this.nation = nation;
    }

    public String getLeader() {
        return leader;
    }

    public void setLeader(String leader) {
        this.leader = leader;
    }

    public String getContinent() {
        return continent;
    }

    public void setContinent(String continent) {
        this.continent = continent;
    }

    public String getWarPolicy() {
        return warPolicy;
    }

    public void setWarPolicy(String warPolicy) {
        this.warPolicy = warPolicy;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getAlliance() {
        return alliance;
    }

    public void setAlliance(String alliance) {
        this.alliance = alliance;
    }

    public Integer getAllianceId() {
        return allianceId;
    }

    public void setAllianceId(Integer allianceId) {
        this.allianceId = allianceId;
    }

    public Integer getAlliancePosition() {
        return alliancePosition;
    }

    public void setAlliancePosition(Integer alliancePosition) {
        this.alliancePosition = alliancePosition;
    }

    public Integer getCities() {
        return cities;
    }

    public void setCities(Integer cities) {
        this.cities = cities;
    }

    public Integer getOffensiveWars() {
        return offensiveWars;
    }

    public void setOffensiveWars(Integer offensiveWars) {
        this.offensiveWars = offensiveWars;
    }

    public Integer getDefensiveWars() {
        return defensiveWars;
    }

    public void setDefensiveWars(Integer defensiveWars) {
        this.defensiveWars = defensiveWars;
    }

    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
    }

    public Integer getRank() {
        return rank;
    }

    public void setRank(Integer rank) {
        this.rank = rank;
    }

    public Integer getVacMode() {
        return vacMode;
    }

    public void setVacMode(Integer vacMode) {
        this.vacMode = vacMode;
    }

    public String getMinutesSinceActive() {
        return minutesSinceActive;
    }

    public void setMinutesSinceActive(String minutesSinceActive) {
        this.minutesSinceActive = minutesSinceActive;
    }

    public Integer getSoldiers() {
        return soldiers;
    }

    public void setSoldiers(Integer soldiers) {
        this.soldiers = soldiers;
    }

    public Integer getSoldiersPercentage() {
        return soldiersPercentage;
    }

    public void setSoldiersPercentage(Integer soldiersPercentage) {
        this.soldiersPercentage = soldiersPercentage;
    }

    public Integer getTanks() {
        return tanks;
    }

    public void setTanks(Integer tanks) {
        this.tanks = tanks;
    }

    public Integer getTanksPercentage() {
        return tanksPercentage;
    }

    public void setTanksPercentage(Integer tanksPercentage) {
        this.tanksPercentage = tanksPercentage;
    }

    public Integer getAircraft() {
        return aircraft;
    }

    public void setAircraft(Integer aircraft) {
        this.aircraft = aircraft;
    }

    public Integer getAircraftPercentage() {
        return aircraftPercentage;
    }

    public void setAircraftPercentage(Integer aircraftPercentage) {
        this.aircraftPercentage = aircraftPercentage;
    }

    public Integer getShips() {
        return ships;
    }

    public void setShips(Integer ships) {
        this.ships = ships;
    }

    public Integer getShipsPercentage() {
        return shipsPercentage;
    }

    public void setShipsPercentage(Integer shipsPercentage) {
        this.shipsPercentage = shipsPercentage;
    }

    public Integer getMissiles() {
        return missiles;
    }

    public void setMissiles(Integer missiles) {
        this.missiles = missiles;
    }

    public Integer getNukes() {
        return nukes;
    }

    public void setNukes(Integer nukes) {
        this.nukes = nukes;
    }

    public Double getMoney() {
        return money;
    }

    public void setMoney(Double money) {
        this.money = money;
    }

    public Double getCoal() {
        return coal;
    }

    public void setCoal(Double coal) {
        this.coal = coal;
    }

    public Double getOil() {
        return oil;
    }

    public void setOil(Double oil) {
        this.oil = oil;
    }

    public Double getUranium() {
        return uranium;
    }

    public void setUranium(Double uranium) {
        this.uranium = uranium;
    }

    public Double getLead() {
        return lead;
    }

    public void setLead(Double lead) {
        this.lead = lead;
    }

    public Double getIron() {
        return iron;
    }

    public void setIron(Double iron) {
        this.iron = iron;
    }

    public Double getBauxite() {
        return bauxite;
    }

    public void setBauxite(Double bauxite) {
        this.bauxite = bauxite;
    }

    public Double getGasoline() {
        return gasoline;
    }

    public void setGasoline(Double gasoline) {
        this.gasoline = gasoline;
    }

    public Double getMunitions() {
        return munitions;
    }

    public void setMunitions(Double munitions) {
        this.munitions = munitions;
    }

    public Double getSteel() {
        return steel;
    }

    public void setSteel(Double steel) {
        this.steel = steel;
    }

    public Double getAluminum() {
        return aluminum;
    }

    public void setAluminum(Double aluminum) {
        this.aluminum = aluminum;
    }

    public Double getFood() {
        return food;
    }

    public void setFood(Double food) {
        this.food = food;
    }

    public Double getInfrastructure() {
        return infrastructure;
    }

    public void setInfrastructure(Double infrastructure) {
        this.infrastructure = infrastructure;
    }

    public String getBeigeLeft() {
        return beigeLeft;
    }

    public void setBeigeLeft(String beigeLeft) {
        this.beigeLeft = beigeLeft;
    }
}
