package com.raylabz.javahttp;

/**
 * Allows the graceful handling of failures when using the call() method.
 */
public interface OnFailureListener {

    /**
     * Executed when an error occurs while calling the call() method.
     * @param error The error.
     */
    void onFailure(Throwable error);

}
