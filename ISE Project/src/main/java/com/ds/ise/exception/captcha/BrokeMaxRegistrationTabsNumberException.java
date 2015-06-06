package com.ds.ise.exception.captcha;

/**
 * This exception acts like {@code RuntimeException} and must be
 * thrown in case when too many registration tabs are opened by
 * the same user.
 *
 * @author Denys Shevchenko
 * @version 1.0
 */
public class BrokeMaxRegistrationTabsNumberException extends RuntimeException {

    /**
     * Construct exception object with specified message.
     *
     * @param message that will be attached.
     */
    public BrokeMaxRegistrationTabsNumberException(String message) {
        super(message);
    }
}
