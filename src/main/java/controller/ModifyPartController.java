package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import java.net.URL;
import java.util.ResourceBundle;

public class ModifyPartController implements Initializable {

    @FXML
    private TextField modpartidTxt;

    @FXML
    private RadioButton modpartinhouse;

    @FXML
    private TextField modpartinvTxt;

    @FXML
    private TextField modpartmachineidTxt;

    @FXML
    private TextField modpartmaxTxt;

    @FXML
    private TextField modpartminTxt;

    @FXML
    private TextField modpartnameTxt;

    @FXML
    private RadioButton modpartoutsourced;

    @FXML
    private TextField modpartpriceTxt;

    @FXML
    void onActionCancelModifyForm(ActionEvent event) {

    }

    @FXML
    void onActionSaveModifyForm(ActionEvent event) {

    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        System.out.println("I am initialized");
    }
}