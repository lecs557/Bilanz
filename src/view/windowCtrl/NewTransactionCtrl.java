package view.windowCtrl;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;

import java.time.LocalDate;

public class NewTransactionCtrl {

    public TextField tb_day;
    public TextField tb_month;
    public TextField tb_year;
    public TextField tb_reason;
    public TextField tb_money;
    public TextField tb_cents;

    public void initialize() {
        LocalDate now = LocalDate.now();
        tb_day.setText(now.getDayOfMonth()+"");
        tb_month.setText(now.getMonthValue()+"");
        tb_year.setText(now.getYear()+"");
        tb_cents.setText("00");

        tb_day.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("(0|[012]?[0-9]|3[01])")) {
                tb_day.setText(oldValue);
            }
            else if(newValue.length()==2){
                tb_month.requestFocus();
            }
        });

        tb_month.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("(0?[1-9]|1[012]|0)")) {
                tb_month.setText(oldValue);
            }
            else if(newValue.length()==2){
                tb_year.requestFocus();
            }
        });

        tb_year.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("[0-9]*")) {
                tb_year.setText(oldValue);
            }
            else if(newValue.length()==4){
                tb_reason.requestFocus();
            }
        });


        tb_money.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("[0-9]*")) {
                tb_money.setText(oldValue);
            }
        });


        tb_cents.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("[0-9][0-9]?")) {
                tb_cents.setText(oldValue);
            }
        });

        tb_cents.setOnKeyTyped(keyEvent -> {
            if (keyEvent.getCode() == KeyCode.ENTER){
                ok();
            }
        });





    }

    public void ok(){
        
    }

    public void close(){

    }
}