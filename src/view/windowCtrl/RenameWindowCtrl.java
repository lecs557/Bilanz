package view.windowCtrl;

import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import model.Main;
import model.Renames;
import model.Sum;
import model.Transaction;

import java.util.ArrayList;


public class RenameWindowCtrl {

    public VBox vb_reasons;
 //   public ArrayList<Renames> renames = new ArrayList<>();
    public Renames rename;
    private TextField tf;
    private TextField tf1;


    public void initialize() {
      tf = new TextField();
      tf1 = new TextField();
      vb_reasons.getChildren().addAll(tf,tf1);
    }

    public void ok() {
        rename = new Renames(tf.getText(),tf1.getText());
        Main.editController.rename(rename);
//        for(Sum s: Main.currentAccount.getSums()){
//            for(HBox h: replacements){
//                if(s.getReason().equals(   ((Label)h.getChildren().get(0)).getText()      )    ){
//                    s.setReason( ((TextField)h.getChildren().get(1)).getText()   );
//                }
//            }
//        }




    }
}
