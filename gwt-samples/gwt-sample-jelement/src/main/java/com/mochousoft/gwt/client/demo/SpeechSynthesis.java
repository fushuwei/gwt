package com.mochousoft.gwt.client.demo;

import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.SubmitButton;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import gwt.jelement.core.Promise;
import gwt.jelement.speech.SpeechSynthesisUtterance;
import gwt.jelement.speech.SpeechSynthesisVoice;

import java.util.Arrays;
import java.util.function.Predicate;

import static gwt.jelement.Browser.window;

/**
 * Speech Synthesis Demo
 *
 * @author fushuwei
 */
public class SpeechSynthesis {

    /**
     * Constructor Method
     *
     * @param panel Panel
     */
    public SpeechSynthesis(Panel panel) {
        // 标题
        HTML title = new HTML("<h1>语音合成</h1>");

        // 按钮
        Button button1 = new Button("英语", (ClickHandler) clickEvent -> speak("en-US"));
        Button button2 = new Button("汉语", (ClickHandler) clickEvent -> speak("zh-CN"));
        Button button3 = new Button("多语种混合", (ClickHandler) clickEvent -> speak("mixin"));
        Button button4 = new Button("未知语言", (ClickHandler) clickEvent -> speak("unknown"));

        // 输入框
        TextBox textBox = new TextBox();
        textBox.setWidth("500px");

        // 提交按钮
        SubmitButton submitButton1 = new SubmitButton("英语发音", clickEvent -> {
            // 获取输入的内容
            String text = textBox.getValue();
            if (text == null || "".equals(text)) {
                return;
            }

            // 获取 Google US English 语音
            SpeechSynthesisVoice usVoice = getVoice("en-US", voice -> true);
            // 获取 Google UK English Female 语音
            SpeechSynthesisVoice femaleUKVoice = getVoice("en-GB", voice -> voice.getName().contains("Female"));
            // 获取 Google UK English Male 语音
            SpeechSynthesisVoice maleUKVoice = getVoice("en-GB", voice -> voice.getName().contains("Male"));

            // 语音播报
            say(text, usVoice);
            say(text, femaleUKVoice);
            say(text, maleUKVoice);
        });
        SubmitButton submitButton2 = new SubmitButton("汉语发音", clickEvent -> {
            // 获取输入的内容
            String text = textBox.getValue();
            if (text == null || "".equals(text)) {
                return;
            }

            // 获取 Microsoft Huihui 语音
            SpeechSynthesisVoice microsoftHuihuiVoice = getVoice("zh-CN", voice -> voice.getName().contains("Microsoft Huihui"));
            // 获取 Google 普通话语音
            SpeechSynthesisVoice googleVoice = getVoice("zh-CN", voice -> voice.getName().contains("Google 普通话"));

            // 语音播报
            say(text, microsoftHuihuiVoice);
            say(text, googleVoice);
        });

        // 水平面板 1
        HorizontalPanel hPanel1 = new HorizontalPanel();
        hPanel1.setSpacing(10);
        hPanel1.add(button1);
        hPanel1.add(button2);
        hPanel1.add(button3);
        hPanel1.add(button4);

        // 水平面板 2
        HorizontalPanel hPanel2 = new HorizontalPanel();
        hPanel2.setSpacing(10);
        hPanel2.add(textBox);
        hPanel2.add(submitButton1);
        hPanel2.add(submitButton2);

        // 垂直面板
        VerticalPanel vPanel = new VerticalPanel();
        vPanel.add(title);
        vPanel.add(hPanel1);
        vPanel.add(hPanel2);

        panel.add(vPanel);
    }

    /**
     * 播音
     */
    private void speak(String language) {
        if (!checkBrowser()) {
            return;
        }
        getVoicesReady().then(x -> {
            switch (language) {
                case "en-US":
                    speakEnglish();
                    break;
                case "zh-CN":
                    speakChinese();
                    break;
                case "mixin":
                    speakMixin();
                    break;
                default:
                    window.alert("unknown language or no support for language");
                    break;
            }
            return null;
        }).catch_(error -> {
            window.alert(((Error) error).getMessage());
            return null;
        });
    }

    /**
     * 讲英语
     */
    private void speakEnglish() {
        // 获取 Google US English 语音
        SpeechSynthesisVoice usVoice = getVoice("en-US", voice -> true);
        // 语音播报
        say("Hello. Welcome to the speech synthesis demo. You sound like a robot. I think I sound better. " +
            "Actually, everything sounds better with a British accent, don't you think?", usVoice);

        // 获取 Google UK English Female 语音
        SpeechSynthesisVoice femaleUKVoice = getVoice("en-GB", voice -> voice.getName().contains("Female"));
        // 语音播报
        say("Hello. Welcome to the speech synthesis demo. You sound like a robot. I think I sound better. " +
            "Actually, everything sounds better with a British accent, don't you think?", femaleUKVoice);

        // 获取 Google UK English Male 语音
        SpeechSynthesisVoice maleUKVoice = getVoice("en-GB", voice -> voice.getName().contains("Male"));
        // 语音播报
        say("Hello. Welcome to the speech synthesis demo. You sound like a robot. I think I sound better. " +
            "Actually, everything sounds better with a British accent, don't you think?", maleUKVoice);
    }

