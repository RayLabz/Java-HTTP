package com.panickapps.javahttp;

import java.util.List;
import java.util.Map;

public class HTTPResponse {

    private final int status;
    private final String content;
    private final Map<String, List<String>> headerFields;

    public HTTPResponse(int status, String content, Map<String, List<String>> headerFields) {
        this.status = status;
        this.content = content;
        this.headerFields = headerFields;
    }

    public int getStatus() {
        return status;
    }

    public String getContent() {
        return content;
    }

    public Map<String, List<String>> getHeaderFields() {
        return headerFields;
    }

    public List<String> getHeaderField(String headerField) {
        return headerFields.get(headerField);
    }

}
