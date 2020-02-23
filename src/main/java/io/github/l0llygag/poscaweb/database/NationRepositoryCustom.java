package io.github.l0llygag.poscaweb.database;

import io.github.l0llygag.poscaweb.database.models.Nation;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NationRepositoryCustom {
    List<Nation> findByScoreAndAllianceId(Double low, Double high, Integer allianceId, Boolean includeBeige,
                                          Boolean includeVM, Boolean slotted);
}
