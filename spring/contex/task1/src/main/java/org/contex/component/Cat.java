package org.contex.component;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Value;

@Getter
@Setter
@Data
@Component
public class Cat {
    @Value(value = "Boris")
    private String name;

}