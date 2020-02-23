package io.github.l0llygag.poscaweb.database;

import io.github.l0llygag.poscaweb.database.models.Nation;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.util.List;

public class NationRepositoryImpl implements NationRepositoryCustom {

    private final MongoTemplate mongoTemplate;

    public NationRepositoryImpl(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public List<Nation> findByScoreAndAllianceId(Double low, Double high, Integer allianceId, Boolean includeBeige, Boolean includeVM, Boolean slotted) {
        Query query = new Query();

        Criteria scoreCriteria = Criteria.where("score").gte(low).lte(high);
        query.addCriteria(scoreCriteria);
        Criteria allianceIdCriteria, beigeCriteria, vmCriteria, slotCriteria;

        if (allianceId >= 0) {
            allianceIdCriteria = Criteria.where("allianceId").is(allianceId);
            query.addCriteria(allianceIdCriteria);
        }
        if (!includeBeige) {
            beigeCriteria = Criteria.where("color").ne("beige");
            query.addCriteria(beigeCriteria);
        }
        if (!includeVM) {
            vmCriteria = Criteria.where("vacMode").is(0);
            query.addCriteria(vmCriteria);
        }
        if (!slotted) {
            slotCriteria = Criteria.where("defensiveWars").lt(3);
            query.addCriteria(slotCriteria);
        }

        query.addCriteria(Criteria.where("lootValue").exists(true));

        query.with(Sort.by(Sort.Direction.DESC, "lootValue"));

        return mongoTemplate.find(query, Nation.class);
    }
}
