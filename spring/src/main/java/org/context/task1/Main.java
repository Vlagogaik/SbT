package org.context.task1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
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