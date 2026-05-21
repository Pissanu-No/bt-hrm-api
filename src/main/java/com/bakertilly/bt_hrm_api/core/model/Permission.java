package com.bakertilly.bt_hrm_api.core.model;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD) // Can be used only at Method.
@Retention(RetentionPolicy.RUNTIME) // Make it accessible at runtime.
public @interface Permission {

    String menu(); // Specify the menu name for which you want to authenticate.
    int permission(); // Specify the value of bitmask permission to check.
}
