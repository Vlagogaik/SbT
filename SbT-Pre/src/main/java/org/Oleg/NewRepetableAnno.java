package org.Oleg;


import java.lang.annotation.*;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Repeatable(ContainerForNewRepetableAnno.class)
public @interface NewRepetableAnno {
    int ID() default 0;
    String Desc() default "null";
}
