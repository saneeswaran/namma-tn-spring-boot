package com.app.tn.global;

import org.springframework.stereotype.Component;

@Component
public class GlobalErrorCheck {

    static boolean hasText(String value) {
        return value != null && value.trim().length() > 0;
    }

    static boolean hasElements(Object[] array) {
        return array != null && array.length > 0;
    }
}
