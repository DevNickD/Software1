package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import model.InHouse;
import model.Inventory;
import model.Outsourced;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Controller class that adds functionality to the add part screen.
 *
 * @author Nicholas Donnarumma
 */

public class AddPartController implements Initializable {
    /**
     * The label that gets swapped between machine ID and Company Name.
     */
    @FXML
    private Label labelswap;
    /**
     * The radio button for In House parts.
     */
    @FXML
    private RadioButton addpartinhouse;
    /**
     * The radio button for Outsourced parts.
     */
    @FXML
    private RadioButton addpartoutsourced;
    /**
     * The part id text field.
     */
    @FXML
    private TextField partidTxt;
    /**
     * The part inventory level text field.
     */
    @FXML
    private TextField partinvTxt;
    /**
     * The part machine ID text field.
     */
    @FXML
    private TextField partmachineidTxt;
    /**
     * The part maximum level text field.
     */
    @FXML
    private TextField partmaxTxt;
    /**
     * The part minimum level text field.
     */
    @FXML
    private TextField partminTxt;
    /**
     * The part name text field.
     */
    @FXML
    private TextField partnameTxt;
    /**
     * The part price text field.
     */
    @FXML
    private TextField partpriceTxt;

    Stage stage;
    Parent scene;

    /**
     * Displays confirmation screen to make sure user wants to cancel and go back to main screen.
     */
    @FXML
    void onActionCancelForm(ActionEvent event) throws IOException {
        if(MainScreenController.confirmAction("Cancel?", "Are you sure you want to cancel adding a part and return to main screen?")) {
            stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
            scene = FXMLLoader.load(getClass().getResource("/view/MainScreen.fxml"));
            stage.setScene(new Scene(scene));
            stage.show();
        }
    }

    /**
     * Sets swappable label to "Machine ID".
     */
    @FXML
    void inHouseSelected(ActionEvent event) {
        if (addpartinhouse.isSelected()) {
            labelswap.setText("Machine ID");
        }
   }

    /**
     * Sets swappable label to "Company Name".
     */
    @FXML
    void outsourcedSelected(ActionEvent event){
            if (addpartoutsourced.isSelected()) {
                labelswap.setText("Company Name");
            }
        }
    /**
     * Displays different alert messages based on specific cases.
     */
    private void showAlert(int alertType) {

        Alert alert = new Alert(Alert.AlertType.ERROR);

        switch (alertType) {
            case 1:
                alert.setTitle("Error");
                alert.setHeaderText("There is an error adding the part");
                alert.setContentText("Form contains empty fields or invalid values.");
                alert.showAndWait();
                break;
            case 2:
                alert.setTitle("Error");
                alert.setHeaderText("Value for Machine ID is invalid");
                alert.setContentText("Machine ID must only contain numbers and cannot be left blank.");
                alert.showAndWait();
                break;
            case 3:
                alert.setTitle("Error");
                alert.setHeaderText("Invalid value for Inventory");
                alert.setContentText("Inventory must be a number equal to or between Min and Max.");
                alert.showAndWait();
                break;
            case 4:
                alert.setTitle("Error");
                alert.setHeaderText("Invalid value for Min");
                alert.setContentText("Min must be less than Max and a number greater than 0.");
                alert.showAndWait();
                break;
            case 5:
                alert.setTitle("Error");
                alert.setHeaderText("Name Is Empty");
                alert.setContentText("Name field cannot be empty.");
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
            showAlert(3);
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
            showAlert(4);
        }

        return isValid;
    }

    /**
     * Adds a new part to the inventory, that is either in-house or outsourced, then program goes back to Main Screen.
     * Error messages are displayed for incorrect inputs.
     * The fields are validated to prevent empty and/or invalid values.
     */
    @FXML
    void onActionSaveForm(ActionEvent event) throws IOException {

        try {
            int id = 0;
            String name = partnameTxt.getText();
            Double price = Double.parseDouble(partpriceTxt.getText());
            int stock = Integer.parseInt(partinvTxt.getText());
            int min = Integer.parseInt(partminTxt.getText());
            int max = Integer.parseInt(partmaxTxt.getText());
            int machineId;
            String companyName;
            boolean partAddSuccessful = false;

            if (name.isEmpty()) {
                showAlert(5);
            } else {
                if (minValidate(min, max) && inventoryValidate(min, max, stock)) {

                    if (addpartinhouse.isSelected()) {
                        try {
                            machineId = Integer.parseInt(partmachineidTxt.getText());
                            InHouse newInHousePart = new InHouse(id, name, price, stock, min, max, machineId);
                            newInHousePart.setId(Inventory.getNewPartId());
                            Inventory.addPart(newInHousePart);
                            partAddSuccessful = true;
                        } catch (Exception e) {
                            showAlert(2);
                        }
                    }

                    if (addpartoutsourced.isSelected()) {
                        companyName = partmachineidTxt.getText();
                        Outsourced newOutsourcedPart = new Outsourced(id, name, price, stock, min, max,
                                companyName);
                        newOutsourcedPart.setId(Inventory.getNewPartId());
                        Inventory.addPart(newOutsourcedPart);
                        partAddSuccessful = true;
                    }

                    if (partAddSuccessful) {
                        stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
                        scene = FXMLLoader.load(getClass().getResource("/view/MainScreen.fxml"));
                        stage.setScene(new Scene(scene));
                        stage.show();
                    }
                }
            }
        } catch(Exception e) {
            showAlert(1);
        }
    }

    /**
     * Initializes controller after user is taken to Add Part Screen.
     * Sets the radio button to be selecting in-house by default.
     */

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        addpartinhouse.setSelected(true);    }
}