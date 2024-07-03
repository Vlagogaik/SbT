package org.context.task1.component;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
public class Human {
    private org.context.task1.component.Parrot parrot1;
    private org.context.task1.component.Parrot parrot2;
    private org.context.task1.component.Cat cat;
    private org.context.task1.component.Dog dog;

    @Autowired
    public Human(Parrot parrot1, Parrot parrot2, Cat cat, Dog dog) {
        this.parrot1 = parrot1;
        this.parrot2 = parrot2;
        this.cat = cat;
        this.dog = dog;
    }
}
