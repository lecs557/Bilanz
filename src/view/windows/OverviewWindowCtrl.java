package view.windows;

import controller.DatabaseController;
import controller.ProfileAccountManager;
import controller.ViewController;
import controller.WindowManager;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import model.Main;
import model.storeclasses.BankAccount;
import model.storeclasses.Group;
import model.storeclasses.Profile;
import model.storeclasses.Transaction;
import model.threads.PDFImporter;
import model.threads.Renamer;
import model.threads.Saver;
import view.simple_panes.CreateNew;
import view.simple_panes.ImportForBankaccount;
import view.simple_panes.StoreClassTable;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class OverviewWindowCtrl extends BaseWindowCtrl{

    public Pane tableContainer;
    private Profile currentAccount;
    private BankAccount profilesBankAccount;
    private Group profilesGroup;
    private Transaction profilesTransaction;

    public AnchorPane pane;
    public Label lbl_account;
    public ListView<BankAccount> lv_bankAccounts;
    public ListView<Group> lv_groups;

    public TabPane tabPane;
    public Pane diagrammContainer;
    public Pane sumContainer;
    public ProgressBar pb_pdf, pb_year, pb_transa,pb_year1, pb_transa1;
    public Pane savePane;
    public Pane chPane;
    public Pane pdfPane;

    private Saver saver;
    private PDFImporter pdfLoad;
    private Renamer renamer;
    private ArrayList<BankAccount> allBankAccounts;

    public void initialize() {
        currentAccount = ProfileAccountManager.getCurrentAccount();
        lbl_account.setText(currentAccount.getName());

        profilesBankAccount = new BankAccount();
        profilesBankAccount.setForeignKeyProfile(currentAccount);
        allBankAccounts = DatabaseController.computeStoreClasses(profilesBankAccount,"");

        profilesGroup = new Group();
        profilesGroup.setForeignKeyBankAccount(allBankAccounts);
        ArrayList<Group> allGroups = DatabaseController.computeStoreClasses(profilesGroup,"");

        profilesTransaction = new Transaction();
        profilesTransaction.setForeignKeyBankAccount(allBankAccounts);
        ArrayList<Transaction> allTransactions = DatabaseController.computeStoreClasses(profilesTransaction,"");

        lv_bankAccounts.getItems().addAll(allBankAccounts);
        lv_bankAccounts.setCellFactory(bankAccountListView -> {
            ListCell<BankAccount> cell = new ListCell<>() {
                @Override
                protected void updateItem(BankAccount bankAccount, boolean b) {
                    super.updateItem(bankAccount, b);
                    if (bankAccount != null) {
                        setText(bankAccount.getBankName());
                    } else {
                        setText("");
                    }
                }
            };
            return cell;
        });


        lv_groups.getItems().setAll(allGroups);
        tableContainer.getChildren().add(new StoreClassTable(allTransactions, new Transaction()));

        ViewController.setBankAccount(profilesBankAccount);
        ViewController.setLv_bankAccounts(lv_bankAccounts);
    }

    public void backToLogin(ActionEvent actionEvent) {
        ProfileAccountManager.setCurrentAccount(null);
        WindowManager.changeSceneTo(Main.windows.LogIn);
    }

    public void newBA(ActionEvent actionEvent) {
        CreateNew<BankAccount> createNew = new CreateNew<>(profilesBankAccount, false);
        WindowManager.openStageOf(createNew);
    }

    public void refresh(){

    }

    public void newGroup(ActionEvent actionEvent) {
        CreateNew<Group> createNew = new CreateNew<>(profilesGroup, false);
        WindowManager.openStageOf(createNew);
    }

    public void newTransaction(ActionEvent actionEvent) {
        CreateNew<Transaction> createNew = new CreateNew<>(profilesTransaction, false);
        WindowManager.openStageOf(createNew);
    }

    public void importTra(ActionEvent actionEvent) {
        ImportForBankaccount ifb = new ImportForBankaccount(allBankAccounts);
        WindowManager.openStageOf(ifb);

    }
}
