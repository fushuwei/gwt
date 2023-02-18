package com.mochousoft.gwt.client.jsinterop;

import jsinterop.annotations.JsType;

/**
 * Exporting a Java type to JavaScript
 *
 * @author fushuwei
 */
@JsType(name = "RenameDemo3")
public class Demo3 {
    public int x;
    public int y;

    public int sum() {
        return x + y;
    }
}
