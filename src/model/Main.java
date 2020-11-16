package model;

import controller.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import view.simple_panes.PDFViewer;

import java.io.IOException;

public class Main extends Application {

    public enum windows {Start,Account,NewAccount,NewTransaction,RenameWindow,TransactionWindow};
    public static Stage primaryStage;
    public static Stage secStage;

    public enum types {Lastschrift, Gutschrift, Gehalt, Überweisung}
    public static Profile currentAccount;
    public static String SEPARATOR = "§-§";
    public static String ENDSEPARATOR = "§END§";
    public static String OPTIONSEPARATOR = "§OOO§";

    // *** Controller ***
    public static WindowManager windowManager;
    public static ProfileAccountManager accountManager;
    public static IOController ioController;
    public static EditController editController;

    @Override
    public void start(Stage primaryStage) throws IOException{
        Main.primaryStage =primaryStage;

        windowManager=new WindowManager();
        accountManager= new ProfileAccountManager();
        ioController = new IOController();
        editController = new EditController();

        Parent root = FXMLLoader.load(getClass().getResource("/view/windows/Start.fxml"));
        primaryStage.setTitle("Finanzen");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
        if (getParameters().getUnnamed().size() > 0) {
            String path = getParameters().getUnnamed().get(0);
            String name = path.split("/")[path.split("/").length - 1].replace(".konto", "");
            ioController.startLoad(name, path);
        }
        Stage sec = new Stage();
        sec.setScene(new Scene(new PDFViewer("C:/Users/User/Desktop/Marcel/Geld/Käufe/2017/PC.pdf")));
        sec.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
