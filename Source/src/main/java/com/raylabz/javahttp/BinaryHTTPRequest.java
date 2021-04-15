package com.raylabz.javahttp;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by RayLabz - 2021
 * Visit http://www.RayLabz.com
 *
 * Java-HTTP: A Java utility library that makes HTTP requests easier to work with.
 * Java HTTP allows easy creation and execution of HTTP requests.
 * Repository: https://github.com/RayLabz/Java-HTTP
 * Guide: https://RayLabz.github.io/Java-HTTP
 */

/**
 * Models and performs an HTTP request.
 * Added in v2.
 */
public class BinaryHTTPRequest extends HTTPRequest<BinaryHTTPResponse> {

    private static final int DEFAULT_CONNECT_TIMEOUT = 30000;
    private static final int DEFAULT_READ_TIMEOUT = 30000;

    private final byte[] data;

    /**
     * Constructs an HTTP request.
     * @param url The URL of the request.
     * @param data The data passed in the request.
     * @param requestProperties Properties (headers) such as Content-Type etc.
     * @param connectTimeout The connection timeout limit.
     * @param readTimeout The data read timeout limit.
     */
    private BinaryHTTPRequest(String url, byte[] data, HashMap<String, String> requestProperties, int connectTimeout, int readTimeout, OnFailureListener failureListener, OnSuccessListener<BinaryHTTPResponse> successListener) {
        super(url, requestProperties, connectTimeout, readTimeout, failureListener, successListener);
        this.data = data;
    }

    /**
     * Returns the data for this request.
     * @return Returns a byte[] of data.
     */
    public byte[] getData() {
        return data;
    }

    /**
     * Instantiates a new builder.
     * @param url The url.
     * @return Returns a new builder.
     */
    public static BinaryHTTPRequest.Builder create(String url) {
        return new BinaryHTTPRequest.Builder(url);
    }

    /**
     * A builder pattern used to instantiate {@link BinaryHTTPRequest}.
     */
    public static class Builder {

        private final String url;
        private final HashMap<String, String> requestProperties = new HashMap<>();
        private int connectTimeout = DEFAULT_CONNECT_TIMEOUT;
        private int readTimeout = DEFAULT_READ_TIMEOUT;
        private final ByteArrayOutputStream bos = new ByteArrayOutputStream();
        private final DataOutputStream dataStream = new DataOutputStream(bos);

        private OnFailureListener failureListener = error -> {
            System.err.println("Error!");
            System.err.println("Message: " + error.getMessage());
            System.err.println("Handle this error by using onFailure().");
        };
        private OnSuccessListener<BinaryHTTPResponse> successListener = new OnSuccessListener<BinaryHTTPResponse>() {
            @Override
            public void onSuccess(BinaryHTTPResponse response) {
                //Print the data by default, this must be overridden by the caller:
                System.out.println("Success!");
                System.out.println("URL: " + url);
                System.out.println("Status: " + response.getStatus());
                System.out.println("Content length (bytes): " + response.getContent().length);
            }
        };

        /**
         * Instantiates a builder object.
         * @param url The URL of the request.
         */
        public Builder(String url) {
            this.url = url;
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
         * Puts an integer into the request data stream.
         * @param data An integer.
         * @return Returns a builder object.
         */
        public Builder putInt(int data) {
            try {
                dataStream.writeInt(data);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            return this;
        }

        /**
         * Puts a short into the request data stream.
         * @param data A short.
         * @return Returns a builder object.
         */
        public Builder putShort(short data) {
            try {
                dataStream.writeShort(data);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            return this;
        }

        /**
         * Puts a long into the request data stream.
         * @param data A long.
         * @return Returns a builder object.
         */
        public Builder putLong(long data) {
            try {
                dataStream.writeLong(data);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            return this;
        }

        /**
         * Puts a boolean into the request data stream.
         * @param data A boolean.
         * @return Returns a builder object.
         */
        public Builder putBoolean(boolean data) {
            try {
                dataStream.writeBoolean(data);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            return this;
        }

        /**
         * Puts a character into the request data stream.
         * @param data A character.
         * @return Returns a builder object.
         */
        public Builder putChar(char data) {
            try {
                dataStream.writeChar(data);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            return this;
        }

        /**
         * Puts a float into the request data stream.
         * @param data A float.
         * @return Returns a builder object.
         */
        public Builder putFloat(float data) {
            try {
                dataStream.writeFloat(data);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            return this;
        }

        /**
         * Puts a double into the request data stream.
         * @param data A double.
         * @return Returns a builder object.
         */
        public Builder putDouble(double data) {
            try {
                dataStream.writeDouble(data);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            return this;
        }

        /**
         * Puts a UTF-8 based string into the request data stream.
         * @param data A string.
         * @return Returns a builder object.
         */
        public Builder putString(String data) {
            try {
                dataStream.writeUTF(data);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            return this;
        }

        /**
         * Puts a byte into the request data stream.
         * @param data A byte.
         * @return Returns a builder object.
         */
        public Builder putByte(byte data) {
            try {
                dataStream.writeByte(data);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            return this;
        }

        /**
         * Puts a byte array into the request data stream.
         * @param data A byte array.
         * @return Returns a builder object.
         */
        public Builder putBytes(byte[] data) {
            try {
                dataStream.write(data);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            return this;
        }

        /**
         * Sets the failure listener.
         * @param onFailureListener The failure listener.
         * @return Returns a builder object.
         */
        public Builder onFailure(OnFailureListener onFailureListener) {
            this.failureListener = onFailureListener;
            return this;
        }

        /**
         * Sets the success listener.
         * @param onSuccessListener The success listener.
         * @return Returns a builder object.
         */
        public Builder onSuccess(OnSuccessListener<BinaryHTTPResponse> onSuccessListener) {
            this.successListener = onSuccessListener;
            return this;
        }

        /**
         * Builds an HTTPRequest object.
         * @return {@link BinaryHTTPRequest}
         */
        public BinaryHTTPRequest build() {
            try {
                dataStream.close();
                bos.close();
                return new BinaryHTTPRequest(url, bos.toByteArray(), requestProperties, connectTimeout, readTimeout, failureListener, successListener);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

    }

    /**
     * Makes a call with the specified configuration to the specified URL and returns the response.
     */
    public void sendAndWait() {

        try {
            URL obj = new URL(url);
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();
            con.setConnectTimeout(connectTimeout);
            con.setReadTimeout(readTimeout);
            con.setDoOutput(true);
            con.setRequestMethod(RequestMethod.POST.toString());

            //Write the data
            con.getOutputStream().write(data);
            con.getOutputStream().flush();
            final long sendTime = System.currentTimeMillis();
            con.getOutputStream().close();

            int statusCode = con.getResponseCode();

            //Read the actual data:
            byte[] buffer = new byte[4096];
            int offset = 0;
            int bytes;
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            DataOutputStream outputStream = new DataOutputStream(bos);
            while ((bytes = con.getInputStream().read(buffer, offset, buffer.length)) > 0) {
                outputStream.write(buffer, 0, bytes);
            }
            outputStream.close();
            bos.close();
            final byte[] data = bos.toByteArray();

            long latency = (System.currentTimeMillis() - sendTime);
            successListener.onSuccess(new BinaryHTTPResponse(statusCode, data, con.getHeaderFields(), latency));

        } catch (Exception e) {
            failureListener.onFailure(e);
        }
    }

    @Override
    public void send() {
        Thread thread = new Thread(this::sendAndWait, "http-request-runnable");
        thread.start();
    }

}
