package org.springs.contexts;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {
    @Bean
    public Parrot parrot1() {
        return new Parrot("Kesha");
    }

    @Bean
    public Parrot parrot2() {
        return new Parrot("Polly");
    }

    @Bean
    public Cat cat() {
        return new Cat("Whiskers");
    }

    @Bean
    public Dog dog() {
        return new Dog("Buddy");
    }

    @Bean
    public Human person() {
        return new Human(parrot1(), parrot2(), cat(), dog());
    }
}
