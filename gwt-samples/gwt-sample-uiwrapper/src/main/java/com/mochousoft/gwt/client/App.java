package com.mochousoft.gwt.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DecoratorPanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.mochousoft.gwt.client.widget.ContactForm;

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
        HorizontalPanel hPanel = new HorizontalPanel();
        hPanel.setSpacing(100);
        // 传统方式
        hPanel.add(traditional());
        // 组件封装方式
        hPanel.add(new ContactForm());

        RootPanel.get().add(hPanel);
    }

    /**
     * 传统写法
     *
     * @return
     */
    private Widget traditional() {
        DecoratorPanel decPanel = new DecoratorPanel();

        // Create a Vertical Panel
        VerticalPanel vPanel = new VerticalPanel();
        vPanel.setSpacing(10);
        vPanel.setWidth("650px");

        vPanel.add(new HTML("<h1>传统做法：逐个画控件，直至页面完成</h1>"));

        Label nameLabel = new Label("Full Name");
        TextBox nameTextBox = new TextBox();
        nameTextBox.setWidth("50");

        Label emailLabel = new Label("Email Address");
        TextBox emailTextBox = new TextBox();

        Label messageLabel = new Label("Message");
        TextArea messageTextArea = new TextArea();

        Button submitButton = new Button("Submit");

        vPanel.add(nameLabel);
        vPanel.add(nameTextBox);

        vPanel.add(emailLabel);
        vPanel.add(emailTextBox);

        vPanel.add(messageLabel);
        vPanel.add(messageTextArea);

        vPanel.add(submitButton);

        decPanel.setWidget(vPanel);

        return decPanel;
    }
}
