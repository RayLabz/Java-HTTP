package com.raylabz.javahttp;

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

public class HTTPResponse {

    private final int status;
    private final String content;
    private final Map<String, List<String>> headerFields;

    /**
     * Creates an HTTPResponse object.
     * @param status The status of the report (e.g. 200, 203, 400 etc).
     * @param content The content (body) of the response.
     * @param headerFields A map of response headers.
     */
    public HTTPResponse(int status, String content, Map<String, List<String>> headerFields) {
        this.status = status;
        this.content = content;
        this.headerFields = headerFields;
    }

    /**
     * Returns the status of the response.
     * @return status
     */
    public int getStatus() {
        return status;
    }

    /**
     * Returns the content (body) of the response.
     * @return content
     */
    public String getContent() {
        return content;
    }

    /**
     * Returns a map of header fields of the response.
     * @return headerFields
     */
    public Map<String, List<String>> getHeaderFields() {
        return headerFields;
    }

    /**
     * Retrieves a list of values for a specific header field.
     * @param headerField The header field to retrieve the values from.
     * @return A List of Strings.
     */
    public List<String> getHeaderField(String headerField) {
        return headerFields.get(headerField);
    }

}
