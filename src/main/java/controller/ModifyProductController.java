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
import java.util.ResourceBundle;

/**
 * Controller class that adds functionality to the Modify Product Screen
 *
 * @author Nicholas Donnarumma
 */
public class ModifyProductController implements Initializable {

    /**
     * The text field for the parts search functionality
     */
    @FXML
    private TextField modproductSearch;

    /**
     * Table View for the Products table in the Modify Product Screen
     */
    @FXML
    private TableView<Part> modprodaddTableView;

    /**
     * The Inventory Level column for the Modify Product Screen's Products Table
     */
    @FXML
    private TableColumn<Part, Integer> modprodaddinvCol;

    /**
     * The ID column for the Add Product Screen's Products Table
     */
    @FXML
    private TableColumn<Part, Integer> modprodaddpartidCol;

    /**
     * The Name column for the Add Product Screen's Products Table
     */
    @FXML
    private TableColumn<Part, String> modprodaddpartnameCol;

    /**
     * The Price column for the Add Product Screen's Products Table
     */
    @FXML
    private TableColumn<Part, Double> modprodaddpriceCol;

    /**
     * The product ID text field for user input
     */
    @FXML
    private TextField modprodidTxt;

    /**
     * The product Inventory Level text field for user input
     */
    @FXML
    private TextField modprodinvTxt;

    /**
     * The product Max text field for user input
     */
    @FXML
    private TextField modprodmaxTxt;

    /**
     * The product Min text field for user input
     */
    @FXML
    private TextField modprodminTxt;

    /**
     * The product Name text field for user input
     */
    @FXML
    private TextField modprodnameTxt;

    /**
     * The product Price text field for user input
     */
    @FXML
    private TextField modprodpriceTxt;

    /**
     * Table View for the Associated Parts table in the Modify Product Screen
     */
    @FXML
    private TableView<Part> modprodremoveTableView;

    /**
     * The Inventory Level column for the Modify Product Screen's Associated Parts Table
     */
    @FXML
    private TableColumn<Part, Integer> modprodremoveinvCol;

    /**
     * The ID column for the Modify Product Screen's Associated Parts Table
     */
    @FXML
    private TableColumn<Part, Integer> modprodremovepartidCol;

    /**
     * The Name column for the Modify Product Screen's Associated Parts Table
     */
    @FXML
    private TableColumn<Part, String> modprodremovepartnameCol;

    /**
     * The Price column for the Modify Product Screen's Associated Parts Table
     */
    @FXML
    private TableColumn<Part, Double> modprodremovepriceCol;

    /**
     * The product object selected in the MainScreenController
     */
    private Product productSelected;

    Stage stage;
    Parent scene;

    /**
     * Displays different alert messages based on specific cases
     * RUNTIME ERROR
     * Error from a simple mistake occurred when I had a missing break statement from case 4 in
     * the Modify Product Controller  This caused a second alert messages to display after the first one
     * which wasn't supposed to happen
     *
     * @param alertType the variable for which case gets executed and has it's error message displayed
     */
    private void showAlert(int alertType) {

        Alert alert = new Alert(Alert.AlertType.ERROR);
        Alert alertInfo = new Alert(Alert.AlertType.INFORMATION);

        switch (alertType) {
            case 1:
                alert.setTitle("Error");
                alert.setHeaderText("Invalid value for Min");
                alert.setContentText("Min must be less than Max and a number greater than 0.");
                alert.showAndWait();
                break;
            case 2:
                alert.setTitle("Error");
                alert.setHeaderText("Invalid value for Inventory");
                alert.setContentText("Inventory must be a number equal to or between Min and Max");
                alert.showAndWait();
                break;
            case 3:
                alert.setTitle("Error Trying to Add Product");
                alert.setHeaderText("Form cannot contain blank fields or invalid values.");
                alert.setContentText("Inventory, Max, Min must contain only whole numbers. Price must contain only whole numbers or a decimal number.");
                alert.showAndWait();
                break;
            case 4:
                alertInfo.setTitle("Information");
                alertInfo.setHeaderText("Part was not found");
                alertInfo.showAndWait();
                break;
            case 5:
                alert.setTitle("Error");
                alert.setHeaderText("An associated part needs to be selected");
                alert.showAndWait();
                break;
            case 6:
                alert.setTitle("Error");
                alert.setHeaderText("A part needs to be selected");
                alert.showAndWait();
                break;
            case 7:
                alert.setTitle("Error");
                alert.setHeaderText("Name Empty");
                alert.setContentText("Name cannot be empty.");
                alert.showAndWait();
                break;
        }
    }

    /**
     * Validates that inventory level is equal to or between minimum and maximum inventory level
     *
     * @return isValid/true if inventory level is between the minimum and maximum inventory level
     */
    private boolean inventoryValidate(int min, int max, int stock) {

        boolean isValid = true;

        if (stock < min || stock > max) {
            isValid = false;
            showAlert(2);
        }

        return isValid;
    }

