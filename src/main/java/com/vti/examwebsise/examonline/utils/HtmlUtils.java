package com.vti.examwebsise.examonline.utils;

import lombok.NonNull;
import lombok.experimental.UtilityClass;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

@UtilityClass
public class HtmlUtils {

    public String addTag(@NonNull String htmlCode) {

        String[] parts = htmlCode.replace("h2>", "p>").split("<p>");
        if (parts.length < 3) {
            return htmlCode.replace("p>", "h2>");
        }
        if (parts.length > 3) {
            return htmlCode;
        }

        String headerHtml = "<h2>" + parts[1].replace("</p>", "</h2>");
        String codeHtml = "<p>" + parts[2].replace("\n", "</p><p>") + "</p>";
        codeHtml = codeHtml.replace("<br>", "</p><p>");
        return headerHtml + codeHtml;
    }
    public String removeTag(@NonNull String htmlCode) {
        htmlCode = htmlCode.replace("&nbsp;", "%space");
        Document doc = Jsoup.parse(htmlCode.replace("<i>", "").replace("</i>", ""));
        StringBuilder b = new StringBuilder();
        for (Element p : doc.select("h2,p")) {
            b.append(p.text());
            b.append(System.getProperty("line.separator"));
        }

        return b.toString().replaceAll("%space", " ");

    }


}
