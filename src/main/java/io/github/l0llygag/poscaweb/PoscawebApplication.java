package io.github.l0llygag.poscaweb;

import io.github.adorableskullmaster.pw4j.PoliticsAndWar;
import io.github.adorableskullmaster.pw4j.PoliticsAndWarBuilder;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class PoscawebApplication {

    public static void main(String[] args) {
        SpringApplication.run(PoscawebApplication.class, args);
    }

    @Bean
    public PoliticsAndWar getPoliticsAndWar() {
        String[] api_keys = System.getenv("API_KEY").split(",");
        return new PoliticsAndWarBuilder().addApiKeys(api_keys).setEnableCache(false).build();
    }
}
