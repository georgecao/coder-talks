package io.whitepaper.coder.talks;

import org.asciidoctor.Asciidoctor;
import org.asciidoctor.Options;

/**
 * Created by george on 21/06/2017.
 */
public class PdfConverter {
    public static void main(String... args) {
        Asciidoctor doc = Asciidoctor.Factory.create();
        Options options = Options.builder().build();
        doc.convert("/Users/george/reports/promotion.adoc", options);
    }
}
