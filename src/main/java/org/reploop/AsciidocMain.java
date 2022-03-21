package org.reploop;

import java.io.BufferedWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;
import java.util.function.Consumer;
import java.util.regex.Pattern;

public class AsciidocMain {
    private static final String regex = "([^-，,*])\s+(http.*?])";
    private static final Pattern pattern = Pattern.compile(regex);

    public static void main(String... args) throws Exception {
        Files.walk(Paths.get("/Users/george/open-source/coder-talks/content/blog"))
                .filter(p -> p.toString().endsWith(".adoc"))
                .forEach(new Consumer<Path>() {
                    @Override
                    public void accept(Path path) {

                        try {
                            List<String> lines = Files.readAllLines(path);
                            try (BufferedWriter w = Files.newBufferedWriter(path, StandardCharsets.UTF_8, StandardOpenOption.WRITE)) {
                                for (String line : lines) {
                                    w.write(line.replaceAll(regex, "$1[.underline]##$2##"));
                                    //       w.write(line.replaceAll(regex, "``$1``"));
                                    w.newLine();
                                }
                            }
                        } catch (Exception e) {
                            System.err.println(path);
                            e.printStackTrace();
                        }
                    }
                });
    }
}
