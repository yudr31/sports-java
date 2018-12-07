package com.spring.boot.sports.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author yuderen
 * @version 2018/8/25 14:08
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.PARAMETER})
public @interface Validation {

    String name() default "";

    boolean nullable() default false;

    int min() default 0;

    int max() default 999999;

    String[] containedIn() default {};

    RegexType regexType() default RegexType.NONE;

    String regexExpression() default "";

}
