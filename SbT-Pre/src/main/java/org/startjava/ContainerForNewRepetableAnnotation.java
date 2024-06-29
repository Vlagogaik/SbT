package org.startjava;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface ContainerForNewRepetableAnnotation {
    NewRepetableAnnotation[] value();
}
