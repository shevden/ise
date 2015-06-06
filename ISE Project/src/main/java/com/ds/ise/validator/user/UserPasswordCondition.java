package com.ds.ise.validator.user;

import com.ds.ise.validator.Condition;
import org.apache.log4j.Logger;

/**
 * Validates password of the {@code User}.
 *
 * @author Denys Shevchenko
 * @version 1.0
 */
public class UserPasswordCondition implements Condition {

    public static final int MAX_PASSWORD_LENGTH = 40;

    public static final String NO_PASSWORDS_ATTR_ERR_MES = "This request does not contain 'password' and 'retyped password' attributes.";
    public static final String PASSWORD_ATTR_EMPTY_ERR_MES = "The password value must not be empty.";
    public static final String PASSWORD_ATTR_LENGTH_ERR_MES = "The length of the password must not be greater than " + MAX_PASSWORD_LENGTH + " symbols.";
    public static final String PASSWORDS_ATTR_MISMATCH_ERR_MES = "The first entered password does not match the second one.";

    private static final Logger LOG = Logger.getLogger(UserPasswordCondition.class);

    @Override
    public String validate(String[] attributes) {
        if (attributes.length < 2) {
            IllegalArgumentException e = new IllegalArgumentException();
            LOG.error(NO_PASSWORDS_ATTR_ERR_MES, e);
            throw e;
        }
        String password = attributes[0];
        String passwordRetyped = attributes[1];
        if (password.isEmpty()) {
            return PASSWORD_ATTR_EMPTY_ERR_MES;
        }
        if (password.length() > MAX_PASSWORD_LENGTH) {
            return PASSWORD_ATTR_LENGTH_ERR_MES;
        }
        if (!password.equals(passwordRetyped)) {
            return PASSWORDS_ATTR_MISMATCH_ERR_MES;
        }

        return null;
    }
}
