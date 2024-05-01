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

public class Translate {
    private String textFrom;
    private Language langFrom;
    private Language langTo;
    
    public Translate() {}

    public Translate(String textFrom, Language langFrom, Language langTo) {
        this.textFrom = textFrom;
        this.langFrom = langFrom;
        this.langTo = langTo;
    }
    
    public String getTextFrom() {
        return textFrom;
    }

    public void setTextFrom(String textFrom) {
        this.textFrom = textFrom;
    }

    public Language getLangFrom() {
        return langFrom;
    }

    public void setLangFrom(Language langFrom) {
        this.langFrom = langFrom;
    }

    public Language getLangTo() {
        return langTo;
    }

    public void setLangTo(Language langTo) {
        this.langTo = langTo;
    }

    /**
     * Translates the text to other languages.
     * 
     * @return the text you want to translate to
     * @throws IOException
     * @throws URISyntaxException
     */
    public String getTextTo() throws IOException, URISyntaxException{
        String langFromCode = langFrom.getCode();
        String langToCode = langTo.getCode();
        String baseURL = "https://script.google.com/macros/s/AKfycbz9L5KTSf-KnTO_UhQxLbrH0LUeQwvGHssfxRkxej_WztIqQZUCXAWNqFSAxYmnDWxS/exec";
        String queryString 
            = "?q=" + URLEncoder.encode(textFrom,"UTF-8") 
            + "&target=" + langToCode 
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
    }
}