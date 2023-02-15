package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import model.Inventory;
import model.Part;
import model.Product;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

/**
 * Controller class that adds functionality to the Main Screen
 *
 * @author Nicholas Donnarumma
 */
public class MainScreenController implements Initializable {

    /**
     * Table View for the Parts table in the Main Screen
     */
    @FXML
    private TableView<Part> partsTableView;

    /**
     * The ID column for the Main Screen's Parts Table
     */
    @FXML
    private TableColumn<Part, Integer> mainpartidCol;

    /**
     * The Inventory Level column for the Main Screen's Parts Table
     */
    @FXML
    private TableColumn<Part, Integer> mainpartinvCol;

    /**
     * The Name column for the Main Screen's Parts Table
     */
    @FXML
    private TableColumn<Part, String> mainpartnameCol;

    /**
     * The Price column for the Main Screen's Parts Table
     */
    @FXML
    private TableColumn<Part, Double> mainpartpriceCol;

    /**
     * The text field for the parts search functionality
     */
    @FXML
    private TextField mainpartsSearch;

    /**
     * Table View for the Products table in the Main Screen
     */
    @FXML
    private TableView<Product> productsTableView;

    /**
     * The ID column for the Main Screen's Products Table
     */
    @FXML
    private TableColumn<Product, Integer> mainprodidCol;

    /**
     * The Inventory Level column for the Main Screen's Products Table
     */
    @FXML
    private TableColumn<Product, Integer> mainprodinvCol;

    /**
     * The Name column for the Main Screen's Products Table
     */
    @FXML
    private TableColumn<Product, String> mainprodnameCol;

    /**
     * The Price column for the Main Screen's Products Table
     */
    @FXML
    private TableColumn<Product, Double> mainprodpriceCol;

    /**
     * The text field for the products search functionality
     */
    @FXML
    private TextField mainproductsSearch;

    /**
     * The part object that is selected
     */
    private static Part part;

    /**
     * The product object that is selected
     */
    private static Product product;



    Stage stage;
    Parent scene;

    /**
     * @return the part object that is selected in the Parts Table
     */
    public static Part getPart() {
        return part;
    }

    /**
     * @return the product object that is selected in the Products Table
     */
    public static Product getProduct() {
        return product;
    }

    /**
     * Displays different alert messages based on specific cases
     *
     * @param alertType the variable for which case gets executed and has it's error message displayed
     */
    private void showAlert(int alertType) {

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        Alert alertError = new Alert(Alert.AlertType.ERROR);

        switch (alertType) {

            case 1:
                alertError.setTitle("Error");
                alertError.setHeaderText("A part needs to be selected");
                alertError.showAndWait();
                break;
            case 2:
                alertError.setTitle("Error");
                alertError.setHeaderText("A product needs to be selected");
                alertError.showAndWait();
                break;
            case 3:
                alert.setTitle("Information");
                alert.setHeaderText("Part was not found");
                alert.showAndWait();
                break;
            case 4:
                alert.setTitle("Information");
                alert.setHeaderText("Product was not found");
                alert.showAndWait();
                break;
            case 5:
                alertError.setTitle("Error");
                alertError.setHeaderText("Product Has Associated Parts");
                alertError.setContentText("All parts must be removed from product before deletion.");
                alertError.showAndWait();
                break;
        }
    }

