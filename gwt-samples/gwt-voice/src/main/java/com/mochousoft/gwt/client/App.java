package com.mochousoft.gwt.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.RootPanel;
import gwt.jelement.core.Promise;
import gwt.jelement.speech.SpeechSynthesisUtterance;
import gwt.jelement.speech.SpeechSynthesisVoice;

import java.util.Arrays;
import java.util.function.Predicate;
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
        Button button = new Button("语音合成", (ClickHandler) clickEvent -> speechSynthesis());
        RootPanel.get().add(button);
    }

    public void speechSynthesis() {
        // 判断浏览器是否支持语音合成
        if (!window.object().has("speechSynthesis")) {
            window.alert("no support for speech synthesis");
            return;
        }

        // 判断浏览器语音合成是否加载中
        if (window.getSpeechSynthesis().isPending()) {
            window.getSpeechSynthesis().cancel();
        }


        getVoicesReady().then(x -> {
            intro();
            return null;
        }).catch_(error -> {
            window.alert(((Error) error).getMessage());
            return null;
        });
    }

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

    private void intro() {
        SpeechSynthesisVoice maleUSVoice = getPreferredVoice("en-US",
            voice -> !voice.getName().contains("Zira")
                && !voice.getName().contains("Female"));
        SpeechSynthesisVoice femaleUSVoice = getPreferredVoice("en-US",
            voice -> voice.getName().contains("Zira")
                || voice.getName().contains("Female"));
        SpeechSynthesisVoice maleUkVoice = getPreferredVoice("en-GB",
            voice -> !voice.getName().contains("Female"));

        say("Hello. Welcome to the speech synthesis demo.", maleUSVoice);
        say("You sound like a robot. I think I sound better.", femaleUSVoice);
        boolean otherLanguages =
            say("en-GB", "Actually, everything sounds better with a British accent, don't you think?");
        otherLanguages = otherLanguages | say("fr", "Sur Chrome, nous pouvons parler de nombreuses langues.");
        otherLanguages = otherLanguages | say("es-US", "Como el español por ejemplo.");
        otherLanguages = otherLanguages | say("es-ES", "O el típico español de España.", "Yo soy de Zaragoza!");
        say("es-US", "Que dices mujer?", "Que mi español no es típico?", "Yo soy de Monterrey, Mejico!");
        otherLanguages = otherLanguages | say("it", "Non dimenticare di me! parlo italiano");
        otherLanguages = otherLanguages | say("de", "Und ich spreche deutsch.");
        otherLanguages = otherLanguages | say("zh", "我可以说中文.");
        otherLanguages = otherLanguages | say("ko", "나는 한국어를 할 수 있습니다.");
        otherLanguages = otherLanguages | say("ru", "И я говорю по-русски.");
        say("fr", "I can even speak English with a French accent.", "C'est amusant, ça!");
        say("fr", "Il y a aussi d'autres langues, mais ça devient un peu ennuyeux...");
        say("pt", "Ei, espere um pouco, você conseguiu ignorar o português?", " Braaaasil!", "Braaaasil!", "Braaaasil!");
        if (maleUkVoice != null) {
            say("I want to hear other languages!", maleUkVoice);
        } else {
            say("en-GB", "I want to hear other languages!");
        }
        if (!otherLanguages) {
            say("Guess what, we can only speak American English.", maleUSVoice);
            say("How dumb! I want to be on Chrome. They speak many languages there.", femaleUSVoice);
        } else {
            say("I'm sorry Dave. I'm afraid I can't do that.", maleUSVoice);
        }
    }

    private SpeechSynthesisVoice getPreferredVoice(String language, Predicate<SpeechSynthesisVoice> predicate) {
        SpeechSynthesisVoice[] candidates = Arrays.stream(window.getSpeechSynthesis().getVoices())
            .filter(voice -> voice.getLang().contains(language))
            .toArray(SpeechSynthesisVoice[]::new);
        if (candidates.length != 0) {
            return Arrays.stream(candidates)
                .filter(predicate)
                .findFirst()
                .orElse(candidates[0]);
        }
        return null;
    }

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

    private boolean isLanguageSupported(String language) {
        return Arrays.stream(window.getSpeechSynthesis().getVoices())
            .anyMatch(voice -> voice.getLang().contains(language));
    }

    private void say(String text, SpeechSynthesisVoice voice) {
        SpeechSynthesisUtterance utterance = new SpeechSynthesisUtterance(text);
        utterance.setVoice(voice);
        window.getSpeechSynthesis().speak(utterance);
    }
}
