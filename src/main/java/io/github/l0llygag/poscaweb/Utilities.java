package io.github.l0llygag.poscaweb;

import io.github.adorableskullmaster.pw4j.domains.subdomains.NationMilitaryContainer;
import io.github.adorableskullmaster.pw4j.domains.subdomains.SNationContainer;
import io.github.l0llygag.poscaweb.database.models.Nation;

public class Utilities {

    public static Nation getNation(SNationContainer sNationContainer, NationMilitaryContainer nationMilitaryContainer) {
        Nation nation = new Nation();

        return getNation(nation, sNationContainer, nationMilitaryContainer);
    }

    public static Nation getNation(Nation nation, SNationContainer sNationContainer, NationMilitaryContainer nationMilitaryContainer) {
        nation.setNationId(sNationContainer.getNationid());
        nation.setNation(sNationContainer.getNation());
        nation.setLeader(sNationContainer.getLeader());
        nation.setContinent(sNationContainer.getContinent());
        nation.setWarPolicy(sNationContainer.getWarPolicy());
        nation.setColor(sNationContainer.getColor());
        nation.setAlliance(sNationContainer.getAlliance());
        nation.setAllianceId(sNationContainer.getAllianceid());
        nation.setAlliancePosition(sNationContainer.getAllianceposition());
        nation.setCities(sNationContainer.getCities());
        nation.setOffensiveWars(sNationContainer.getOffensivewars());
        nation.setDefensiveWars(sNationContainer.getDefensivewars());
        nation.setScore(sNationContainer.getScore());
        nation.setRank(sNationContainer.getRank());
        nation.setVacMode(sNationContainer.getVacmode());
        nation.setInfrastructure(sNationContainer.getInfrastructure());
        nation.setMinutesSinceActive(sNationContainer.getMinutessinceactive() / 60 + "h " + sNationContainer.getMinutessinceactive() % 60 + "m");
        nation.setSoldiers(nationMilitaryContainer.getSoldiers());
        nation.setSoldiersPercentage(nationMilitaryContainer.getSoldiers() * 100 / (sNationContainer.getCities() * 15000));
        nation.setTanks(nationMilitaryContainer.getTanks());
        nation.setTanksPercentage(nationMilitaryContainer.getTanks() * 100 / (sNationContainer.getCities() * 1250));
        nation.setAircraft(nationMilitaryContainer.getAircraft());
        nation.setAircraftPercentage(nationMilitaryContainer.getAircraft() * 100 / (sNationContainer.getCities() * 90));
        nation.setShips(nationMilitaryContainer.getShips());
        nation.setShipsPercentage(nationMilitaryContainer.getShips() * 100 / (sNationContainer.getCities() * 15));
        nation.setMissiles(nationMilitaryContainer.getMissiles());
        nation.setNukes(nationMilitaryContainer.getNukes());

        return nation;
    }
}
