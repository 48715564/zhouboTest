package com.example.demo;

import java.lang.annotation.*;

/**
 * Created by bozhou on 2017/8/22.
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface MyTestAnn {
    String value() default "";
}
