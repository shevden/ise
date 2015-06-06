package com.ds.ise.exception.captcha;

/**
 * This exception wrapper acts like {@code RuntimeException} and
 * receives {@code InterruptedException} as a cause.
 *
 * @author Denys Shevchenko
 * @version 1.0
 */
public class InterruptedWakeUpException extends RuntimeException {

    /**
     * Construct exception object with specified cause.
     *
     * @param cause that will be set up.
     */
    public InterruptedWakeUpException(InterruptedException cause) {
        super(cause);
    }
}
