package com.mochousoft.gwt.client.leaflet;

import jsinterop.annotations.JsType;

/**
 * todo
 *
 * @author fushuwei
 */
@JsType(isNative = true, namespace = "L")
public class Map {

    public native L setView(double[] center, int zoom);
}
