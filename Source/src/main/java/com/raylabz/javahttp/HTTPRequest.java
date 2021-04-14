package com.raylabz.javahttp;

import java.util.HashMap;

/**
 * Created by RayLabz - 2021.
 * Models an abstract HTTPRequest.
 */
public abstract class HTTPRequest<ResponseType extends HTTPResponse<?>> {

    protected static final int DEFAULT_CONNECT_TIMEOUT = 30000;
    protected static final int DEFAULT_READ_TIMEOUT = 30000;

    protected String url;
    protected final HashMap<String, String> requestProperties;
    protected final int connectTimeout;
    protected final int readTimeout;
    protected final OnFailureListener failureListener;
    protected final OnSuccessListener<ResponseType> successListener;

    /**
     * Constructs an abstract HTTPRequest.
     * @param url The URL of the request.
     * @param requestProperties A map of request properties.
     * @param connectTimeout The connection timeout.
     * @param readTimeout The read timeout.
     * @param failureListener The failure listener of this request.
     * @param successListener The success listener of this request.
     */
    public HTTPRequest(String url, HashMap<String, String> requestProperties, int connectTimeout, int readTimeout, OnFailureListener failureListener, OnSuccessListener<ResponseType> successListener) {
        this.url = url;
        this.requestProperties = requestProperties;
        this.connectTimeout = connectTimeout;
        this.readTimeout = readTimeout;
        this.failureListener = failureListener;
        this.successListener = successListener;
    }

    /**
     * Returns the URL of the request.
     * @return the URL.
     */
    public String getUrl() {
        return url;
    }

    /**
     * Returns the connection timeout limit.
     * @return connectTimeout
     */
    public int getConnectTimeout() {
        return connectTimeout;
    }

    /**
     * Returns the read timeout limit.
     * @return readTimeout
     */
    public int getReadTimeout() {
        return readTimeout;
    }

    /**
     * Retrieves the request properties of this request.
     * @return Returns a map of string to string.
     */
    public HashMap<String, String> getRequestProperties() {
        return requestProperties;
    }

    /**
     * Sends the request to the URL and blocks execution until a response is received.
     */
    public abstract void sendAndWait();

    /**
     * Sends the request to the URL asynchronously.
     */
    public abstract void send();

}
