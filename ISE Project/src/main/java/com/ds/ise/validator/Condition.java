package com.ds.ise.validator;

/**
 * This interface provides common contract for all
 * validation conditions.
 *
 * @author Denys Shvchenko
 * @version 1.0
 */
public interface Condition {

    /**
     * Validates specified attributes. If they are valid, returns
     * {@code null}. If they are not, returns error message that will
     * be displayed to the user.
     *
     * @param attributes that will be validated.
     * @return {@code null} if valid, error message if not.
     */
    String validate(String[] attributes);
}