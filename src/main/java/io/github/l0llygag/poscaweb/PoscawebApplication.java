package io.github.l0llygag.poscaweb;

import io.github.adorableskullmaster.pw4j.PoliticsAndWar;
import io.github.adorableskullmaster.pw4j.PoliticsAndWarBuilder;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class PoscawebApplication {

    public static void main(String[] args) {
        SpringApplication.run(PoscawebApplication.class, args);
    }

    @Bean
    public PoliticsAndWar getPoliticsAndWar() {
        return new PoliticsAndWarBuilder().setApiKey("4216612b725b2b").setEnableCache(false).build();
    }
}
