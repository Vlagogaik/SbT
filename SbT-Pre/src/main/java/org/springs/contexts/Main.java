package org.springs.contexts;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main(String[] args) {
//        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
//        Human person = context.getBean(Human.class);
        Human person = new Human();
//        System.out.println("Попугай 1: " + person.getParrot1().getName());
//        System.out.println("Попугай 2: " + person.getParrot2().getName());
        System.out.println("Кот: " + person.getCat().getName());
//        System.out.println("Собака: " + person.getDog().getName());
    }
}
