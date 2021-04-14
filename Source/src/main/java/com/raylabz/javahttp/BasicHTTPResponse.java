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

public class BasicHTTPResponse extends HTTPResponse<String> {

    /**
     * Creates an HTTPResponse object.
     *
     * @param status       The status of the report (e.g. 200, 203, 400 etc).
     * @param content      The content (body) of the response.
     * @param headerFields A map of response headers.
     */
    public BasicHTTPResponse(int status, String content, Map<String, List<String>> headerFields) {
        super(status, content, headerFields);
    }

}
