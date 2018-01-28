package com.langel.snake.annotation;

import com.langel.snake.http.RequestMethod;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface SnakeAction {
    RequestMethod[] method() default {};

    String path() default "";
}
