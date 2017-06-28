package api;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.net.URL;

/**
 * Created by Cziczarito on 11.06.2017.
 */
public interface UrlParser {
    public void setDoc(Document doc);
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
        setDoc(doc);
    }
}
