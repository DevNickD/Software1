package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import model.Inventory;
import model.Part;
import javafx.scene.control.*;
import model.Product;


import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Controller class that adds functionality to the Add Product Screen.
 *
 * @author Nicholas Donnarumma
 */
public class AddProductController implements Initializable {
    /**
     * Table View for the Products table in the Add Product Screen.
     */
    @FXML
    private TableView<Part> productTableView;
    /**
     * The Inventory Level column for the Add Product Screen's Products Table.
     */
    @FXML
    private TableColumn<Part, Integer> addinvlevelCol;
    /**
     * The ID column for the Add Product Screen's Products Table.
     */
    @FXML
    private TableColumn<Part, Integer> addpartidCol;
    /**
     * The Name column for the Add Product Screen's Products Table.
     */
    @FXML
    private TableColumn<Part, String> addpartnameCol;
    /**
     * The Price column for the Add Product Screen's Products Table.
     */
    @FXML
    private TableColumn<Part, Double> addpriceCol;
    /**
     * The text field for the parts search functionality.
     */
    @FXML
    private TextField addproductSearch;
    /**
     * The product ID text field for user input.
     */
    @FXML
    private TextField addproductidTxt;
    /**
     * The product Inventory Level text field for user input.
     */
    @FXML
    private TextField addproductinvTxt;
    /**
     * The product Max text field for user input.
     */
    @FXML
    private TextField addproductmaxTxt;
    /**
     * The product Min text field for user input.
     */
    @FXML
    private TextField addproductminTxt;
    /**
     * The product Name text field for user input.
     */
    @FXML
    private TextField addproductnameTxt;
    /**
     * The product Price text field for user input.
     */
    @FXML
    private TextField addproductpriceTxt;
    /**
     * Table View for the Associated Parts table in the Add Product Screen.
     */
    @FXML
    private TableView<Part> associatedPartTableView;
    /**
     * The Inventory Level column for the Add Product Screen's Associated Parts Table.
     */
    @FXML
    private TableColumn<Part, Integer> removeinvlevelCol;
    /**
     * The ID column for the Add Product Screen's Associated Parts Table.
     */
    @FXML
    private TableColumn<Part, Integer> removepartidCol;
    /**
     * The Name column for the Add Product Screen's Associated Parts Table.
     */
    @FXML
    private TableColumn<Part, String> removepartnameCol;
    /**
     * The Price column for the Add Product Screen's Associated Parts Table.
     */
    @FXML
    private TableColumn<Part, Double> removepriceCol;
    /**
     * A list containing the product's Associated Parts.
     */
    private ObservableList<Part> associatedParts = FXCollections.observableArrayList();

    Stage stage;
    Parent scene;

    /**
     * Copies selected part object, from Add Product top table, and adds
     * it to the bottom associated parts table.
     */
    @FXML
    void onActionAddPart(ActionEvent event) {
        Part partSelected = productTableView.getSelectionModel().getSelectedItem();

        if (partSelected == null) {
            showAlert(6);
        } else {
            associatedParts.add(partSelected);
            associatedPartTableView.setItems(associatedParts);
        }
    }
    /**
     * Displays different alert messages based on specific cases.
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
                alert.setTitle("Error");
                alert.setHeaderText("Error Trying to Add Product");
                alert.setContentText("Form cannot contain blank fields or invalid values.");
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
     * Validates that inventory level is equal to or between minimum and maximum inventory level.
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
     * Validates that min is a number greater than 0 and less than max.
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
     * Adds part object selected in the all parts table to the associated parts table.
     *
     * Displays error message if no part is selected.
     *
     * @param event Add button action.
     */

