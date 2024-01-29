package com.example.rest.rest.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = OrderFilterValidValidator.class)
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface OrderFilterValid {

    String message () default "Pogination fields must be specified";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

}
