package com.mochousoft.gwt.client.jsinterop;

import jsinterop.annotations.JsType;

/**
 * Exporting a Java type to JavaScript
 *
 * @author fushuwei
 */
@JsType(namespace = "gwt.jsinterop")
public class Demo5 {
    public int x;
    public int y;

    public int sum() {
        return x + y;
    }
}
