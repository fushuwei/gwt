package com.mochousoft.gwt.client.jsinterop;

import jsinterop.annotations.JsType;

/**
 * Exporting a Java type to JavaScript
 *
 * @author fushuwei
 */
@JsType
public class Demo1 {
    int x;
    int y;

    int sum() {
        return x + y;
    }
}
