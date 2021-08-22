package io.whitepaper.coder.talks;

import com.steadystate.css.parser.CSSOMParser;
import com.steadystate.css.parser.SACParserCSS3;
import org.apache.commons.lang3.tuple.Pair;
import org.apache.http.client.utils.URIBuilder;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.css.sac.InputSource;
import org.w3c.dom.css.*;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.StringReader;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Copy {
    private static final Logger LOG = LoggerFactory.getLogger(Copy.class);

    private static final String STYLE_KEY = "style";
    private static final String STYLE_LINK_KEY = "link[rel=stylesheet]";

    public static void main(String... args) throws IOException {
        URIBuilder u;
        u.addParameter();
        String file = "/Users/george/open-source/coder-talks/target/generated-docs/the-bad-of-thrift.html";
        Document doc = Jsoup.parse(new File(file), "UTF-8");
        CSSOMParser parser = new CSSOMParser(new SACParserCSS3());

        Elements styles = doc.select(STYLE_KEY);
        List<CSSStyleSheet> styleSheets = style(styles, parser);

        Elements links = doc.select(STYLE_LINK_KEY);
        List<CSSStyleSheet> sheets = link(links, parser);

        Stream<List<CSSStyleSheet>> stream = Stream.of(styleSheets, sheets);

        Map<Element, Map<String, String>> elementStyleMap = new HashMap<>();

        Stream.of(styleSheets, sheets)
                .flatMap(Collection::stream)
                .forEach(sheet -> {
                    CSSRuleList rules = sheet.getCssRules();
                    for (int i = 0; i < rules.getLength(); i++) {
                        CSSRule rule = rules.item(i);
                        if (rule instanceof CSSStyleRule) {
                            CSSStyleRule styleRule = (CSSStyleRule) rule;
                            String selector = styleRule.getSelectorText();
                            if (!selector.contains(":")) {
                                Elements elements = doc.select(selector);
                                for (Element element : elements) {
                                    CSSStyleDeclaration declaration = styleRule.getStyle();
                                    for (int idx = 0; idx < declaration.getLength(); idx++) {
                                        String name = declaration.item(idx);
                                        String value = declaration.getPropertyValue(name);
                                        elementStyleMap.computeIfAbsent(element, e -> new HashMap<>()).put(name, value);
                                    }
                                }
                            }
                        }
                    }
                });
        elementStyleMap.forEach((element, styleMap) ->
                styleMap.entrySet()
                        .stream()
                        .map(entry -> entry.getKey() + ":" + entry.getValue())
                        .reduce((s, s2) -> s + ";" + s2)
                        .ifPresent(style -> {
                            element.attr(STYLE_KEY, style);
                            element.removeAttr("class");
                        }));
        doc.html();
        System.out.println(doc);
    }

    private static List<CSSStyleSheet> link(Elements styles, CSSOMParser parser) {

        return styles.stream()
                .map(element -> {
                    String uri = element.attr("href");
                    return Pair.of(element.baseUri(), uri);
                })
                .map(pair -> {
                            String url = pair.getRight();
                            if (url.startsWith("https://") || url.startsWith("http://")) {
                                return parse(parser, url);
                            } else {
                                Path baseUri = Paths.get(pair.getLeft()).getParent();
                                Path path = baseUri.resolve(url).normalize();
                                return parse(parser, path);
                            }
                        }
                )
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }

    private static CSSStyleSheet parse(CSSOMParser parser, String url) {
        try {
            return parser.parseStyleSheet(new InputSource(url), null, null);
        } catch (IOException e) {
            LOG.warn("Cannot parse style sheet from url {}", url, e);
        }
        return null;
    }

    private static CSSStyleSheet parse(CSSOMParser parser, Path path) {
        try {
            return parser.parseStyleSheet(new InputSource(new FileReader(path.toFile())), null, null);
        } catch (IOException e) {
            LOG.warn("Cannot parse style sheet from path {}", path, e);
        }
        return null;
    }

    private static List<CSSStyleSheet> style(Elements styles, CSSOMParser parser) {
        return styles.stream()
                .map(Element::data)
                .map(style -> {
                            try {
                                return parser.parseStyleSheet(new InputSource(new StringReader(style)), null, null);
                            } catch (IOException e) {
                                LOG.warn("Cannot parse style sheet from text {}", style, e);
                            }
                            return null;
                        }
                )
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }

    private static void test(CSSOMParser parser) throws IOException {
        Path dir = Paths.get("/Users/george/open-source/coder-talks/target/generated-docs/");
        CSSStyleSheet sheet = parser.parseStyleSheet(new InputSource(new FileReader(dir.resolve("coderay-asciidoctor.css").toFile())), null, null);
        System.out.println(sheet);

    }

}
