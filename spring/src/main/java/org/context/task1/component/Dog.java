package org.context.task1.component;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Value;

@Getter
@Setter
@Component
public class Dog {
    @Value(value = "Buddy")
    private String name;
}
