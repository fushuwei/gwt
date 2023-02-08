package com.mochousoft.gwt.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.RootPanel;

import java.util.logging.Logger;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 *
 * @author fushuwei
 */
public class App implements EntryPoint {

    private static final Logger logger = Logger.getLogger(App.class.getName());

    @Override
    public void onModuleLoad() {
        Button button1 = new Button("方法无public修饰符");
        button1.setStyleName("btn");
        Button button2 = new Button("默认");
        button2.setStyleName("btn");
        Button button3 = new Button("自定义类型名称");
        button3.setStyleName("btn");
        Button button4 = new Button("自定义类型命名空间：JsPackage.GLOBAL");
        button4.setStyleName("btn");
        Button button5 = new Button("自定义类型命名空间：任意字符串");
        button5.setStyleName("btn");
        Button button6 = new Button("自定义类型名称 + 自定义类型命名空间");
        button6.setStyleName("btn");

        RootPanel.get("button1").add(button1);
        RootPanel.get("button2").add(button2);
        RootPanel.get("button3").add(button3);
        RootPanel.get("button4").add(button4);
        RootPanel.get("button5").add(button5);
        RootPanel.get("button6").add(button6);
    }
}
