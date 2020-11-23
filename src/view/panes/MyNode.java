package view.panes;

import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXMLLoader;

import javafx.scene.Node;
import javafx.scene.control.Control;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;

import java.io.IOException;

public class MyNode extends Region {

    private FXMLLoader loader;
    private SimpleStringProperty path = new SimpleStringProperty("panes/test");

    public MyNode()  {
        try {
            loader = new FXMLLoader(getClass().getResource("/view/"+path.get()+".fxml"));
            this.getChildren().clear();
            this.getChildren().add(loader.load());
        } catch (IOException e) {
            e.printStackTrace();
        }
        path.addListener((observableValue, s, t1) -> {
            try {
                loader = new FXMLLoader(getClass().getResource("/view/"+path.get()+".fxml"));
                this.getChildren().clear();
                this.getChildren().add(loader.load());
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    public String getPath() {
        return path.get();
    }

    public SimpleStringProperty pathProperty() {
        return path;
    }

    public void setPath(String path){
        this.path.set(path);
    }

    public FXMLLoader getLoader() {
        return loader;
    }
}
