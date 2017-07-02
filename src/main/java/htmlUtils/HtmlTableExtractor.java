package htmlUtils;

import api.UrlParser;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;


public class HtmlTableExtractor implements UrlParser {
    private String tableTagName;
    private String tableTagValue;
    private String source;
    private Document document;
    private int positionOfTagInDocument = 0;
    public HtmlTableExtractor(String source, boolean isUrl){
        this.source = source;
        changeDoc(source, isUrl);
    }
    public HtmlTableExtractor(String tableTagName, String tableTagValue, String source, boolean isUrl) {
        this.tableTagName = tableTagName;
        this.tableTagValue = tableTagValue;
        this.source = source;
        changeDoc(source, isUrl);
    }

    public int getPositionOfTagInDocument() {
        return positionOfTagInDocument;
    }

    public void setPositionOfTagInDocument(int positionOfTagInDocument) {
        this.positionOfTagInDocument = positionOfTagInDocument;
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

    public Document getDocument() {
        return document;
    }
    @Override
    public void setDocument(Document document) {
        this.document = document;
    }

    private Element getTableByTagName(){
        Elements elements = document.getElementsByAttribute(tableTagName);
        return elements.get(positionOfTagInDocument);
    }

    private Element getTableByTagNameAndValue(){
        Elements elements = document.getElementsByAttributeValue(tableTagName,tableTagValue);
        return elements.get(positionOfTagInDocument);
    }

    public HtmlArray getHtmlArray(){
        Element htmlTable;
        if (tableTagValue == null) htmlTable = getTableByTagName();
        else htmlTable = getTableByTagNameAndValue();

        HtmlArray htmlArray = new HtmlArray();

        Elements headers = htmlTable.getElementsByAttribute("th");
        if (headers != null) {
            String[] columnsNames = new String[headers.size()];
            for (int i = 0; i< headers.size(); i++){
                columnsNames[i] = headers.get(i).text();
            }
            htmlArray.setColumnsNames(columnsNames);
        }

        Elements rows = htmlTable.getElementsByAttribute("tr");
        int beginIndex = 0;
        if (headers != null) beginIndex = 1;
        int nrOfColumns = rows.get(beginIndex).getElementsByAttribute("td").size();

        List<Map<String,String>> values = new LinkedList<>();
        for (int i = beginIndex; i<rows.size(); i++){
            Elements cols = rows.get(i).getElementsByAttribute("td");
            Map<String, String> map = new HashMap<String,String>();
            for(Integer j = 0; j<cols.size(); j++){
                map.put(j.toString(),cols.get(j).text());
            }
            values.add(map);
        }

        htmlArray.setValues(values);

        return htmlArray;
    }

}
