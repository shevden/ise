package com.ds.ise.validator.user;

import com.ds.ise.validator.Condition;
import org.apache.log4j.Logger;

/**
 * Validates last name of the {@code User}.
 *
 * @author Denys Shevchenko
 * @version 1.0
 */
public class UserLastNameCondition implements Condition {

    public static final int MAX_LAST_NAME_LENGTH = 40;

    public static final String NO_LAST_NAME_ATTR_ERR_MES = "This request does not contain last name attribute.";
    public static final String LAST_NAME_ATTR_EMPTY_ERR_MES = "The last name value must not be empty.";
    public static final String LAST_NAME_ATTR_LENGTH_ERR_MES = "The length of the last name must not be greater than " + MAX_LAST_NAME_LENGTH + " symbols.";

    private static final Logger LOG = Logger.getLogger(UserLastNameCondition.class);

    @Override
    public String validate(String[] attributes) {
        if (attributes.length < 1) {
            IllegalArgumentException e = new IllegalArgumentException();
            LOG.error(NO_LAST_NAME_ATTR_ERR_MES, e);
            throw e;
        }
        String lastName = attributes[0];
        if (lastName.isEmpty()) {
            return LAST_NAME_ATTR_EMPTY_ERR_MES;
        }
        if (lastName.length() > MAX_LAST_NAME_LENGTH) {
            return LAST_NAME_ATTR_LENGTH_ERR_MES;
        }

        return null;
    }
}
