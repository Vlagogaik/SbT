package org.context.task1;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Getter
@Setter
@Component
public class Cat {
    private String name;

    public Cat() {
        this.name = "Whiskers";
    }
}