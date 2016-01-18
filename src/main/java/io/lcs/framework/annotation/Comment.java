package io.lcs.framework.annotation;

import java.lang.annotation.*;

/**
 * 评论/注释
 * Created by lcs on 10/20/15.
 */
@Target({ElementType.METHOD, ElementType.TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Comment {
	String value() default "";
}
