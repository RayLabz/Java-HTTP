package com.panickapps.javahttp;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by PaNickApps - 2019
 * Visit http://www.panickapps.com
 *
 * Java-HTTP: A Java utility library that makes HTTP requests easier to work with.
 * Java HTTP allows easy creation and execution of HTTP requests.
 * Repository: https://github.com/panickapps/Java-HTTP
 * Guide: https://panickapps.github.io/Java-HTTP
 */

/**
 * Models and performs an HTTP request.
 */
public class HTTPRequest {

    private static final int DEFAULT_CONNECT_TIMEOUT = 30000;
    private static final int DEFAULT_READ_TIMEOUT = 30000;

    private String url;
    private final HashMap<String, String> params;
    private final HashMap<String, String> requestProperties;
    private final RequestMethod requestMethod;
    private int connectTimeout;
    private int readTimeout;
    private String mimeType;

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
    private HTTPRequest(String url, RequestMethod requestMethod, HashMap<String, String> params,
                       HashMap<String, String> requestProperties, int connectTimeout, int readTimeout, String mimeType) {
        this.url = url;
        this.requestMethod = requestMethod;
        this.params = params;
        this.requestProperties = requestProperties;
        this.connectTimeout = connectTimeout;
        this.readTimeout = readTimeout;
        this.mimeType = mimeType;
    }

    /**
     * Returns the URL of the request.
     * @return the URL.
     */
    public String getUrl() {
        return url;
    }

    /**
     * Returns the parameter set of the request's data.
     * @return a HashMap of String->String.
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
     * Returns the mime type.
     * @return mimeType
     */
    public String getMimeType() {
        return mimeType;
    }

    /**
     * A builder pattern used to instantiate {@link HTTPRequest}.
     */
    public static class Builder {

        private String url;
        private HashMap<String, String> params = new HashMap<>();
        private HashMap<String, String> requestProperties = new HashMap<>();
        private RequestMethod requestMethod;
        private int connectTimeout = DEFAULT_CONNECT_TIMEOUT;
        private int readTimeout = DEFAULT_READ_TIMEOUT;
        private String mimeType = MimeType.CONTENT_TYPE_TEXT.getText();

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
         * @param mimeType The Mime Type of the request (visit: https://developer.mozilla.org/en-US/docs/Web/HTTP/Basics_of_HTTP/MIME_types/Complete_list_of_MIME_types)
         * @return Returns a builder object
         */
        public Builder setMimeType(MimeType mimeType) {
            this.mimeType = mimeType.getText();
            requestProperties.put(MimeType.CONTENT_TYPE_KEY, mimeType.getText());
            return this;
        }

        /**
         * Sets a custom Mime Type.
         * @param customMimeType The Mime Type of the request as a String(visit: https://developer.mozilla.org/en-US/docs/Web/HTTP/Basics_of_HTTP/MIME_types/Complete_list_of_MIME_types)
         * @return Returns a builder object
         */
        public Builder setCustomMimeType(String customMimeType) {
            this.mimeType = customMimeType;
            requestProperties.put(MimeType.CONTENT_TYPE_KEY, customMimeType);
            return this;
        }

        /**
         * Builds an HTTPRequest object.
         * @return {@link HTTPRequest}
         */
        public HTTPRequest build() {
            return new HTTPRequest(url, requestMethod, params, requestProperties, connectTimeout, readTimeout, mimeType);
        }

    }

    /**
     * Makes a call with the specified configuration to the specified URL and returns the response.
     * @return Returns a {@link HTTPResponse} object, containing the response data.
     */
    public HTTPResponse call() {

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

            if (requestMethod == RequestMethod.POST) {
                DataOutputStream out = new DataOutputStream(con.getOutputStream());
                out.writeBytes(QueryStringMaker.makeQueryString(params));
                out.flush();
                out.close();
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

            return new HTTPResponse(statusCode, content.toString(), con.getHeaderFields());

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
