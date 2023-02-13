package com.mochousoft.gwt.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import gwt.jelement.notifications.Notification;
import gwt.jelement.notifications.NotificationOptions;

import java.util.logging.Logger;

import static gwt.jelement.Browser.window;

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
        // 标题
        HTML title = new HTML("<h1>浏览器消息通知</h1><span>注意：需要设置浏览器允许接收来自该站点的消息，否则无法模拟效果</span>");

        // 文本框
        TextBox textBox = new TextBox();
        textBox.setWidth("500px");
        textBox.setValue("请输入您要通知的内容");

        // 按钮
        Button button = new Button("发送通知", (ClickHandler) clickEvent -> notice(textBox.getValue()));

        // 水平面板
        HorizontalPanel hPanel = new HorizontalPanel();
        hPanel.setSpacing(10);
        hPanel.add(textBox);
        hPanel.add(button);

        // 垂直面板
        VerticalPanel vPanel = new VerticalPanel();
        vPanel.add(title);
        vPanel.add(hPanel);

        RootPanel.get().add(vPanel);
    }

    /**
     * 发送通知
     */
    private void notice(final String body) {
        // 校验浏览器是否支持发送通知
        if (!window.object().has("Notification")) {
            window.alert("Notifications are not supported on this browser");
            return;
        }

        // 请求浏览器授权发送通知
        Notification.requestPermission().then(response -> {
            if ("granted".equals(response)) {
                NotificationOptions notificationOptions = new NotificationOptions();
                notificationOptions.setBody(body);
                notificationOptions.setIcon("images/gwt-logo.png");
                notificationOptions.setImage("images/code.png");
                notificationOptions.setRequireInteraction(true);
                new Notification("提示", notificationOptions);
            }
            return null;
        });
    }
}
