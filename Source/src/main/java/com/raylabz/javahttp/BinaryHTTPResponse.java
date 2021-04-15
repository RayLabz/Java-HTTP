package com.raylabz.javahttp;

import com.sun.xml.internal.messaging.saaj.util.ByteInputStream;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.util.List;
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

public class BinaryHTTPResponse extends HTTPResponse<byte[]> {

    private final DataInputStream inputStream;

    /**
     * Creates an HTTPResponse object.
     *
     * @param status       The status of the report (e.g. 200, 203, 400 etc).
     * @param content      The content (body) of the response.
     * @param headerFields A map of response headers.
     * @param latency The latency of the request-response cycle.
     */
    public BinaryHTTPResponse(int status, byte[] content, Map<String, List<String>> headerFields, long latency) {
        super(status, content, headerFields, latency);
        inputStream = new DataInputStream(new ByteArrayInputStream(content));
    }

    /**
     * Reads an integer from the response.
     * @return Returns an integer.
     */
    public int readInt() {
        try {
            return inputStream.readInt();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Reads a short from the response.
     * @return Returns a short.
     */
    public short readShort() {
        try {
            return inputStream.readShort();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Reads a long from the response.
     * @return Returns a long.
     */
    public long readLong() {
        try {
            return inputStream.readLong();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Reads a character from the response.
     * @return Returns a character.
     */
    public char readChar() {
        try {
            return inputStream.readChar();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Reads a byte from the response.
     * @return Returns a byte.
     */
    public byte readByte() {
        try {
            return inputStream.readByte();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Reads a string from the response.
     * @return Returns a string.
     */
    public String readString() {
        try {
            return inputStream.readUTF();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Reads a boolean from the response.
     * @return Returns a boolean.
     */
    public boolean readBoolean() {
        try {
            return inputStream.readBoolean();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Reads a float from the response.
     * @return Returns a float.
     */
    public float readFloat() {
        try {
            return inputStream.readFloat();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Reads a double from the response.
     * @return Returns a double.
     */
    public double readDouble() {
        try {
            return inputStream.readDouble();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
