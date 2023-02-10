package com.mochousoft.gwt.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;

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
        // 标题
        HTML title = new HTML("<h1>Exporting a Java type to JavaScript</h1>");

        // 按钮
        HTML button1 = new HTML("<button id='button1' onclick='demo1()'>方法无 public 修饰符</button>");
        HTML button2 = new HTML("<button id='button2' onclick='demo2()'>默认 @JsType 参数</button>");
        HTML button3 = new HTML("<button id='button3' onclick='demo3()'>自定义类型名称</button>");
        HTML button4 = new HTML("<button id='button4' onclick='demo4()'>自定义类型命名空间：JsPackage.GLOBAL</button>");
        HTML button5 = new HTML("<button id='button5' onclick='demo5()'>自定义类型命名空间：任意字符串</button>");
        HTML button6 = new HTML("<button id='button6' onclick='demo6()'>自定义类型名称 + 自定义类型命名空间</button>");
        Button button7 = new Button("GWT JSNI", (ClickHandler) clickEvent -> demo7());

        // 水平面板
        HorizontalPanel hPanel = new HorizontalPanel();
        hPanel.setSpacing(10);
        hPanel.add(button1);
        hPanel.add(button2);
        hPanel.add(button3);
        hPanel.add(button4);
        hPanel.add(button5);
        hPanel.add(button6);
        hPanel.add(button7);

        // 垂直面板
        VerticalPanel vPanel = new VerticalPanel();
        vPanel.add(title);
        vPanel.add(hPanel);

        RootPanel.get().add(vPanel);
    }

    public static native void demo7() /*-{
        $wnd.alert("在 JSNI 方法中不能使用 JsInterop")
    }-*/;
}
