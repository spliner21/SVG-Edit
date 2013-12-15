package com.larvalabs.svgandroid;

/**
 * Runtime exception thrown when there is a problem parsing an SVG.
 *
 * @author Larva Labs, LLC
 */
public class SVG1ParseException extends RuntimeException {

    public SVG1ParseException(String s) {
        super(s);
    }

    public SVG1ParseException(String s, Throwable throwable) {
        super(s, throwable);
    }

    public SVG1ParseException(Throwable throwable) {
        super(throwable);
    }
}
