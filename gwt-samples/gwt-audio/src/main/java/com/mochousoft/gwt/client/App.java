package com.mochousoft.gwt.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import gwt.jelement.dom.Element;
import gwt.jelement.webaudio.AudioContext;
import gwt.jelement.webaudio.AudioContextState;
import gwt.jelement.webaudio.GainNode;
import gwt.jelement.webaudio.OscillatorNode;
import gwt.jelement.webaudio.OscillatorType;

import java.util.logging.Logger;

import static gwt.jelement.Browser.document;
import static gwt.jelement.Browser.window;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 *
 * @author fushuwei
 */
public class App implements EntryPoint {

    private static final Logger logger = Logger.getLogger(App.class.getName());

    private AudioContext audioContext;
    private OscillatorNode osc;
    private GainNode gain;
    private OscillatorNode osc2;
    private Button btnSoundOn;
    private Button btnSoundOff;

    /**
     * This is the entry point method.
     */
    public void onModuleLoad() {
        // 标题
        HTML title = new HTML("<h1>音频合成示例</h1>");

        // 按钮
        btnSoundOn = new Button("启动", (ClickHandler) clickEvent -> start());
        btnSoundOff = new Button("停止", (ClickHandler) clickEvent -> stop());

        // 设置 btnSoundOff 按钮禁用
        btnSoundOff.setEnabled(false);

        // 水平面板 1
        HorizontalPanel hPanel = new HorizontalPanel();
        hPanel.setSpacing(10);
        hPanel.add(btnSoundOn);
        hPanel.add(btnSoundOff);

        // 垂直面板
        VerticalPanel vPanel = new VerticalPanel();
        vPanel.add(title);
        vPanel.add(hPanel);

        RootPanel.get().add(vPanel);
    }

    /**
     * 启动
     */
    private void start() {
        boolean audioSupported = window.object().has("AudioContext");
        if (!audioSupported) {
            window.alert("Web audio is not supported in this browser");
            return;
        }

        audioContext = new AudioContext();
        osc = audioContext.createOscillator();
        osc.setType(OscillatorType.SQUARE);
        osc.connect(audioContext.getDestination(), 0);
        osc.getFrequency().setValue(550);

        gain = audioContext.createGain();
        gain.getGain().setValue(100);
        gain.connect(osc.getFrequency());

        osc2 = audioContext.createOscillator();
        osc2.getFrequency().setValue(1.25);
        osc2.setType(OscillatorType.TRIANGLE);
        osc2.connect(gain);

        osc.start();
        osc2.start();

        btnSoundOn.setEnabled(false);
        btnSoundOff.setEnabled(true);
    }

    /**
     * 停止
     */
    private void stop() {
        osc.stop(audioContext.getCurrentTime() + 1);
        osc2.stop();
        osc.disconnect();
        osc2.disconnect();
        gain.disconnect();
        if (audioContext.getState() != AudioContextState.CLOSED) {
            audioContext.close();
        }
        btnSoundOn.setEnabled(true);
        btnSoundOff.setEnabled(false);
    }
}