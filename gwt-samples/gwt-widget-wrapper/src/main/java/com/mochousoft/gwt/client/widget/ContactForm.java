package com.mochousoft.gwt.client.widget;

import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DecoratorPanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

/**
 * 自定义表单封装
 *
 * @author fushuwei
 */
public class ContactForm extends Composite {

    public ContactForm() {
        DecoratorPanel decPanel = new DecoratorPanel();

        // Create a Vertical Panel
        VerticalPanel vPanel = new VerticalPanel();
        vPanel.setSpacing(10);
        vPanel.setWidth("650px");

        vPanel.add(new HTML("<h1>封装公共 Widget, 展示 GWT 可重用能力</h1>"));

        Label nameLabel = new Label("Full Name");
        TextBox nameTextBox = new TextBox();

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

        // Return the content
        vPanel.ensureDebugId("cwHorizontalPanel");

        decPanel.setWidget(vPanel);

        initWidget(decPanel);
    }
}
