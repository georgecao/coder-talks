package io.whitepaper.coder.talks;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * What's this about?
 *
 * @since 2017-06-04 20
 */
public class CssInliner {
    public static void main(String... args) {
        try (WebClient client = new WebClient(BrowserVersion.CHROME)) {
            Path path = Paths.get("E:\\open\\coder-talks\\target\\generated-docs\\the-bad-of-thrift.html");
            HtmlPage page = client.getPage(path.toUri().toURL());
            //System.out.println(page.asXml());
            Path p = path.getParent().resolve("expand.html");
            Files.newBufferedWriter(p).write(page.asXml());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
