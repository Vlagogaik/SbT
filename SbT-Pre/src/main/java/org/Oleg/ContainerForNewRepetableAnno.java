package org.Oleg;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface ContainerForNewRepetableAnno {
    NewRepetableAnno[] value();
}
