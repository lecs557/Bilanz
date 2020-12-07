package model;

import controller.*;
import javafx.application.Application;
import javafx.stage.Stage;
import model.storeclasses.Profile;
import view.simple_panes.CreateNew;

import java.io.IOException;
import java.util.ArrayList;

public class Main extends Application {

    public enum windows {LogIn, Overview ,NewAccount,NewTransaction,RenameWindow,TransactionWindow};
    public static Stage primaryStage;
    public static Stage secStage;

    public enum types {Lastschrift, Gutschrift, Gehalt, Überweisung}


    // *** Controller ***
    public static IOController ioController;
    public static EditController editController;

    @Override
    public void start(Stage primaryStage) throws IOException{
        Main.primaryStage =primaryStage;

        ioController = new IOController();
        editController = new EditController();

        ArrayList<Profile> profiles = DatabaseController.loadStoreClass(Profile.class);
        ProfileAccountManager.setProfiles(profiles);

        CreateNew createNew = new CreateNew();
        createNew.setClassName("model.storeclasses.BankAccount");
        WindowManager.openStageOf(createNew);
        WindowManager.changeSceneTo(windows.LogIn);
        primaryStage.setTitle("Aurum Observa");
        primaryStage.show();
        primaryStage.centerOnScreen();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
