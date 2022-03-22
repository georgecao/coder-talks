package org.reploop.coder.talks;

import org.asciidoctor.Asciidoctor;
import org.asciidoctor.Options;
import org.asciidoctor.SafeMode;
import org.asciidoctor.ast.Document;
import org.asciidoctor.ast.StructuralNode;
import org.asciidoctor.jruby.ast.impl.BlockImpl;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.function.Consumer;

public class Adoc {

    public static void main(String... args) throws Exception {
        Path path = Paths.get("/Users/george/open-source/coder-talks/content/blog/2021/06/a-brief-introduce-to-rbac.adoc");
        Files.lines(path).forEach(new Consumer<String>() {
            @Override
            public void accept(String s) {
                System.out.println(s);
            }
        });
        Asciidoctor asciidoctor = Asciidoctor.Factory.create();
        Options options = Options.builder()
                .safe(SafeMode.UNSAFE)
                .build();
        Document doc = asciidoctor.loadFile(path.toFile(), options);
        System.out.println(doc);
        var blocks = doc.getBlocks();
        String body = asciidoctor.convert(Files.readString(path), options);
        for (StructuralNode node : blocks) {
            BlockImpl ik;
            System.out.println(node);
        }
        System.out.println(body);
    }
}
