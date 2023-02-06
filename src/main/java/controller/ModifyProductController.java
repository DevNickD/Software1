package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;
import java.net.URL;
import java.util.ResourceBundle;

public class ModifyProductController implements Initializable {

    @FXML
    private TextField modprodSearch;

    @FXML
    private TableColumn<?, ?> modprodaddinvCol;

    @FXML
    private TableColumn<?, ?> modprodaddpartidCol;

    @FXML
    private TableColumn<?, ?> modprodaddpartnameCol;

    @FXML
    private TableColumn<?, ?> modprodaddpriceCol;

    @FXML
    private TextField modprodidTxt;

    @FXML
    private TextField modprodinvTxt;

    @FXML
    private TextField modprodmaxTxt;

    @FXML
    private TextField modprodminTxt;

    @FXML
    private TextField modprodnameTxt;

    @FXML
    private TextField modprodpriceTxt;

    @FXML
    private TableColumn<?, ?> modprodremoveinvCol;

    @FXML
    private TableColumn<?, ?> modprodremovepartidCol;

    @FXML
    private TableColumn<?, ?> modprodremovepartnameCol;

    @FXML
    private TableColumn<?, ?> modprodremovepriceCol;

    @FXML
    void onActionAddPart(ActionEvent event) {

    }

    @FXML
    void onActionCancel(ActionEvent event) {

    }

    @FXML
    void onActionRemovePart(ActionEvent event) {

    }

    @FXML
    void onActionSave(ActionEvent event) {

    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        System.out.println("I am initialized");
    }
}