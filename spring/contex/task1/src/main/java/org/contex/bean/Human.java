package org.contex.bean;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;

@NoArgsConstructor
@Getter
@Setter
public class Human {
    private Parrot parrot1;
    private Parrot parrot2;
    private Cat cat;
    private Dog dog;

    @Autowired
    public Human(Parrot parrot1, Parrot parrot2, Cat cat, Dog dog) {
        this.parrot1 = parrot1;
        this.parrot2 = parrot2;
        this.cat = cat;
        this.dog = dog;
    }
}
