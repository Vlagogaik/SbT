package org.context.task1.bean;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class AppConfing {
    @Bean
    @Scope("prototype")
    public Parrot parrot1() {
        return new Parrot("Kesha");
    }

    @Bean
    @Scope("prototype")
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

