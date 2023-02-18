package com.mochousoft.gwt.client.jsinterop;

import jsinterop.annotations.JsPackage;
import jsinterop.annotations.JsType;

/**
 * Exporting a Java type to JavaScript
 *
 * @author fushuwei
 */
@JsType(name = "RenameDemo6", namespace = JsPackage.GLOBAL)
public class Demo6 {
    public int x;
    public int y;

    public int sum() {
        return x + y;
    }
}
