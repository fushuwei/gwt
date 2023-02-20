package com.mochousoft.gwt.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.RootPanel;
import com.mochousoft.gwt.shared.People;

import java.util.logging.Logger;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 *
 * @author fushuwei
 */
public class App implements EntryPoint {

    private static final Logger logger = Logger.getLogger(App.class.getName());

    /**
     * This is the entry point method.
     */
    public void onModuleLoad() {

        Button button = new Button("Hello World", (ClickHandler) clickEvent -> {
            People people = new People("Jack", 18);
            logger.info("My name is " + people.getName() + ", and I am " + people.getAge());
            Window.alert("View browser console!");
        });

        RootPanel.get().add(button);
    }
}
