package tool;

import java.io.IOException;
import java.net.URLEncoder;

import javafx.scene.control.Alert;
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
            Alert alert1 = new Alert(Alert.AlertType.ERROR);
            alert1.setTitle("Error");
            alert1.setHeaderText("Internet connection error");
            alert1.setContentText("Unable to translate due to internet connection error!");
            alert1.show();
        }
    }
}
