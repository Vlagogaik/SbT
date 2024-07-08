package org.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;
import java.util.Collection;

@Aspect
@Component
public class NotEmptyAspect {

    @Before("@annotation(NotEmpty)")
    public void checkArguments(JoinPoint joinPoint) {
        Object[] args = joinPoint.getArgs();
        for (Object arg : args) {
            if (arg == null) {
                throw new IllegalArgumentException("Method argument is null");
            }
            if (arg instanceof String && ((String) arg).isEmpty()) {
                throw new IllegalArgumentException("Method argument is an empty string");
            }
            if (arg instanceof Collection && ((Collection<?>) arg).isEmpty()) {
                throw new IllegalArgumentException("Method argument is an empty collection");
            }
        }
    }
}