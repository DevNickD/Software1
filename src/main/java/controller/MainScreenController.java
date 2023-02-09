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
import model.Product;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainScreenController implements Initializable {

    Stage stage;
    Parent scene;

    @FXML
    void onActionAddPart(ActionEvent event) throws IOException {

        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/AddPart.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();

    }

    @FXML
    void onActionAddProduct(ActionEvent event) throws IOException {

        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/AddProduct.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    @FXML
    void onActionDeletePart(ActionEvent event) {
        //System.out.println("Delete Part Button Clicked");
    }

    @FXML
    void onActionDeleteProduct(ActionEvent event) {
        //System.out.println("Delete Product Button Clicked");
    }

    @FXML
    void onActionExitProgram(ActionEvent event) {
        System.exit(0);
    }

    @FXML
    void onActionModifyPart(ActionEvent event) throws IOException {
        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/ModifyPart.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();

    }

    @FXML
    void onActionModifyProduct(ActionEvent event) throws IOException {
        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/ModifyProduct.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();

    }

    @FXML
    private TableView<Part> partsTableView;

    @FXML
    private TableColumn<Part, Integer> mainpartidCol;

    @FXML
    private TableColumn<Part, Integer> mainpartinvCol;

    @FXML
    private TableColumn<Part, String> mainpartnameCol;

    @FXML
    private TableColumn<Part, Double> mainpartpriceCol;

    @FXML
    private TextField mainpartsSearch;

    @FXML
    private TableView<Product> productsTableView;
    @FXML
    private TableColumn<Product, Integer> mainprodidCol;

    @FXML
    private TableColumn<Product, Integer> mainprodinvCol;

    @FXML
    private TableColumn<Product, String> mainprodnameCol;

    @FXML
    private TableColumn<Product, Double> mainprodpriceCol;

    @FXML
    private TextField mainproductsSearch;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        partsTableView.setItems(Inventory.getAllParts());
        mainpartidCol.setCellValueFactory(new PropertyValueFactory<Part, Integer>("id"));
        mainpartnameCol.setCellValueFactory(new PropertyValueFactory<Part, String>("name"));
        mainpartinvCol.setCellValueFactory(new PropertyValueFactory<Part, Integer>("stock"));
        mainpartpriceCol.setCellValueFactory(new PropertyValueFactory<Part, Double>("price"));

        productsTableView.setItems((Inventory.getAllProducts()));
        mainprodidCol.setCellValueFactory(new PropertyValueFactory<Product, Integer>("id"));
        mainprodnameCol.setCellValueFactory(new PropertyValueFactory<Product, String>("name"));
        mainprodinvCol.setCellValueFactory(new PropertyValueFactory<Product, Integer>("stock"));
        mainprodpriceCol.setCellValueFactory(new PropertyValueFactory<Product, Double>("price"));
    }
}