package com.ds.ise.exception.captcha;

/**
 * This exception acts like {@code RuntimeException} and must be
 * thrown in case of registration process timeout.
 *
 * @author Denys Shevchenko
 * @version 1.0
 */
public class ExpiredRegistrationException extends RuntimeException {

    /**
     * Construct exception object with specified message.
     *
     * @param message that will be attached.
     */
    public ExpiredRegistrationException(String message) {
        super(message);
    }
}
