package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.Inventory;
import model.Part;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Controller class that adds functionality to the add product screen.
 *
 * @author Nicholas Donnarumma
 */
public class AddProductController implements Initializable {

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
    private TableView<Part> addTableView;

    @FXML
    private TableColumn<Part, Integer> addinvlevelCol;

    @FXML
    private TableColumn<Part, Integer> addpartidCol;

    @FXML
    private TableColumn<Part, String> addpartnameCol;

    @FXML
    private TableColumn<Part, Double> addpriceCol;

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
    private TableView<Part> removeTableView;

    @FXML
    private TableColumn<Part, Integer> removeinvlevelCol;

    @FXML
    private TableColumn<Part, Integer> removepartidCol;

    @FXML
    private TableColumn<Part, String> removepartnameCol;

    @FXML
    private TableColumn<Part, Double> removepriceCol;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        //Table and Columns parts that aren't associated
        addTableView.setItems(Inventory.getAllParts());
        addpartidCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        addpartnameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        addinvlevelCol.setCellValueFactory(new PropertyValueFactory<>("stock"));
        addpriceCol.setCellValueFactory(new PropertyValueFactory<>("price"));

        //Table and Columns for associated parts
        removeTableView.setItems(Inventory.getAllParts());
        removepartidCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        removepartnameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        removeinvlevelCol.setCellValueFactory(new PropertyValueFactory<>("stock"));
        removepriceCol.setCellValueFactory(new PropertyValueFactory<>("price"));

    }
}