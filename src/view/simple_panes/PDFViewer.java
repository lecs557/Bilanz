package view.simple_panes;

import javafx.concurrent.Worker;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.Pane;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import netscape.javascript.JSObject;
import java.io.File;
import java.nio.file.Files;
import java.util.Base64;

public final class PDFViewer extends ScrollPane{

    private final WebView web  = new WebView();
    private String path;

    public PDFViewer() {
        path = "C:/Users/User/Desktop/Marcel/Geld/ING_DiBa/Kontoauszüge/Bilanz 2017/04.2017.pdf";
        WebEngine engine = web.getEngine();
        engine.setJavaScriptEnabled(true);
        engine.load("file:///C:/Users/User/Desktop/web/viewer.html");

        engine.getLoadWorker()
                .stateProperty()
                .addListener((observable, oldValue, newValue) -> {
                    // to debug JS code by showing console.log() calls in IDE console
                    JSObject window = (JSObject) engine.executeScript("window");
                    window.setMember("java", new JSLogListener());
                    engine.executeScript("console.log = function(message){ java.log(message); };");

                    // this pdf file will be opened on application startup
                    if (newValue == Worker.State.SUCCEEDED) {
                        try {
                            // readFileToByteArray() comes from commons-io library
                            byte[] data = Files.readAllBytes(new File(path).toPath());
                            String base64 = Base64.getEncoder().encodeToString(data);
                            // call JS function from Java code
                            engine.executeScript("openFileFromBase64('" + base64 + "')");
                      //      System.out.println("OPEN");
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });
        setContent(web);
    }

    public void setPath(String path) {
        this.path = path;
    }

    public void putInto(Pane container) {
        setPrefHeight(container.getPrefHeight());
        setPrefWidth(container.getPrefWidth());
        container.getChildren().clear();
        container.getChildren().add(this);
    }
}