    /**
     * Validates that min is a number greater than 0 and less than max
     *
     * @return isValid/true if minimum inventory level is greater than 0 and less than max
     */
    private boolean minValidate(int min, int max) {

        boolean isValid = true;

        if (min <= 0 || min >= max) {
            isValid = false;
            showAlert(1);
        }

        return isValid;
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
        String searchPartName = modproductSearch.getText();
        try {
            int searchPartId = Integer.parseInt(searchPartName);
            ObservableList<Part> idFound = FXCollections.observableArrayList();
            idFound.add(Inventory.lookupPart(searchPartId));
            modprodaddTableView.setItems(idFound);
            if (Inventory.lookupPart(searchPartId) == null) {
                showAlert(4);
            }
        } catch (NumberFormatException nfe) {
            ObservableList<Part> namesFound = Inventory.lookupPart(searchPartName);
            modprodaddTableView.setItems(namesFound);
            if (namesFound.isEmpty()) {
                showAlert(4);
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
        if (modproductSearch.getText().isEmpty()) {
            modprodaddTableView.setItems(Inventory.getAllParts());
        }
    }


    /**
     * Copies selected part object, from Add Product top table, and adds it to the bottom associated parts table
     * Displays error message if no part is selected
     * RUNTIME ERROR
     * Some Errors occurred during the project  One error was that associated parts were able to be
     * added to the bottom table in the Add Product and Modify Product Screens  However, after saving
     * and returning to the product to modify, the associated parts weren't showing up  I realized my
     * code needed to be changed because I was just adding parts to the tableview but there was no code
     * implemented for the product itself to add the associated parts to it's Observable List
     *
     * @param event the event to be executed when the Add button is clicked on the Modify Product Screen
     */
    @FXML
    void onActionAddPart(ActionEvent event) {
        Part partSelected = modprodaddTableView.getSelectionModel().getSelectedItem();

        if (partSelected == null) {
            showAlert(6);
       } else {
            productSelected.addAssociatedPart(partSelected);
        }
    }

    /**
     * Displays confirmation alert to make sure user wants to cancel and go back to Main Screen
     *
     * @param event the event to be executed when the Cancel button is clicked on the Modify Product Screen
     */
    @FXML
    void onActionCancel(ActionEvent event) throws IOException {
        if (MainScreenController.confirmAction("Cancel?", "Are you sure you want to cancel adding a product and return to main screen?")) {
            stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
            scene = FXMLLoader.load(getClass().getResource("/view/MainScreen.fxml"));
            stage.setScene(new Scene(scene));
            stage.show();
        }
    }

    /**
     * Deletes the selected part from the Associated Parts Table
     * Confirmation is required to make sure it wasn't an accident
     *
     * @param event the event to be executed when the Remove button is clicked on the Modify Product Screen
     */
    @FXML
    void onActionRemovePart(ActionEvent event) {

        if (modprodremoveTableView.getSelectionModel().isEmpty()) {
            showAlert(5);
        } else {
            if (MainScreenController.confirmAction("Warning!", "Are you sure you would like to delete this part?")) {
                Part selectedAssociatedPart = modprodremoveTableView.getSelectionModel().getSelectedItem();
                productSelected.deleteAssociatedPart(selectedAssociatedPart);

            }
        }

    }

    /**
     * Adds a new product to the inventory then program goes back to Main Screen
     * Error messages are displayed for incorrect inputs
     * The fields are validated to prevent empty and/or invalid values
     * RUNTIME ERROR
     * Some Errors occurred during the project. One error was that associated parts were able to be
     * added to the bottom table in the Add Product and Modify Product Screens  However, after saving
     * and returning to the product to modify, the associated parts weren't showing up  I realized my
     * code needed to be changed because I was just adding parts to the tableview but there was no code
     * implemented for the product itself to add the associated parts to it's Observable List
     *
     * @param event the event to be executed when the Save button is clicked on the Modify Product Screen
     */
    @FXML
    void onActionSave(ActionEvent event) throws IOException {
        try {
            int productIndex = Inventory.getAllProducts().indexOf(productSelected);
            int id = productSelected.getId();
            String name = modprodnameTxt.getText();
            Double price = Double.parseDouble(modprodpriceTxt.getText());
            int stock = Integer.parseInt(modprodinvTxt.getText());
            int min = Integer.parseInt(modprodminTxt.getText());
            int max = Integer.parseInt(modprodmaxTxt.getText());


            if (name.isEmpty()) {
                showAlert(7);
            } else {
                if (minValidate(min, max) && inventoryValidate(min, max, stock)) {

                    Product newProduct = new Product(1, "blank", 1, 1, 1, 1);
                    newProduct.setId(id);
                    newProduct.setName(name);
                    newProduct.setPrice(price);
                    newProduct.setStock(stock);
                    newProduct.setMin(min);
                    newProduct.setMax(max);

                    for (Part associatedPart : productSelected.getAllAssociatedParts()) {
                        newProduct.addAssociatedPart(associatedPart);
                   }

                    Inventory.updateProduct(productIndex, newProduct);
                    stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
                    scene = FXMLLoader.load(getClass().getResource("/view/MainScreen.fxml"));
                    stage.setScene(new Scene(scene));
                    stage.show();
                }
            }
        } catch (Exception e){
            showAlert(3);
        }
    }

    /**
     * Initializes Modify Product Screen Controller.
     * The text fields are populated based on product selected in Main Screen.
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        productSelected = MainScreenController.getProduct();

        modprodaddpartidCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        modprodaddpartnameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        modprodaddinvCol.setCellValueFactory(new PropertyValueFactory<>("stock"));
        modprodaddpriceCol.setCellValueFactory(new PropertyValueFactory<>("price"));
        modprodaddTableView.setItems(Inventory.getAllParts());

        modprodremovepartidCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        modprodremovepartnameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        modprodremoveinvCol.setCellValueFactory(new PropertyValueFactory<>("stock"));
        modprodremovepriceCol.setCellValueFactory(new PropertyValueFactory<>("price"));
        modprodremoveTableView.setItems(productSelected.getAllAssociatedParts());

        modprodidTxt.setText(String.valueOf(productSelected.getId()));
        modprodnameTxt.setText(productSelected.getName());
        modprodinvTxt.setText(String.valueOf(productSelected.getStock()));
        modprodpriceTxt.setText(String.valueOf(productSelected.getPrice()));
        modprodmaxTxt.setText(String.valueOf(productSelected.getMax()));
        modprodminTxt.setText(String.valueOf(productSelected.getMin()));
    }
}