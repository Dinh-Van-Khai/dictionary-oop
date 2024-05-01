package tool;

import java.io.IOException;
import java.net.URLEncoder;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class Sound {

    /**
     * Pronounce a text.
     * @param text the text to be pronounced
     * @param lang the language of the text
     */
    public static void getSound(String text, Language lang) {
        try {
            String langCode = lang.getCode();
            String baseURL = "https://translate.google.com.vn/translate_tts?ie=UTF-8&q=";
            String queryString
                = URLEncoder.encode(text, "UTF-8") 
                + "&tl=" + langCode +"&client=tw-ob";
            Media sound = new Media(baseURL + queryString);
            MediaPlayer mediaPlayer = new MediaPlayer(sound);
            mediaPlayer.play();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
