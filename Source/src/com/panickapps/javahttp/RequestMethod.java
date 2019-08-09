package com.panickapps.javahttp;

/**
 * Models the possible HTTP request methods.
 */
public enum RequestMethod {

    GET,
    POST,
    HEAD,
    OPTIONS,
    PUT,
    TRACE,
    DELETE,
    PATCH,
    COPY,
    LINK,
    UNLINK,
    PURGE,
    LOCK,
    UNLOCK,
    PROPFIND,
    VIEW

    ;

    /**
     * Returns the string representation of the HTTP request method.
     * @return String
     */
    @Override
    public String toString() {
        return super.toString();
    }

}
