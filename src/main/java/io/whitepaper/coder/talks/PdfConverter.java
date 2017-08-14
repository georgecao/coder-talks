package io.whitepaper.coder.talks;

import jdk.incubator.http.HttpClient;
import jdk.incubator.http.HttpRequest;
import org.asciidoctor.Asciidoctor;
import org.asciidoctor.Attributes;
import org.asciidoctor.AttributesBuilder;

import java.net.URL;

/**
 * Created by george on 21/06/2017.
 */
public class PdfConverter {
    public static void main(String... args) {
        Asciidoctor doc = Asciidoctor.Factory.create();
        AttributesBuilder attributes = AttributesBuilder.attributes().backend("pdf");
        doc.convert("/Users/george/reports/promotion.adoc", attributes.asMap());
    }
}
