package com.mochousoft.gwt.client.leaflet;

import jsinterop.annotations.JsMethod;
import jsinterop.annotations.JsType;

/**
 * todo
 *
 * @author fushuwei
 */
@JsType(isNative = true)
public class Map {

    @JsMethod
    public native L setView(double[] center, int zoom);
}
