package com.raylabz.javahttp;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by RayLabz - 2019
 * Visit http://www.RayLabz.com
 *
 * Java-HTTP: A Java utility library that makes HTTP requests easier to work with.
 * Java HTTP allows easy creation and execution of HTTP requests.
 * Repository: https://github.com/RayLabz/Java-HTTP
 * Guide: https://RayLabz.github.io/Java-HTTP
 */

/**
 * Models and performs an HTTP request.
 */
public class BasicHTTPRequest extends HTTPRequest<BasicHTTPResponse> {

    private final HashMap<String, String> params;
    private final RequestMethod requestMethod;
    private final String mimeType;

    /**
     * Constructs an HTTP request.
     * @param url The URL of the request.
     * @param requestMethod The HTTP method to use.
     * @param params Parameters of the request (data).
     * @param requestProperties Properties (headers) such as Content-Type etc.
     * @param connectTimeout The connection timeout limit.
     * @param readTimeout The data read timeout limit.
     * @param mimeType The data type of the requested resource.
     */
    private BasicHTTPRequest(String url, RequestMethod requestMethod, HashMap<String, String> params,
                             HashMap<String, String> requestProperties, int connectTimeout, int readTimeout, String mimeType,
                             OnFailureListener failureListener, OnSuccessListener<BasicHTTPResponse> successListener) {
        super(url, requestProperties, connectTimeout, readTimeout, failureListener, successListener);
        this.requestMethod = requestMethod;
        this.params = params;
        this.mimeType = mimeType;
    }

    /**
     * Returns the parameter set of the request's data.
     * @return a HashMap of String to String.
     */
    public HashMap<String, String> getParams() {
        return params;
    }

    /**
     * Returns the request method.
     * @return RequestMethod.
     */
    public RequestMethod getRequestMethod() {
        return requestMethod;
    }

    /**
     * Returns the mime type.
     * @return mimeType
     */
    public String getMimeType() {
        return mimeType;
    }

    /**
     * Instantiates a new builder.
     * @param url The url.
     * @param requestMethod The request method.
     * @return Returns a new builder.
     */
    public static Builder create(String url, RequestMethod requestMethod) {
        return new Builder(url, requestMethod);
    }

    /**
     * A builder pattern used to instantiate {@link BasicHTTPRequest}.
     */
    public static class Builder {

        private final String url;
        private final HashMap<String, String> params = new HashMap<>();
        private final HashMap<String, String> requestProperties = new HashMap<>();
        private final RequestMethod requestMethod;
        private int connectTimeout = DEFAULT_CONNECT_TIMEOUT;
        private int readTimeout = DEFAULT_READ_TIMEOUT;
        private String mimeType = ContentType.CONTENT_TYPE_TEXT.getText();
        private OnFailureListener failureListener = error -> {
            System.err.println("Unhandled Error!");
            System.err.println("Message: " + error.getMessage());
            System.err.println("Handle this error by using onFailure().");
        };
        private OnSuccessListener<BasicHTTPResponse> successListener = new OnSuccessListener<BasicHTTPResponse>() {
            @Override
            public void onSuccess(BasicHTTPResponse response) {
                //Print the data by default, this must be overridden by the caller:
                System.out.println("Success!");
                System.out.println("URL: " + url);
                System.out.println("Status: " + response.getStatus());
                System.out.println("Content: " + response.getContent());
            }
        };

        /**
         * Instantiates a builder object.
         * @param url The URL of the request.
         * @param requestMethod The request method.
         */
        public Builder(String url, RequestMethod requestMethod) {
            this.url = url;
            this.requestMethod = requestMethod;
        }

        /**
         * Adds a String parameter to the request's parameter set.
         * @param paramName The parameter's name.
         * @param paramValue The parameter's value.
         * @return Returns a builder object.
         */
        public Builder addParam(String paramName, String paramValue) {
            params.put(paramName, paramValue);
            return this;
        }

        /**
         * Adds an integer parameter to the request's parameter set.
         * @param paramName The parameter's name.
         * @param paramValue The parameter's value.
         * @return Returns a builder object.
         */
        public Builder addParam(String paramName, int paramValue) {
            params.put(paramName, String.valueOf(paramValue));
            return this;
        }

        /**
         * Adds a double parameter to the request's parameter set.
         * @param paramName The parameter's name.
         * @param paramValue The parameter's value.
         * @return Returns a builder object.
         */
        public Builder addParam(String paramName, double paramValue) {
            params.put(paramName, String.valueOf(paramValue));
            return this;
        }

        /**
         * Adds a float parameter to the request's parameter set.
         * @param paramName The parameter's name.
         * @param paramValue The parameter's value.
         * @return Returns a builder object.
         */
        public Builder addParam(String paramName, float paramValue) {
            params.put(paramName, String.valueOf(paramValue));
            return this;
        }

        /**
         * Adds a boolean parameter to the request's parameter set.
         * @param paramName The parameter's name.
         * @param paramValue The parameter's value.
         * @return Returns a builder object.
         */
        public Builder addParam(String paramName, boolean paramValue) {
            params.put(paramName, String.valueOf(paramValue));
            return this;
        }

        /**
         * Adds a character parameter to the request's parameter set.
         * @param paramName The parameter's name.
         * @param paramValue The parameter's value.
         * @return Returns a builder object.
         */
        public Builder addParam(String paramName, char paramValue) {
            params.put(paramName, String.valueOf(paramValue));
            return this;
        }

