package org.context.task1.component;

import lombok.Getter;
import lombok.Setter;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Value;

@Getter
@Setter
@Component
@Scope("prototype")
public class Parrot {
    @Value(value = "Kesha")
    private String name;

}
