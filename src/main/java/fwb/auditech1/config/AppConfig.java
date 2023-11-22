package fwb.auditech1.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Random;
import fwb.auditech1.model.Palavras;

@Configuration
public class AppConfig {

    @Bean
    public Random random() {
        return new Random();
    }

    @Bean
    public Palavras palavras(){
        return new Palavras();
    }
}
