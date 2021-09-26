package org.reploop.coder.talks;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;

public class Book {
    public static void main(String... args) throws IOException {
        try (WebClient client = new WebClient()) {
            String url = "https://use-the-index-luke.com/sql/partial-results";
            //String url = "https://use-the-index-luke.com/";
            HtmlPage page = client.getPage(url);
            String html = page.asXml();
            Document doc = Jsoup.parse(html);
            Elements eles = doc.select("div.content");
            System.out.println(eles.html());
        }
    }
}