    /**
     * A search is executed to find parts with an id or name that matches the user's input.
     * Name can be partial and it is case sensitive.
     * Enter must be pressed for search to be executed.
     * After parts are found, table will show only those matching parts.
     * If no matching parts are found, an error message will be displayed.
     */
    @FXML
    void searchPart(ActionEvent event) {

        ObservableList<Part> allParts = Inventory.getAllParts();
        ObservableList<Part> partsFound = FXCollections.observableArrayList();
        String searchPartInput = addproductSearch.getText();

        for (Part part : allParts) {
            if (String.valueOf(part.getId()).contains(searchPartInput) ||
                    part.getName().contains(searchPartInput)) {
                partsFound.add(part);
            }
        }

        productTableView.setItems(partsFound);

        if (partsFound.size() == 0) {
            showAlert(3);
        }
    }

    /**
     * When parts search text field is cleared by user, the table is
     * repopulated with all parts. User doesn't need to press enter.
     */
    @FXML
    void partSearchCleared(KeyEvent event) {
        if (addproductSearch.getText().isEmpty()) {
            productTableView.setItems(Inventory.getAllParts());
        }
    }

    /**
     * Displays confirmation alert to make sure user wants to cancel and go back to Main Screen.
     */
    @FXML
    void onActionCancel(ActionEvent event) throws IOException {
        if(MainScreenController.confirmAction("Cancel?", "Are you sure you want to cancel adding a product and return to main screen?")) {
            stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
            scene = FXMLLoader.load(getClass().getResource("/view/MainScreen.fxml"));
            stage.setScene(new Scene(scene));
            stage.show();
        }
    }
    /**
     * Deletes the selected part from the Associated Parts Table.
     * Confirmation is required to make sure it wasn't an accident.
     */
    @FXML
    void onActionRemovePart(ActionEvent event) {

        if (associatedPartTableView.getSelectionModel().isEmpty()) {
            showAlert(5);
        }
        else {
            if (MainScreenController.confirmAction("Warning!", "Are you sure you would like to delete this part?")){
                int selectedPart = associatedPartTableView.getSelectionModel().getSelectedIndex();
                associatedPartTableView.getItems().remove(selectedPart);
            }
        }
    }
    /**
     * Adds a new product to the inventory then program goes back to Main Screen.
     * Error messages are displayed for incorrect inputs.
     * The fields are validated to prevent empty and/or invalid values.
     */
    @FXML
    void onActionSave(ActionEvent event) throws IOException {
        try {
            int id = 1;
            String name = addproductnameTxt.getText();
            Double price = Double.parseDouble(addproductpriceTxt.getText());
            int stock = Integer.parseInt(addproductinvTxt.getText());
            int min = Integer.parseInt(addproductminTxt.getText());
            int max = Integer.parseInt(addproductmaxTxt.getText());

            if (name.isEmpty()) {
                showAlert(7);
            }
            else {
                if (minValidate(min, max) && inventoryValidate(min, max, stock)) {

                    Product newProduct = new Product(id, name, price, stock, min, max);

                    for (Part part : associatedParts) {
                        newProduct.addAssociatedPart(part);
                    }

                    newProduct.setId(Inventory.getNewProductId());
                    Inventory.addProduct(newProduct);
                    stage = (Stage)((Button)event.getSource()).getScene().getWindow();
                    scene = FXMLLoader.load(getClass().getResource("/view/MainScreen.fxml"));
                    stage.setScene(new Scene(scene));
                    stage.show();
                }
            }
        }
        catch (Exception e){
            showAlert(3);
        }
    }

    /**
     * Initializes Add Product Screen Controller.
     * The Product and Associated Part Tables are populated with values.
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        //Table and Columns parts that aren't associated
        productTableView.setItems(Inventory.getAllParts());
        addpartidCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        addpartnameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        addinvlevelCol.setCellValueFactory(new PropertyValueFactory<>("stock"));
        addpriceCol.setCellValueFactory(new PropertyValueFactory<>("price"));

        //Table and Columns for associated parts
        removepartidCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        removepartnameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        removeinvlevelCol.setCellValueFactory(new PropertyValueFactory<>("stock"));
        removepriceCol.setCellValueFactory(new PropertyValueFactory<>("price"));

    }
}