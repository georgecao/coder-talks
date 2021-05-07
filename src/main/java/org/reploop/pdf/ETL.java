package org.reploop.pdf;

import com.google.common.base.Strings;
import com.itextpdf.text.pdf.PdfDictionary;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.parser.PdfTextExtractor;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ETL {

    public static void main(String... args) throws IOException {
        //Path dir = Paths.get("/Users/george/reports/2019-Q1/wiki");
        Path dir = Paths.get("/Users/george/Downloads");

        read(dir.resolve("Patterns Principles and Practices of Domain-Driven Design.pdf"));

    }

    public static void read(Path path) throws IOException {
        PdfReader reader = new PdfReader(path.toUri().toURL());
        int pages = reader.getNumberOfPages();
        for (int i = 1; i <= pages; i++) {
            PdfDictionary pn = reader.getPageN(i);
            String text = PdfTextExtractor.getTextFromPage(reader, i);
            System.out.println(text);
            if (Strings.isNullOrEmpty(text)) {
                continue;
            }
            break;
        }
    }
}