package model;

import controller.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {

    public static Account  currentAccount;
    public static WindowManager windowManager;
    public static AccountManager accountManager;
    public static IOController ioController;
    public static EditController editController;
    public static Stage stage;
    public static Stage newStage;
    public enum windows {Start,Account,NewAccount,NewTransaction,RenameWindow,TransactionWindow};

    @Override
    public void start(Stage primaryStage) throws IOException{
        stage=primaryStage;

        windowManager=new WindowManager();
        accountManager= new AccountManager();
        ioController = new IOController();
        editController = new EditController();

        Parent root = FXMLLoader.load(getClass().getResource("/view/windows/Start.fxml"));
        primaryStage.setTitle("Finanzen");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
        if (getParameters().getUnnamed().size() > 0) {
            String path = getParameters().getUnnamed().get(0);
            String name = path.split("/")[path.split("/").length - 1].replace(".konto", "");
            ioController.load(name, path);
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
