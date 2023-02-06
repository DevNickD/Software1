package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ModifyProductController implements Initializable {

    Stage stage;
    Parent scene;

    @FXML
    void onActionAddPart(ActionEvent event) {

    }

    @FXML
    void onActionCancel(ActionEvent event) throws IOException {
        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/MainScreen.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    @FXML
    void onActionRemovePart(ActionEvent event) {

    }

    @FXML
    void onActionSave(ActionEvent event) throws IOException {
        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/MainScreen.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }

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


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        System.out.println("I am initialized");
    }
}