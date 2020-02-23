package io.github.l0llygag.poscaweb.database;

import io.github.l0llygag.poscaweb.database.models.Nation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NationRepository extends MongoRepository<Nation, Integer> {

    List<Nation> findByAllianceIdOrderByScoreDesc(Integer allianceId);

    // Normal
    @SuppressWarnings("SpringDataRepositoryMethodParametersInspection")
    List<Nation> findByScoreBetweenAndAllianceIdRegexAndVacModeRegexAndColorRegexAndDefensiveWarsRegexOrderByLootValue(Double score, Double score2, String allianceIdRegex, String vacModeRegex, String colorRegex, String defensiveWarsRegex);


}