        /**
         * Adds a short parameter to the request's parameter set.
         * @param paramName The parameter's name.
         * @param paramValue The parameter's value.
         * @return Returns a builder object.
         */
        public Builder addParam(String paramName, short paramValue) {
            params.put(paramName, String.valueOf(paramValue));
            return this;
        }

        /**
         * Adds a long parameter to the request's parameter set.
         * @param paramName The parameter's name.
         * @param paramValue The parameter's value.
         * @return Returns a builder object.
         */
        public Builder addParam(String paramName, long paramValue) {
            params.put(paramName, String.valueOf(paramValue));
            return this;
        }

        /**
         * Sets a single request property.
         * @param propertyKey The name of the header.
         * @param propertyValue The value of the header.
         * @return Returns a builder object.
         */
        public Builder setRequestProperty(String propertyKey, String propertyValue) {
            requestProperties.put(propertyKey, propertyValue);
            return this;
        }

        /**
         * Sets the timeout for connection.
         * @param connectTimeout The connection timeout limit.
         * @return Returns a builder object
         */
        public Builder setConnectTimeout(int connectTimeout) {
            this.connectTimeout = connectTimeout;
            return this;
        }

        /**
         * Sets the timout for the response read.
         * @param readTimeout The response read timeout.
         * @return Returns a builder object
         */
        public Builder setReadTimeout(int readTimeout) {
            this.readTimeout = readTimeout;
            return this;
        }

        /**
         * Sets the mime type for the request.
         * @param contentType The Mime Type of the request (visit: https://developer.mozilla.org/en-US/docs/Web/HTTP/Basics_of_HTTP/MIME_types/Complete_list_of_MIME_types)
         * @return Returns a builder object
         */
        public Builder setContentType(ContentType contentType) {
            this.mimeType = contentType.getText();
            requestProperties.put(ContentType.CONTENT_TYPE_KEY, contentType.getText());
            return this;
        }

        /**
         * Sets a custom Mime Type.
         * @param customMimeType The Mime Type of the request as a String(visit: https://developer.mozilla.org/en-US/docs/Web/HTTP/Basics_of_HTTP/MIME_types/Complete_list_of_MIME_types)
         * @return Returns a builder object
         */
        public Builder setContentType(String customMimeType) {
            this.mimeType = customMimeType;
            requestProperties.put(ContentType.CONTENT_TYPE_KEY, customMimeType);
            return this;
        }

        /**
         * Sets the failure listener.
         * @param onFailureListener The failure listener.
         * @return Returns a builder object.
         */
        public BasicHTTPRequest.Builder onFailure(OnFailureListener onFailureListener) {
            this.failureListener = onFailureListener;
            return this;
        }

        /**
         * Sets the success listener.
         * @param onSuccessListener The success listener.
         * @return Returns a builder object.
         */
        public BasicHTTPRequest.Builder onSuccess(OnSuccessListener<BasicHTTPResponse> onSuccessListener) {
            this.successListener = onSuccessListener;
            return this;
        }

        /**
         * Builds an HTTPRequest object.
         * @return {@link BasicHTTPRequest}
         */
        public BasicHTTPRequest build() {
            return new BasicHTTPRequest(url, requestMethod, params, requestProperties, connectTimeout, readTimeout, mimeType, failureListener, successListener);
        }

    }

    /**
     * Makes a call with the specified configuration to the specified URL and returns the response.
     */
    public void sendAndWait() {

        try {
            if (requestMethod == RequestMethod.GET) {
                this.url = url + "?" + QueryStringMaker.makeQueryString(this.params);
            }
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }

        try {
            URL obj = new URL(url);
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();

            con.setConnectTimeout(connectTimeout);
            con.setReadTimeout(readTimeout);
            con.setDoOutput(true);
            con.setRequestMethod(requestMethod.toString());
            for (Map.Entry<String,String> entry : requestProperties.entrySet()) {
                con.setRequestProperty(entry.getKey(), entry.getValue());
            }

            final long sendTime;
            if (requestMethod == RequestMethod.POST) {
                DataOutputStream out = new DataOutputStream(con.getOutputStream());
                out.writeBytes(QueryStringMaker.makeQueryString(params));
                out.flush();
                sendTime = System.currentTimeMillis();
                out.close();
            }
            else {
                sendTime = System.currentTimeMillis();
            }

            int statusCode = con.getResponseCode();

            Reader streamReader;
            if (statusCode > 299) {
                streamReader = new InputStreamReader(con.getErrorStream());
            } else {
                streamReader = new InputStreamReader(con.getInputStream());
            }

            BufferedReader in = new BufferedReader(streamReader);
            String inputLine;
            StringBuilder content = new StringBuilder();
            while ((inputLine = in.readLine()) != null) {
                content.append(inputLine);
            }
            in.close();

            long latency = (System.currentTimeMillis() - sendTime);
            successListener.onSuccess(new BasicHTTPResponse(statusCode, content.toString(), con.getHeaderFields(), latency));

        } catch (IOException e) {
            failureListener.onFailure(e);
        }
    }

    @Override
    public void send() {
        Thread thread = new Thread(this::sendAndWait, "http-request-runnable");
        thread.start();
    }

}
