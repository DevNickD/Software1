package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import java.net.URL;
import java.util.ResourceBundle;

public class AddPartController implements Initializable {

    @FXML
    private RadioButton addpartinhouse;

    @FXML
    private RadioButton addpartoutsourced;

    @FXML
    private TextField partidTxt;

    @FXML
    private TextField partinvTxt;

    @FXML
    private TextField partmachineidTxt;

    @FXML
    private TextField partmaxTxt;

    @FXML
    private TextField partminTxt;

    @FXML
    private TextField partnameTxt;

    @FXML
    private TextField partpriceTxt;

    @FXML
    void onActionCancelForm(ActionEvent event) {
        //System.out.println("CANCEL BUTTON CLICKED");

    }

    @FXML
    void onActionSaveForm(ActionEvent event) {
        //System.out.println("Save Button Clicked");

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        System.out.println("I am initialized");
    }
}