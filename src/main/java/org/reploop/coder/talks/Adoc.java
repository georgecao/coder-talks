package org.reploop.coder.talks;

import org.asciidoctor.Asciidoctor;
import org.asciidoctor.Options;
import org.asciidoctor.ast.Document;

import java.nio.file.Path;
import java.nio.file.Paths;

public class Adoc {

    public static void main(String... args) {
        Path path = Paths.get("/Users/george/open-source/coder-talks/content/blog/2021/06/a-brief-introduce-to-rbac.adoc");
        Asciidoctor asciidoctor = Asciidoctor.Factory.create();
        Options options = Options.builder().build();
        Document doc = asciidoctor.loadFile(path.toFile(), options);
        System.out.println(doc);
        String body = asciidoctor.convertFile(path.toFile(), options);
        System.out.println(body);
    }
}
