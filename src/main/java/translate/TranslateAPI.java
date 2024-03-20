package translate;

import tool.Language;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URLEncoder;
import java.net.URL;
import java.net.URI;
import java.net.URISyntaxException;

public class TranslateAPI {

    /**
     * Translates the text into other languages.
     * 
     * @param text the text you want to translate from
     * @param langFrom the language you want to translate from
     * @param langInto the language you want to translate into
     * @return the text you want to translate into
     * @throws IOException
     * @throws URISyntaxException
     */
    public static String translate(String text, Language langFrom, Language langInto) {
        try {
            String langFromCode = langFrom.getCode();
            String langIntoCode = langInto.getCode();
            String baseURL = "https://script.google.com/macros/s/AKfycbz9L5KTSf-KnTO_UhQxLbrH0LUeQwvGHssfxRkxej_WztIqQZUCXAWNqFSAxYmnDWxS/exec";
            String queryString 
                = "?q=" + URLEncoder.encode(text,"UTF-8") 
                + "&target=" + langIntoCode 
                + "&source=" + langFromCode;

            URI uri = new URI(baseURL+queryString);
            URL url = uri.toURL();
            StringBuilder response = new StringBuilder();
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestProperty("User-Agent", "Mozilla/5.0");
            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();
            return response.toString();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}