package com.ds.ise.validator.user;

import com.ds.ise.validator.Condition;
import org.apache.log4j.Logger;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Validates email of the {@code User}.
 *
 * @author Denys Shevchenko
 * @version 1.0
 */
public class UserEmailCondition implements Condition {

    public static final int MAX_EMAIL_LENGTH = 40;
    public static final String EMAIL_REGEX_PATTERN = "^([\\w\\-\\.]+@([\\w\\-]+\\.)+[\\w\\-]{2,4})?$";

    public static final String NO_EMAIL_ATTR_ERR_MES = "This request does not contain email attribute.";
    public static final String EMAIL_ATTR_EMPTY_ERR_MES = "The email value must not be empty.";
    public static final String EMAIL_ATTR_LENGTH_ERR_MES = "The length of the email must not be greater than " + MAX_EMAIL_LENGTH + " symbols.";
    public static final String EMAIL_ATTR_PATTERN_ERR_MES = "Email field must be filled out according to the pattern 'email@mail.com'.";

    private static final Logger LOG = Logger.getLogger(UserEmailCondition.class);

    @Override
    public String validate(String[] attributes) {
        if (attributes.length < 1) {
            IllegalArgumentException e = new IllegalArgumentException();
            LOG.error(NO_EMAIL_ATTR_ERR_MES, e);
            throw e;
        }
        String email = attributes[0];
        if (email.isEmpty()) {
            return EMAIL_ATTR_EMPTY_ERR_MES;
        }
        if (email.length() > MAX_EMAIL_LENGTH) {
            return EMAIL_ATTR_LENGTH_ERR_MES;
        }
        Pattern pattern = Pattern.compile(EMAIL_REGEX_PATTERN);
        Matcher matcher = pattern.matcher(email);
        if(!matcher.matches()){
            return EMAIL_ATTR_PATTERN_ERR_MES;
        }

        return null;
    }
}