package io.github.l0llygag.poscaweb.database.models;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;

@Document(collection = "logs")
public class Log {
    @Id
    private
    ObjectId objectId;
    private String type;
    private String details;
    private Instant instant;

    public Log() {
    }

    public Log(ObjectId objectId, String type, String details, Instant instant) {
        this.objectId = objectId;
        this.type = type;
        this.details = details;
        this.instant = instant;
    }

    public Log(String type, String details, Instant instant) {
        this.type = type;
        this.details = details;
        this.instant = instant;
    }

    public ObjectId getObjectId() {
        return objectId;
    }

    public void setObjectId(ObjectId objectId) {
        this.objectId = objectId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public Instant getInstant() {
        return instant;
    }

    public void setInstant(Instant instant) {
        this.instant = instant;
    }
}
