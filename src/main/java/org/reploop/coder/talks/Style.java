package org.reploop.coder.talks;

import org.asciidoctor.Asciidoctor;
import org.asciidoctor.Options;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

public class Style {
    public static void main(String... args) throws IOException {
        Map<String, Object> map = new HashMap<>();
        Map<String, Object> val = new HashMap<>();
        val.put("x", "y");
        map.put("key", val);
        System.out.println(map);

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


        String output = asciidoc.convert(sw.toString(), Options.builder().build());
        System.out.println(output);
    }
}
