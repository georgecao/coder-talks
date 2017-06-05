package io.whitepaper.coder.talks;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.io.OutputStreamWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.concurrent.Worker;
import javafx.scene.Scene;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

/**
 * What's this about?
 *
 * @since 2017-06-04 18
 */
public class Inliner extends Application {

    public static void main(String... args) throws Exception {
        launch(args);
    }

    public void start(Stage primaryStage) throws Exception {
        WebView webView = new WebView();
        final WebEngine engine = webView.getEngine();
        engine.getLoadWorker().stateProperty().addListener(new ChangeListener<Worker.State>() {
            public void changed(ObservableValue observable,
                                Worker.State oldValue, Worker.State newValue) {
                System.out.println(newValue);
                if (newValue == Worker.State.SUCCEEDED) {
                    Document doc = engine.getDocument();
                    NodeList list = doc.getElementsByTagName("style");
                    for (int i = 0; i < list.getLength(); i++) {
                        Node item = list.item(i);
                        item.getTextContent();
                        System.out.println(item.getTextContent());
                    }
                    try {
                        Transformer transformer = TransformerFactory.newInstance().newTransformer();
                        transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "no");
                        transformer.setOutputProperty(OutputKeys.METHOD, "xml");
                        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
                        transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
                        transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");

                        transformer.transform(new DOMSource(doc),
                                new StreamResult(new OutputStreamWriter(System.out, "UTF-8")));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        Path path = Paths.get("E:\\open\\coder-talks\\target\\generated-docs\\the-bad-of-thrift.html");
        Optional<String> content = Files.lines(path).reduce((s, s2) -> s + s2);
        engine.loadContent(content.get());
        //engine.load("http://stackoverflow.com");
        Scene scene = new Scene(webView);
        ObservableList<String> i = scene.getStylesheets();

        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
