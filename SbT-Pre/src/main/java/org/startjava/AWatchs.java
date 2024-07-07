package org.startjava;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface AWatchs{
    AWatch[] value();
}
