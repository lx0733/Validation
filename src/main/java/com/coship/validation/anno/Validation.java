package com.coship.validation.anno;

import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;


@Documented
@Retention(RUNTIME)
@Target({METHOD})
@Inherited
public @interface Validation {
	//校验类,与xml配置对应
	String id();
    //校验组(同一dto的不同应用场景的校验)
    String group()  default "";
}
