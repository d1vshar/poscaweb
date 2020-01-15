package io.github.l0llygag.poscaweb;

import io.github.adorableskullmaster.pw4j.domains.subdomains.NationMilitaryContainer;
import io.github.adorableskullmaster.pw4j.domains.subdomains.SNationContainer;

public class AllianceNation {
    private Integer nationId;
    private String nation;
    private String leader;
    private String continent;
    private String warPolicy;
    private String color;
    private String alliance;
    private Integer allianceId;
    private Integer alliancePosition;
    private Integer cities;
    private Integer oWars;
    private Integer dWars;
    private Double score;
    private Integer rank;
    private String vacMode;
    private Integer minutesSinceActive;
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
    private String oRange;
    private String dRange;

    AllianceNation(SNationContainer sNationContainer, NationMilitaryContainer nationMilitaryContainer) {
        this.nationId = sNationContainer.getNationId();
        this.nation = sNationContainer.getNation();
        this.leader = sNationContainer.getLeader();
        this.continent = sNationContainer.getContinent();
        this.warPolicy = sNationContainer.getWarPolicy();
        this.color = sNationContainer.getColor();
        this.alliance = sNationContainer.getAlliance();
        this.allianceId = sNationContainer.getAllianceid();
        this.alliancePosition = sNationContainer.getAllianceposition();
        this.cities = sNationContainer.getCities();
        this.oWars = sNationContainer.getOffensivewars();
        this.dWars = sNationContainer.getDefensivewars();
        this.score = sNationContainer.getScore();
        this.rank = sNationContainer.getRank();
        this.vacMode = sNationContainer.getVacmode();
        this.minutesSinceActive = sNationContainer.getMinutessinceactive();
        this.soldiers = nationMilitaryContainer.getSoldiers();
        this.soldiersPercentage = nationMilitaryContainer.getSoldiers() * 100 / (cities * 15000);
        this.tanks = nationMilitaryContainer.getTanks();
        this.tanksPercentage = nationMilitaryContainer.getTanks() * 100 / (cities * 1250);
        this.aircraft = nationMilitaryContainer.getAircraft();
        this.aircraftPercentage = nationMilitaryContainer.getAircraft() * 100 / (cities * 90);
        this.ships = nationMilitaryContainer.getShips();
        this.shipsPercentage = nationMilitaryContainer.getShips() * 100 / (cities * 15);
        this.missiles = nationMilitaryContainer.getMissiles();
        this.nukes = nationMilitaryContainer.getNukes();
        this.oRange = String.format("%.1f - %.1f", score * 0.75, score * 1.75);
        this.dRange = String.format("%.1f - %.1f", score * 0.5714, score * 1.3333);
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

    public Integer getoWars() {
        return oWars;
    }

    public void setoWars(Integer oWars) {
        this.oWars = oWars;
    }

    public Integer getdWars() {
        return dWars;
    }

    public void setdWars(Integer dWars) {
        this.dWars = dWars;
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

    public String getVacMode() {
        return vacMode;
    }

    public void setVacMode(String vacMode) {
        this.vacMode = vacMode;
    }

    public Integer getMinutesSinceActive() {
        return minutesSinceActive;
    }

    public void setMinutesSinceActive(Integer minutesSinceActive) {
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

    public String getoRange() {
        return oRange;
    }

    public void setoRange(String oRange) {
        this.oRange = oRange;
    }

    public String getdRange() {
        return dRange;
    }

    public void setdRange(String dRange) {
        this.dRange = dRange;
    }
}
