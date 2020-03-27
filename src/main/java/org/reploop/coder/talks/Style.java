package org.reploop.coder.talks;

import org.asciidoctor.Asciidoctor;
import org.asciidoctor.OptionsBuilder;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.StringWriter;

public class Style {
    public static void main(String... args) throws IOException {
        Asciidoctor asciidoc = Asciidoctor.Factory.create();
        StringWriter sw = new StringWriter();
        try (BufferedWriter bw = new BufferedWriter(sw)) {
            bw.write("= Header Title");
            bw.newLine();
            bw.write("George Cao");
            bw.newLine();
            bw.newLine();

            bw.write("== H2");
            bw.newLine();
            bw.write("H2 Body");
            bw.newLine();
            bw.newLine();
            bw.write("content");
        }


        String output = asciidoc.convert(sw.toString(), OptionsBuilder.options());
        System.out.println(output);
    }
}
