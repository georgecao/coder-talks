package io.whitepaper.coder.talks;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.concurrent.TimeUnit;

/**
 * What's this about?
 *
 * @since 2017-06-04 20
 */
public class CssInliner {
    public static void main(String... args) {
        Path dir = Paths.get("D:\\open\\coder-talks\\target\\generated-docs");
        try (WebClient client = new WebClient(BrowserVersion.CHROME);
             BufferedWriter writer = new BufferedWriter(new FileWriter(dir.resolve("expand.html").toFile(), false))) {
            Path path = dir.resolve("the-bad-of-thrift.html");
            HtmlPage page = client.getPage(path.toUri().toURL());
            client.waitForBackgroundJavaScript(TimeUnit.SECONDS.toMillis(20));
            //System.out.println(page.asXml());
            writer.write(page.asXml());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
