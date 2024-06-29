package org.startjava;

import java.lang.annotation.*;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Repeatable(ContainerForNewRepetableAnnotation.class)
public @interface NewRepetableAnnotation {
    int id() default 0;
    String description() default "null";
}
