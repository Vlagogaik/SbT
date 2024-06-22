package org.Oleg;

import java.lang.annotation.*;

@Inherited
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface NewAnnoClass {
    int ID() default 0;
    String Desc() default "null";
}
