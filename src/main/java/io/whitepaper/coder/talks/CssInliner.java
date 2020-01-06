package io.whitepaper.coder.talks;

import org.fit.cssbox.css.CSSNorm;
import org.fit.cssbox.css.DOMAnalyzer;
import org.fit.cssbox.css.NormalOutput;
import org.fit.cssbox.io.DOMSource;
import org.fit.cssbox.io.DefaultDOMSource;
import org.fit.cssbox.io.DefaultDocumentSource;
import org.fit.cssbox.io.DocumentSource;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * What's this about?
 *
 * @since 2017-06-04 20
 */
public class CssInliner {
    public static void main(String... args) throws IOException, SAXException {
        Path dir = Paths.get("D:\\open\\coder-talks\\target\\generated-docs");

        String url = "https://www.baidu.com/";
        DocumentSource ds = new DefaultDocumentSource(url);
        DOMSource source = new DefaultDOMSource(ds);
        Document doc = source.parse();
        DOMAnalyzer da = new DOMAnalyzer(doc, ds.getURL());
        da.attributesToStyles(); //convert the HTML presentation attributes to inline styles
        da.addStyleSheet(null, CSSNorm.stdStyleSheet(), DOMAnalyzer.Origin.AGENT); //use the standard style sheet
        da.addStyleSheet(null, CSSNorm.userStyleSheet(), DOMAnalyzer.Origin.AGENT); //use the additional style sheet
        da.getStyleSheets(); //load the author style sheets
        da.stylesToDomInherited();
        NormalOutput output = new NormalOutput(doc);
        output.dumpTo(System.out);
    }
}
