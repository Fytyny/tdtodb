package htmlUtils;

import api.UrlParser;
import databaseUtils.Column;
import databaseUtils.Table;
import org.jsoup.nodes.Document;

import java.util.LinkedList;

public class HtmlTable extends AbstractHtmlArray implements UrlParser {
    private String tableTagName;
    private String tableTagValue;
    private String source;
    private Document doc;
    public HtmlTable(String source, boolean isUrl){
        this.source = source;
        changeDoc(source, isUrl);
    }
    public HtmlTable(String tableTagName, String tableTagValue, String source, boolean isUrl) {
        this.tableTagName = tableTagName;
        this.tableTagValue = tableTagValue;
        this.source = source;
        changeDoc(source, isUrl);
    }

    @Override
    public Table toTable() {
        return null;
    }

    @Override
    public LinkedList<Column> generateColumnsTypes() {
        return null;
    }

    public String getTableTagName() {
        return tableTagName;
    }

    public void setTableTagName(String tableTagName) {
        this.tableTagName = tableTagName;
    }

    public String getTableTagValue() {
        return tableTagValue;
    }

    public void setTableTagValue(String tableTagValue) {
        this.tableTagValue = tableTagValue;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public Document getDoc() {
        return doc;
    }

    public void setDoc(Document doc) {
        this.doc = doc;
    }
}