    /**
     * 讲汉语
     */
    private void speakChinese() {
        // 获取 Microsoft Huihui 语音
        SpeechSynthesisVoice microsoftHuihuiVoice = getVoice("zh-CN", voice -> voice.getName().contains("Microsoft Huihui"));
        // 语音播报
        say("你好，我是微软慧慧，我可以说中文，请听：您有新短消息，请注意查收", microsoftHuihuiVoice);

        // 获取 Google 普通话语音
        SpeechSynthesisVoice googleVoice = getVoice("zh-CN", voice -> voice.getName().contains("Google 普通话"));
        // 语音播报
        say("你好，我是Google普通话，我也可以说中文，请听：您有新短消息，请注意查收", googleVoice);
    }

    /**
     * 混合语音
     */
    private void speakMixin() {
        say("en-GB", "Actually, everything sounds better with a British accent, don't you think?");
        say("fr", "Sur Chrome, nous pouvons parler de nombreuses langues.");
        say("es-US", "Como el español por ejemplo.");
        say("es-ES", "O el típico español de España.", "Yo soy de Zaragoza!");
        say("es-US", "Que dices mujer?", "Que mi español no es típico?", "Yo soy de Monterrey, Mejico!");
        say("it", "Non dimenticare di me! parlo italiano");
        say("de", "Und ich spreche deutsch.");
        say("zh", "我可以说中文. 您有新短消息，请注意查收");
        say("ko", "나는 한국어를 할 수 있습니다.");
        say("ru", "И я говорю по-русски.");
        say("fr", "I can even speak English with a French accent.", "C'est amusant, ça!");
        say("fr", "Il y a aussi d'autres langues, mais ça devient un peu ennuyeux...");
        say("pt", "Ei, espere um pouco, você conseguiu ignorar o português?", " Braaaasil!", "Braaaasil!", "Braaaasil!");
        say("en-GB", "I want to hear other languages!");
    }

    /**
     * 获取语音
     *
     * @param language  语言/区域
     * @param predicate 过滤条件，用于筛选语音
     * @return SpeechSynthesisVoice
     */
    private SpeechSynthesisVoice getVoice(String language, Predicate<SpeechSynthesisVoice> predicate) {
        SpeechSynthesisVoice[] candidates = Arrays.stream(window.getSpeechSynthesis().getVoices())
            .filter(voice -> voice.getLang().contains(language))
            .toArray(SpeechSynthesisVoice[]::new);
        if (candidates.length != 0) {
            return Arrays.stream(candidates).filter(predicate).findFirst().orElse(candidates[0]);
        }
        return null;
    }

    /**
     * 使用指定的语音播报文字
     *
     * @param text  文本
     * @param voice 语音
     */
    private void say(String text, SpeechSynthesisVoice voice) {
        SpeechSynthesisUtterance utterance = new SpeechSynthesisUtterance(text);
        utterance.setVoice(voice);
        window.getSpeechSynthesis().speak(utterance);
    }

    /**
     * 使用指定的语言播报文字
     *
     * @param language 语言/区域
     * @param text     文本
     * @return 是否成功
     */
    private boolean say(String language, String... text) {
        if (isLanguageSupported(language)) {
            for (String phrase : text) {
                SpeechSynthesisUtterance utterance = new SpeechSynthesisUtterance(phrase);
                utterance.setLang(language);
                window.getSpeechSynthesis().speak(utterance);
            }
            return true;
        }
        return false;
    }

    /**
     * 准备就绪
     *
     * @return Promise
     */
    private Promise<Void> getVoicesReady() {
        return new Promise<>((resolve, reject) -> {
            SpeechSynthesisVoice[] voices = window.getSpeechSynthesis().getVoices();
            if (voices.length != 0) {
                resolve.with(null);
            } else if (window.getSpeechSynthesis().object().has("onvoiceschanged")) {
                window.getSpeechSynthesis().setOnVoiceschanged(event -> {
                    window.getSpeechSynthesis().setOnVoiceschanged(null);
                    if (window.getSpeechSynthesis().getVoices().length != 0) {
                        resolve.with(null);
                    } else {
                        reject.with(new Error("No voices are available."));
                    }
                    return null;
                });
            } else {
                reject.with(new Error("No voices are available."));
            }
        });
    }

    /**
     * 校验浏览器是否支持语音合成技术
     *
     * @return true or false
     */
    private boolean checkBrowser() {
        // 判断浏览器是否支持语音合成
        if (!window.object().has("speechSynthesis")) {
            window.alert("no support for speech synthesis");
            return false;
        }
        // 判断浏览器语音合成是否加载中
        if (window.getSpeechSynthesis().isPending()) {
            window.getSpeechSynthesis().cancel();
            return false;
        }
        return true;
    }

    /**
     * 校验是否支持指定的语言
     *
     * @param language 语言/区域
     * @return true or false
     */
    private boolean isLanguageSupported(String language) {
        return Arrays.stream(window.getSpeechSynthesis().getVoices())
            .anyMatch(voice -> voice.getLang().contains(language));
    }
}
