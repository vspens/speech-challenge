package com.project.speech.validation;

import java.util.regex.Pattern;

/**
 * @author Vince Spencer Historia
 */
public class Validator {
    /**
     * Email validator
     * @param emailAddress
     * @return boolean
     */
    public static boolean email(String emailAddress) {
        return Pattern.compile("^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$")
                .matcher(emailAddress)
                .matches();
    }
}
