package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;
import java.net.URL;
import java.util.ResourceBundle;

public class AddProductController implements Initializable {

    @FXML
    private TableView<?> addTableView;

    @FXML
    private TableColumn<?, ?> addinvlevelCol;

    @FXML
    private TableColumn<?, ?> addpartidCol;

    @FXML
    private TableColumn<?, ?> addpartnameCol;

    @FXML
    private TableColumn<?, ?> addpriceCol;

    @FXML
    private TextField addproductSearch;

    @FXML
    private TextField addproductidTxt;

    @FXML
    private TextField addproductinvTxt;

    @FXML
    private TextField addproductmaxTxt;

    @FXML
    private TextField addproductminTxt;

    @FXML
    private TextField addproductnameTxt;

    @FXML
    private TextField addproductpriceTxt;

    @FXML
    private TableView<?> removeTableView;

    @FXML
    private TableColumn<?, ?> removeinvlevelCol;

    @FXML
    private TableColumn<?, ?> removepartidCol;

    @FXML
    private TableColumn<?, ?> removepartnameCol;

    @FXML
    private TableColumn<?, ?> removepriceCol;

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