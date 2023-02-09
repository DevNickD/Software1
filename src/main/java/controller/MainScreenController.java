package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.Inventory;
import model.Part;
import model.Product;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

/**
 * Controller class that adds functionality to the main screen.
 *
 * @author Nicholas Donnarumma
 */
public class MainScreenController implements Initializable {

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

    Stage stage;
    Parent scene;

    static boolean confirmAction(String title, String content){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle(title);
        alert.setHeaderText("Confirm");
        alert.setContentText(content);
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){
            return true;
        }
        else {
            return false;
        }
    }

    static void infoWarning(String title, String header, String content){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.showAndWait();
    }

    @FXML
    void onActionAddPart(ActionEvent event) throws IOException {

        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/AddPart.fxml"));
        stage.setTitle("Add Part");
        stage.setScene(new Scene(scene));
        stage.show();

    }

    @FXML
    void onActionAddProduct(ActionEvent event) throws IOException {

        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/AddProduct.fxml"));
        stage.setTitle("Add Product");
        stage.setScene(new Scene(scene));
        stage.show();
    }

    @FXML
    void onActionDeletePart(ActionEvent event) {
        if (partsTableView.getSelectionModel().isEmpty()){
            infoWarning("Warning!", "No Part Selected","Please choose part from the above list");
            return;
        }
        if (confirmAction("Warning!", "Would you like to delete this part?")){
            int selectedPart = partsTableView.getSelectionModel().getSelectedIndex();
            partsTableView.getItems().remove(selectedPart);
        }
    }

    @FXML
    void onActionDeleteProduct(ActionEvent event) {
        if (productsTableView.getSelectionModel().isEmpty()){
            infoWarning("Warning!", "No Product Selected","Please choose product from the above list");
            return;
        }
        if (confirmAction("Warning!", "Would you like to delete this product?")){
            int selectedPart = productsTableView.getSelectionModel().getSelectedIndex();
            productsTableView.getItems().remove(selectedPart);
        }
    }

    @FXML
    void onActionExitProgram(ActionEvent event) {
        confirmAction("Exit", "Are you sure you would like to exit the program?");
        {
            System.exit(0);
        }
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

    public boolean searchPartId(int id){
        for(Part part : Inventory.getAllParts()){
            if(part.getId() == id){
                return true;
            }
        }
        return false;
    }
    public boolean searchPartName(String name){
        for(Part part : Inventory.getAllParts()){
            if(part.getName() == name){
                return true;
            }
        }
        return false;
    }
    public boolean searchProductId(int id){
        for(Product product : Inventory.getAllProducts()){
            if(product.getId() == id){
                return true;
            }
        }
        return false;
    }
    public boolean searchProductName(String name){
        for(Product product : Inventory.getAllProducts()){
            if(product.getName() == name){
                return true;
            }
        }
        return false;
    }

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