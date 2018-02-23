package io.whitepaper.coder.talks;

import org.asciidoctor.Asciidoctor;
import org.asciidoctor.AttributesBuilder;

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
