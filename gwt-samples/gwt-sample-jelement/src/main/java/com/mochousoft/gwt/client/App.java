package com.mochousoft.gwt.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.mochousoft.gwt.client.demo.Notification;
import com.mochousoft.gwt.client.demo.SpeechSynthesis;
import com.mochousoft.gwt.client.demo.SvgDraw;
import com.mochousoft.gwt.client.demo.WebAudio;

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
        VerticalPanel vPanel = new VerticalPanel();

        // 语音合成
        new SpeechSynthesis(vPanel);

        // 音频
        new WebAudio(vPanel);

        // 通知
        new Notification(vPanel);

        // 画图
        new SvgDraw();

        RootPanel.get().add(vPanel);
    }
}
