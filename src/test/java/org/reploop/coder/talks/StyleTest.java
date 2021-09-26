package org.reploop.coder.talks;

import org.asciidoctor.Asciidoctor;
import org.asciidoctor.AttributesBuilder;
import org.asciidoctor.OptionsBuilder;
import org.asciidoctor.SafeMode;
import org.junit.Test;

import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

public class StyleTest {

    @Test
    public void name() {
        System.out.println(StyleTest.class.getResource(""));
        System.out.println(StyleTest.class.getResource("."));
        System.out.println(StyleTest.class.getResource("/"));
        System.out.println();
        System.out.println(ClassLoader.getSystemResource(""));
        System.out.println(ClassLoader.getSystemResource("."));
        System.out.println(ClassLoader.getSystemResource("org/reploop/coder/talks/StyleTest.class"));
    }

    @Test
    public void main() throws Exception {
        URL url = StyleTest.class.getResource("/the-bad-of-thrift.adoc");
        Path path = Paths.get(url.toURI());
        System.out.println(url);
        Asciidoctor asciidoc = Asciidoctor.Factory.create();
        OptionsBuilder ob = OptionsBuilder.options();
        ob.safe(SafeMode.UNSAFE)
                .baseDir(path.getParent().toFile());
        AttributesBuilder ab = AttributesBuilder.attributes();
        ab.linkCss(false);
        //ab.copyCss(true);
        ab.stylesDir(path.getParent().toString());
        ab.attribute("source-highlighter", "highlightjs");
        ab.attribute("highlightjsdir", "highlightjs");
        ab.attribute("highlightjs-theme", "solarized-dark");
        ab.stylesDir("");
        ab.styleSheetName("xxx.css");
        ob.attributes(ab);


        List<String> files = Files.find(path.getParent(), 1, (path1, attributes) -> path1.toString().endsWith("adoc"))
                .map(file -> {
                    try {
                        return Files.lines(file).collect(Collectors.joining("\r\n"));
                    } catch (IOException e) {
                        throw new IllegalArgumentException(e);
                    }
                }).collect(Collectors.toList());
        for (String body : files) {
            String content = asciidoc.convert(body, ob);
            System.out.println(content);
            System.out.println();
        }
    }
}