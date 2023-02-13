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
 * Controller class that adds functionality to the Modify Product Screen.
 *
 * @author Nicholas Donnarumma
 */
public class ModifyProductController implements Initializable {
    /**
     * The text field for the parts search functionality.
     */
    @FXML
    private TextField modprodSearch;
    /**
     * Table View for the Products table in the Modify Product Screen.
     */
    @FXML
    private TableView<Part> modprodaddTableView;
    /**
     * The Inventory Level column for the Modify Product Screen's Products Table.
     */
    @FXML
    private TableColumn<Part, Integer> modprodaddinvCol;
    /**
     * The ID column for the Add Product Screen's Products Table.
     */
    @FXML
    private TableColumn<Part, Integer> modprodaddpartidCol;
    /**
     * The Name column for the Add Product Screen's Products Table.
     */
    @FXML
    private TableColumn<Part, String> modprodaddpartnameCol;
    /**
     * The Price column for the Add Product Screen's Products Table.
     */
    @FXML
    private TableColumn<Part, Double> modprodaddpriceCol;
    /**
     * The product ID text field for user input.
     */
    @FXML
    private TextField modprodidTxt;
    /**
     * The product Inventory Level text field for user input.
     */
    @FXML
    private TextField modprodinvTxt;
    /**
     * The product Max text field for user input.
     */
    @FXML
    private TextField modprodmaxTxt;
    /**
     * The product Min text field for user input.
     */
    @FXML
    private TextField modprodminTxt;
    /**
     * The product Name text field for user input.
     */
    @FXML
    private TextField modprodnameTxt;
    /**
     * The product Price text field for user input.
     */
    @FXML
    private TextField modprodpriceTxt;
    /**
     * Table View for the Associated Parts table in the Modify Product Screen.
     */
    @FXML
    private TableView<Part> modprodremoveTableView;
    /**
     * The Inventory Level column for the Modify Product Screen's Associated Parts Table.
     */
    @FXML
    private TableColumn<Part, Integer> modprodremoveinvCol;
    /**
     * The ID column for the Modify Product Screen's Associated Parts Table.
     */
    @FXML
    private TableColumn<Part, Integer> modprodremovepartidCol;
    /**
     * The Name column for the Modify Product Screen's Associated Parts Table.
     */
    @FXML
    private TableColumn<Part, String> modprodremovepartnameCol;
    /**
     * The Price column for the Modify Product Screen's Associated Parts Table.
     */
    @FXML
    private TableColumn<Part, Double> modprodremovepriceCol;
    /**
     * A list containing the product's Associated Parts.
     */
    private ObservableList<Part> associatedParts = FXCollections.observableArrayList();
    /**
     * The product object selected in the MainScreenController.
     */
    private Product productSelected;

    Stage stage;
    Parent scene;

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
        String searchPartInput = modprodSearch.getText();

        for (Part part : allParts) {
            if (String.valueOf(part.getId()).contains(searchPartInput) ||
                    part.getName().contains(searchPartInput)) {
                partsFound.add(part);
            }
        }

        modprodaddTableView.setItems(partsFound);

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
        if (modprodSearch.getText().isEmpty()) {
            modprodaddTableView.setItems(Inventory.getAllParts());
        }
    }


    /**
     * Copies selected part object, from Add Product top table, and adds
     * it to the bottom associated parts table.
     */
    @FXML
    void onActionAddPart(ActionEvent event) {
        Part partSelected = modprodaddTableView.getSelectionModel().getSelectedItem();

        if (partSelected == null) {
            showAlert(6);
       } else {
            associatedParts.add(partSelected);
            modprodremoveTableView.setItems(associatedParts);
        }
    }

    /**
     * Displays confirmation alert to make sure user wants to cancel and go back to Main Screen.
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
     * Deletes the selected part from the Associated Parts Table.
     * Confirmation is required to make sure it wasn't an accident.
     */
    @FXML
    void onActionRemovePart(ActionEvent event) {

        if (modprodremoveTableView.getSelectionModel().isEmpty()) {
            showAlert(5);
        } else {
            if (MainScreenController.confirmAction("Warning!", "Are you sure you would like to delete this part?")) {
                int selectedPart = modprodremoveTableView.getSelectionModel().getSelectedIndex();
                modprodremoveTableView.getItems().remove(selectedPart);
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
        /*
        int productIndex = Inventory.getAllProducts().
                indexOf(productSelected);

        // Gathering form data
        String productName = modprodnameTxt.getText();
        int productID = Integer.parseInt(modprodidTxt.getText());
        int productInventory = Integer.parseInt(modprodinvTxt.getText());
        double productPrice = Double.parseDouble(modprodpriceTxt.getText());
        int productMax = Integer.parseInt(modprodmaxTxt.getText());
        int productMin = Integer.parseInt(modprodminTxt.getText());
        // How many Parts are associated with this Product?
        int associatedPartArraySize = productSelected.getAllAssociatedParts().size();
        // No Associated Parts found for this Product
        if(associatedPartArraySize == 0) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("No Associated Part Found");
            alert.setHeaderText("Products must have Parts.");
            alert.setContentText(
                    "Please Add a Part to this Product before Saving");
            alert.show();
        }
        else {
            // Setting the new Product values
            productSelected.setId(productID);
            productSelected.setName(productName);
            productSelected.setStock(productInventory);
            productSelected.setPrice(productPrice);
            productSelected.setMax(productMax);
            productSelected.setMin(productMin);


            stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
            scene = FXMLLoader.load(getClass().getResource("/view/MainScreen.fxml"));
            stage.setScene(new Scene(scene));
            stage.show();
        }
        */

        try {

            int id = productSelected.getId();
           // int id = Integer.parseInt(modprodidTxt.getText());
            String name = modprodnameTxt.getText();
            Double price = Double.parseDouble(modprodpriceTxt.getText());
            int stock = Integer.parseInt(modprodinvTxt.getText());
            int min = Integer.parseInt(modprodminTxt.getText());
            int max = Integer.parseInt(modprodmaxTxt.getText());


            if (name.isEmpty()) {
                showAlert(7);
            } else {
                if (minValidate(min, max) && inventoryValidate(min, max, stock)) {

                    Product newProduct = new Product(id, name, price, stock, min, max);

                    for (Part associatedPart : associatedParts) {
                        newProduct.addAssociatedPart(associatedPart);
                    }

                    Inventory.addProduct(newProduct);
                    Inventory.deleteProduct(productSelected);
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

        //Product product = Inventory.getAllProducts().get(productIndex);
        //productID = getProductInventory().get(productIndex).getProductID();

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