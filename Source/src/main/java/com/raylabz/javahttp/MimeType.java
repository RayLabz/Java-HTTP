package com.raylabz.javahttp;

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
 * Enumerates commonly used MIME types.
 */
public enum MimeType {

    CONTENT_TYPE_JSON("application/json"),
    CONTENT_TYPE_URL_ENCODED("application/x-www-form-urlencoded"),
    CONTENT_TYPE_TEXT("text/plain"),
    CONTENT_TYPE_STREAM("application/xml"),
    CONTENT_TYPE_BMP("image/bmp"),
    CONTENT_TYPE_CSS("text/css"),
    CONTENT_TYPE_CSV("text/csv"),
    CONTENT_TYPE_HTML("text/html"),
    CONTENT_TYPE_JPG("image/jpeg"),
    CONTENT_TYPE_MP3("audio.mpeg"),
    CONTENT_TYPE_PNG("image/png"),

    ;

    public static final String CONTENT_TYPE_KEY = "Content-Type";

    private final String text;

    /**
     * Constructs a MimeType object.
     * @param text The textual representation of the MimeType (Visit: https://developer.mozilla.org/en-US/docs/Web/HTTP/Basics_of_HTTP/MIME_types/Complete_list_of_MIME_types)
     */
    MimeType(String text) {
        this.text = text;
    }

    /**
     * Returns the textual representation of the Mime Type.
     * @return text
     */
    public String getText() {
        return text;
    }

}
