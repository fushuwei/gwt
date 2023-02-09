package com.mochousoft.gwt.client;

import com.google.gwt.core.client.EntryPoint;
import com.mochousoft.gwt.client.leaflet.L;

import java.util.logging.Logger;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 *
 * @author fushuwei
 */
public class App implements EntryPoint {

    private static final Logger logger = Logger.getLogger(App.class.getName());

    double[] positions = {51.505, -0.09};

    @Override
    public void onModuleLoad() {
        L.map("map").setView(positions, 13);
    }
}
