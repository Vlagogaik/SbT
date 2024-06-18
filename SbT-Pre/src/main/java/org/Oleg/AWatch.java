package org.Oleg;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Repeatable(AWatchs.class)
public @interface AWatch {
    int hour() default 0;
    String description() default "Oleg";
}

