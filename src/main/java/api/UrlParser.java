package api;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.net.URL;

public interface UrlParser {
    public void setDocument(Document document);
    public default void changeDoc(String html, boolean isURL){
        Document doc = null;
        try{
            if (!isURL) doc = Jsoup.parseBodyFragment(html);
            else{
                URL url = new URL(html);
                doc = Jsoup.parse(url, 3000);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        setDocument(doc);
    }
}
