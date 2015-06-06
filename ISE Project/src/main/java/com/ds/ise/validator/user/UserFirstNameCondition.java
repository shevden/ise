package com.ds.ise.validator.user;

import com.ds.ise.validator.Condition;
import org.apache.log4j.Logger;

/**
 * Validates first name of the {@code User}.
 *
 * @author Denys Shevchenko
 * @version 1.0
 */
public class UserFirstNameCondition implements Condition {

    public static final int MAX_FIRST_NAME_LENGTH = 40;

    public static final String NO_FIRST_NAME_ATTR_ERR_MES = "This request does not contain first name attribute.";
    public static final String FIRST_NAME_ATTR_EMPTY_ERR_MES = "The first name value must not be empty.";
    public static final String FIRST_NAME_ATTR_LENGTH_ERR_MES = "The length of the first name must not be greater than " + MAX_FIRST_NAME_LENGTH + " symbols.";

    private static final Logger LOG = Logger.getLogger(UserFirstNameCondition.class);


    @Override
    public String validate(String[] attributes) {
        if (attributes.length < 1) {
            IllegalArgumentException e = new IllegalArgumentException();
            LOG.error(NO_FIRST_NAME_ATTR_ERR_MES, e);
            throw e;
        }
        String firstName = attributes[0];
        if (firstName.isEmpty()) {
            return FIRST_NAME_ATTR_EMPTY_ERR_MES;
        }
        if (firstName.length() > MAX_FIRST_NAME_LENGTH) {
            return FIRST_NAME_ATTR_LENGTH_ERR_MES;
        }

        return null;
    }
}
