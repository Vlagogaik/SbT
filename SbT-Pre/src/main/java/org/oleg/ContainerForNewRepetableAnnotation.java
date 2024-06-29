package org.oleg;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface ContainerForNewRepetableAnnotation {
    NewRepetableAnnotation[] value();
}
