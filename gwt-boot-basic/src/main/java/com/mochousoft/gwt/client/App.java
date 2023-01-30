package com.mochousoft.gwt.client;

import java.util.logging.Logger;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.RootPanel;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 *
 * @author fushuwei
 */
public class App implements EntryPoint {

    private static final Logger logger = Logger.getLogger(App.class.getName());

    @Override
    public void onModuleLoad() {
        Button button = new Button("Click me");
        button.addClickHandler(clickEvent -> {
            Window.alert("Hello World!");
            logger.info("Hello World!");
        });

        RootPanel.get("helloButton").add(button);
    }
}
