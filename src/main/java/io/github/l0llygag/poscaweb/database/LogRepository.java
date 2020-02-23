package io.github.l0llygag.poscaweb.database;

import io.github.l0llygag.poscaweb.database.models.Log;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LogRepository extends MongoRepository<Log, String> {
    Optional<Log> findFirstByTypeOrderByInstantDesc(String type);
}
