package com.raylabz.javahttp;

import java.util.List;
import java.util.Map;

public abstract class HTTPResponse<ContentType> {

    protected final int status;
    protected final ContentType content;
    protected final Map<String, List<String>> headerFields;

    /**
     * Creates an HTTPResponse object.
     * @param status The status of the report (e.g. 200, 203, 400 etc).
     * @param content The content (body) of the response.
     * @param headerFields A map of response headers.
     */
    public HTTPResponse(int status, ContentType content, Map<String, List<String>> headerFields) {
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
     * Returns if the status of the response is successful (within 200-299 inclusive)
     * @return status
     */
    public boolean isSuccess() {
        return (status >= 200 && status <= 299);
    }

    /**
     * Returns the content (body) of the response.
     * @return content
     */
    public ContentType getContent() {
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
