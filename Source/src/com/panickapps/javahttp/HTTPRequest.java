package com.panickapps.javahttp;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

//TODO Improve based on: https://www.baeldung.com/java-http-request
public class HTTPRequest {

    private static final int DEFAULT_CONNECT_TIMEOUT = 30000;
    private static final int DEFAULT_READ_TIMEOUT = 30000;

    private String url;
    private final HashMap<String, String> params;
    private final HashMap<String, String> requestProperties;
    private final RequestMethod requestMethod;
    private int connectTimeout;
    private int readTimeout;
    private MimeType mimeType;

    public HTTPRequest(String url, RequestMethod requestMethod, HashMap<String, String> params,
                       HashMap<String, String> requestProperties, int connectTimeout, int readTimeout, MimeType mimeType) {
        this.url = url;
        this.requestMethod = requestMethod;
        this.params = params;
        this.requestProperties = requestProperties;
        this.connectTimeout = connectTimeout;
        this.readTimeout = readTimeout;
        this.mimeType = mimeType;
    }

    public String getUrl() {
        return url;
    }

    public HashMap<String, String> getParams() {
        return params;
    }

    public RequestMethod getRequestMethod() {
        return requestMethod;
    }

    public int getConnectTimeout() {
        return connectTimeout;
    }

    public int getReadTimeout() {
        return readTimeout;
    }

    public MimeType getMimeType() {
        return mimeType;
    }

    public static class Builder {

        private String url;
        private HashMap<String, String> params = new HashMap<>();
        private HashMap<String, String> requestProperties = new HashMap<>();
        private RequestMethod requestMethod;
        private int connectTimeout = DEFAULT_CONNECT_TIMEOUT;
        private int readTimeout = DEFAULT_READ_TIMEOUT;
        private MimeType mimeType = MimeType.CONTENT_TYPE_TEXT;

        public Builder(String url, RequestMethod requestMethod) {
            requestProperties.put(MimeType.CONTENT_TYPE_KEY, mimeType.getText());
            this.url = url;
            this.requestMethod = requestMethod;
        }

        public Builder setParams(HashMap<String, String> params) {
            this.params = params;
            return this;
        }

        public Builder addParam(String paramName, String paramValue) {
            params.put(paramName, paramValue);
            return this;
        }

        public Builder setRequestProperties(HashMap<String, String> requestProperties) {
            this.requestProperties = requestProperties;
            return this;
        }

        public Builder addRequestProperty(String propertyKey, String propertyValue) {
            requestProperties.put(propertyKey, propertyValue);
            return this;
        }

        public Builder setConnectTimeout(int connectTimeout) {
            this.connectTimeout = connectTimeout;
            return this;
        }

        public Builder setReadTimeout(int readTimeout) {
            this.readTimeout = readTimeout;
            return this;
        }

        public Builder setMimeType(MimeType mimeType) {
            this.mimeType = mimeType;
            requestProperties.put(MimeType.CONTENT_TYPE_KEY, mimeType.getText());
            return this;
        }

        public HTTPRequest build() {
            return new HTTPRequest(url, requestMethod, params, requestProperties, connectTimeout, readTimeout, mimeType);
        }

    }

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

            con.setRequestMethod(requestMethod.toString());

            for (Map.Entry<String,String> entry : requestProperties.entrySet()) {
                con.setRequestProperty(entry.getKey(), entry.getValue());
            }

            con.setConnectTimeout(connectTimeout);
            con.setReadTimeout(readTimeout);
            con.setDoOutput(true);

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
