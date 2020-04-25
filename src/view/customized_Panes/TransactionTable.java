package view.customized_Panes;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Font;
import javafx.util.Callback;
import model.Main;
import model.Transaction;

import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class TransactionTable extends TableView<Transaction> {


    public TransactionTable()  {

        setItems(Main.currentAccount.getTransactions());

        //DATUM
        TableColumn<Transaction, LocalDate> date =new TableColumn<>("Datum");
        date.setMinWidth(120);
        date.setCellValueFactory(new PropertyValueFactory<>("date"));
        date.setCellFactory(new Callback<>() {
            @Override
            public TableCell<Transaction, LocalDate> call(TableColumn<Transaction, LocalDate> transactionStringTableColumn) {
                TableCell<Transaction, LocalDate> cell = new TableCell<>() {
                    @Override
                    protected void updateItem(LocalDate item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty || item == null) {
                            setText(null);
                            setGraphic(null);
                        } else {
                            Label l = new Label(item.format(DateTimeFormatter.ofPattern("dd LLLL yyyy")));
                            setGraphic(l);// TODO mit Scenebuilder
                        }
                    }
                };
                cell.setAlignment(Pos.CENTER);
                return cell;
            }
        });

        //ZWECK
        TableColumn<Transaction, String> zweck =new TableColumn<>("zweck");
        zweck.setMinWidth(200);
        zweck.setCellValueFactory(new PropertyValueFactory<>("reason"));
        zweck.setCellFactory(new Callback<>() {
            @Override
            public TableCell<Transaction, String> call(TableColumn<Transaction, String> transactionStringTableColumn) {
                TableCell<Transaction, String> cell = new TableCell<>() {
                    @Override
                    protected void updateItem(String item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty || item == null) {
                            setText(null);
                            setGraphic(null);
                        } else {
                            Label l = new Label(item);
                            setGraphic(l);// TODO mit Scenebuilder
                        }
                    }
                };
                cell.setAlignment(Pos.CENTER);
                return cell;
            }
        });


        //BETRAG
        TableColumn<Transaction, Integer> betrag =new TableColumn<Transaction, Integer>("betrag");
        betrag.setMinWidth(80);
        betrag.setCellValueFactory(new PropertyValueFactory<Transaction,Integer>("betrag"));
        betrag.setCellFactory(new Callback<>() {
            @Override
            public TableCell<Transaction, Integer> call(TableColumn<Transaction, Integer> transactionStringTableColumn) {
                TableCell<Transaction, Integer> cell = new TableCell<>() {
                    @Override
                    protected void updateItem(Integer item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty || item == null) {
                            setText(null);
                            setGraphic(null);
                        } else {
                            Label l = new Label(new DecimalFormat("#0.00").format((double)item/100)+" €");
                            if (item > 0){
                                l.setStyle("-fx-text-fill: green;");
                            } else if (item < 0) {
                                l.setStyle("-fx-text-fill: red;");
                            } else {
                            }
                            setGraphic(l);// TODO mit Scenebuilder
                        }
                    }
                };
                cell.setAlignment(Pos.CENTER);
                return cell;
            }
        });


        //BETRAG
        TableColumn<Transaction, Integer> stand =new TableColumn<Transaction, Integer>("Kontostand");
        stand.setMinWidth(100);
        stand.setCellValueFactory(new PropertyValueFactory<Transaction,Integer>("konto"));
        stand.setCellFactory(new Callback<>() {
            @Override
            public TableCell<Transaction, Integer> call(TableColumn<Transaction, Integer> transactionStringTableColumn) {
                TableCell<Transaction, Integer> cell = new TableCell<>() {
                    @Override
                    protected void updateItem(Integer item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty || item == null) {
                            setText(null);
                            setGraphic(null);
                        } else {
                            Label l = new Label(new DecimalFormat("#0.00").format((double)item/100)+" €");
                            if (item > 0){
                                l.setStyle("-fx-text-fill: green;");
                            } else if (item < 0) {
                                l.setStyle("-fx-text-fill: red;");
                            }
                            l.setFont(new Font("Arial", 18));
                            setGraphic(l);// TODO mit Scenebuilder
                        }
                    }
                };
                cell.setAlignment(Pos.CENTER);
                return cell;
            }
        });




        getColumns().addAll(date, zweck, betrag, stand);
    }

    @Override
    protected double computePrefHeight(double width){
        return 50+Main.currentAccount.getTransactions().size()*50;
    }

    @Override
    protected double computeMaxHeight(double width){
        return 200;
    }
}
