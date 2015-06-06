package com.ds.ise.exception.login;

/**
 * This exception acts like {@code RuntimeException} and must be
 * thrown in case when login process cannot be processed due to
 * the incorrect credentials.
 *
 * @author Denys Shevchenko
 * @version 1.0
 */
public class LoginException extends RuntimeException {

    /**
     * Construct exception object with specified message.
     *
     * @param message that will be attached.
     */
    public LoginException(String message) {
        super(message);
    }
}
