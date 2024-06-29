package org.springs.contexts;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Getter
@Setter
@Component
public class Dog {
    private String name;

    public Dog() {
        this.name = "Buddy";
    }
}
