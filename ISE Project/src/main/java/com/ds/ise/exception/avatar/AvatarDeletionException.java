package com.ds.ise.exception.avatar;

/**
 * This exception wrapper acts like {@code RuntimeException} and
 * receives {@code message} to describe the cause of trouble.
 *
 * @author Denys Shevchenko
 * @version 1.0
 */
public class AvatarDeletionException extends RuntimeException {

    /**
     * Construct exception object with specified cause.
     *
     * @param message that will be set up.
     */
    public AvatarDeletionException(String message) {
        super(message);
    }
}
