package com.beiyou.annotation;


import java.lang.annotation.*;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface DbType {
    String value() default "";
    int type() default 0 ;
}
