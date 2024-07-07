package org.startjava;

import java.lang.annotation.*;

@Inherited
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface NewAnnotationClass {
    int id() default 0;
    String description() default "null";
}