    /**
     * Method for confirming if user really wants to delete or cancel
     *
     * @return true if OK button is clicked (action is confirmed) or false if cancel is chosen
     */
    public static boolean confirmAction(String title, String content){
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

    /**
     * Takes the user to the Add Part Screen
     *
     * @param event the event to be executed when the Add button is clicked below Parts Table
     */
    @FXML
    void onActionAddPart(ActionEvent event) throws IOException {

        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/AddPart.fxml"));
        stage.setTitle("Add Part");
        stage.setScene(new Scene(scene));
        stage.show();

    }

    /**
     * Takes the user to the Add Product Screen
     *
     * @param event the event to be executed when the Add button is clicked below Products Table
     */
    @FXML
    void onActionAddProduct(ActionEvent event) throws IOException {

        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/AddProduct.fxml"));
        stage.setTitle("Add Product");
        stage.setScene(new Scene(scene));
        stage.show();
    }

    /**
     * Deletes the selected part from the Parts Table
     * Confirmation is required to make sure it wasn't an accident
     *
     * @param event the event to be executed when the Delete button is clicked below the Parts Table
     */
    @FXML
    void onActionDeletePart(ActionEvent event) {
        if (partsTableView.getSelectionModel().isEmpty()){
            showAlert(1);
            return;
        }
        if (confirmAction("Warning!", "Are you sure you would like to delete this part?")){
            //int selectedPart = partsTableView.getSelectionModel().getSelectedIndex();
            Part selectedPart = partsTableView.getSelectionModel().getSelectedItem();
            Inventory.deletePart(selectedPart);
            //partsTableView.getItems().remove(selectedPart);
        }
    }

    /**
     * Deletes the selected product from the Products Table
     * Confirmation is required to make sure it wasn't an accident
     * Product with associated parts cannot be deleted
     *
     * @param event the event to be executed when the Delete button is clicked below the Products Table
     */
    @FXML
    void onActionDeleteProduct(ActionEvent event) {
        if (productsTableView.getSelectionModel().isEmpty()){
            showAlert(2);
            return;
        }
        Product productSelected = productsTableView.getSelectionModel().getSelectedItem();
        if (productSelected.getAllAssociatedParts().isEmpty() == false) {
            showAlert(5);
        }
        else{
        if (confirmAction("Warning!", "Would you like to delete this product?")) {
            Product selectedProduct = productsTableView.getSelectionModel().getSelectedItem();
            Inventory.deleteProduct(selectedProduct);
        }
        }
    }

    /**
     * Exits the program when Exit button is clicked
     *
     * @param event the event to be executed when the Exit button is clicked
     */
    @FXML
    void onActionExitProgram(ActionEvent event) {
        if (MainScreenController.confirmAction("Cancel?", "Are you sure you want to exit the program?")) {

            System.exit(0);
        }
    }
    /**
     * Takes user to Modify Part Screen
     * Error message if no part was selected
     *
     * @param event the event to be executed when the Modify button is clicked below the Parts Table
     */
    @FXML
    void onActionModifyPart(ActionEvent event) throws IOException {
        part = partsTableView.getSelectionModel().getSelectedItem();
        if(part == null){
            showAlert(1);
        }
        else {
            stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
            scene = FXMLLoader.load(getClass().getResource("/view/ModifyPart.fxml"));
            stage.setScene(new Scene(scene));
            stage.show();
        }
    }

    /**
     * Takes user to Modify Product Screen
     * Error message if no product was selected
     *
     * @param event the event to be executed when the Modify button is clicked below the Products Table
     */
    @FXML
    void onActionModifyProduct(ActionEvent event) throws IOException {
        product = productsTableView.getSelectionModel().getSelectedItem();
        if (product == null) {
            showAlert(2);
        } else {
            stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
            scene = FXMLLoader.load(getClass().getResource("/view/ModifyProduct.fxml"));
            stage.setScene(new Scene(scene));
            stage.show();

        }
    }

    /**
     * A search is executed to find parts with an id or name that matches the user's input
     * Name can be partial and it isn't case sensitive
     * Enter must be pressed for search to be executed
     * After parts are found, table will show only those matching parts
     * If no matching parts are found, an error message will be displayed
     *
     * @param event the event to be executed when the Part Table's Search Field is filled and entered
     */
    @FXML
    void searchPart(ActionEvent event) {
        String searchPartName = mainpartsSearch.getText();
        try {
            int searchPartId = Integer.parseInt(searchPartName);
            ObservableList<Part> idFound = FXCollections.observableArrayList();
            idFound.add(Inventory.lookupPart(searchPartId));
            partsTableView.setItems(idFound);
            if (Inventory.lookupPart(searchPartId) == null) {
                showAlert(3);
            }
        } catch (NumberFormatException nfe) {
            ObservableList<Part> namesFound = Inventory.lookupPart(searchPartName);
            partsTableView.setItems(namesFound);
            if (namesFound.isEmpty()) {
                showAlert(3);
            }
        }
    }

    /**
     * When parts search text field is cleared by user, the table is repopulated with all parts. User doesn't need to press enter
     *
     * @param event the event to be executed after a Part search was executed and the user clears the text in search field
     */
    @FXML
    void partSearchCleared(KeyEvent event) {
        if (mainpartsSearch.getText().isEmpty()) {
            partsTableView.setItems(Inventory.getAllParts());
        }
    }

    /**
     * A search is executed to find products with an id or name that matches the user's input
     * Name can be partial and it isn't case sensitive
     * Enter must be pressed for search to be executed
     * After products are found, table will show only those matching products
     * If no matching products are found, an error message will be displayed
     *
     * @param event the event to be executed when the Product Table's Search Field is filled and entered
     */
    @FXML
    void searchProduct(ActionEvent event) {
        String searchProductName = mainproductsSearch.getText();
        try {
            int searchProductId = Integer.parseInt(searchProductName);
            ObservableList<Product> idFound = FXCollections.observableArrayList();
            idFound.add(Inventory.lookupProduct(searchProductId));
            productsTableView.setItems(idFound);
            if (Inventory.lookupProduct(searchProductId) == null) {
                showAlert(4);
            }
        } catch (NumberFormatException nfe) {
            ObservableList<Product> namesFound = Inventory.lookupProduct(searchProductName);
            productsTableView.setItems(namesFound);
            if (namesFound.isEmpty()) {
                showAlert(4);
            }
        }
    }

    /**
     * When products search text field is cleared by user, the table is repopulated with all products. User doesn't need to press enter
     *
     * @param event the event to be executed after a Product search was executed and the user clears the text in search field
     */
    @FXML
    void productSearchCleared(KeyEvent event) {

        if (mainproductsSearch.getText().isEmpty()) {
            productsTableView.setItems(Inventory.getAllProducts());
        }
    }

    /**
     * Initializes Main Screen Controller after program is started
     * The Product and Part tables are populated with values
     *
     */
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