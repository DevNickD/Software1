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
import model.Part;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

/**
 * Controller class that adds functionality to the modify part screen.
 *
 * @author Nicholas Donnarumma
 */
public class ModifyPartController implements Initializable {

    /**
     * The part object that is selected.
     */
    private Part partSelected;

    /**
     * The label that gets swapped between machine ID and Company Name.
     */
    @FXML
    private Label labelswap;
    /**
     * The part id text field.
     */
    @FXML
    private TextField modpartidTxt;
    /**
     * The radio button for In House parts.
     */
    @FXML
    private RadioButton modpartinhouse;
    /**
     * The part inventory level text field.
     */
    @FXML
    private TextField modpartinvTxt;
    /**
     * The part machine ID text field.
     */
    @FXML
    private TextField modpartmachineidTxt;
    /**
     * The part maximum level text field.
     */
    @FXML
    private TextField modpartmaxTxt;
    /**
     * The part minimum level text field.
     */
    @FXML
    private TextField modpartminTxt;
    /**
     * The part name text field.
     */
    @FXML
    private TextField modpartnameTxt;
    /**
     * The radio button for Outsourced parts.
     */
    @FXML
    private RadioButton modpartoutsourced;
    /**
     * The part price text field.
     */
    @FXML
    private TextField modpartpriceTxt;

    Stage stage;
    Parent scene;

    /*public void setPart(Part partSelected) {
        this.partSelected = partSelected;
        partId = Inventory.getAllParts().indexOf(partSelected);
        ID.setText(Integer.toString(selectedPart.getPartID()));
        Name.setText(selectedPart.getName());
        Inventory.setText(Integer.toString(selectedPart.getStock()));
        Price.setText(Double.toString(selectedPart.getPartCost()));
        Maximum.setText(Integer.toString(selectedPart.getMax()));
        Minimum.setText(Integer.toString(selectedPart.getMin()));
        if(selectedPart instanceof InHouse){
            InHouse ih = (InHouse) selectedPart;
            inHouse.setSelected(true);
            this.inhouseoroutsourced.setText("Machine ID");
            companyORmachineID.setText(Integer.toString(ih.getMachineID()));
        }
        else{
            OutSourced os = (OutSourced) selectedPart;
            outsourced.setSelected(true);
            this.inhouseoroutsourced.setText("Company Name");
            companyORmachineID.setText(os.getCompanyName());
        }
    }*/
    /**
     * Displays confirmation screen to make sure user wants to cancel changes and go back to main screen.
     */
    @FXML
    void onActionCancelModifyForm(ActionEvent event) throws IOException {

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Cancel Alert");
        alert.setContentText("Are you sure you want to cancel your changes and return to the main screen?");
        Optional<ButtonType> result = alert.showAndWait();

        if (result.isPresent() && result.get() == ButtonType.OK) {
            stage = (Stage)((Button)event.getSource()).getScene().getWindow();
            scene = FXMLLoader.load(getClass().getResource("/view/MainScreen.fxml"));
            stage.setScene(new Scene(scene));
            stage.show();
        }
    }

    /**
     * Modifies part in inventory based on changes made then program returns to the Main Screen.
     * Error messages are displayed for incorrect inputs.
     * The fields are validated to prevent empty and/or invalid values.
     */
    @FXML
    void onActionSaveModifyForm(ActionEvent event) throws IOException {
        try {
            int id = partSelected.getId();
            String name = modpartnameTxt.getText();
            Double price = Double.parseDouble(modpartpriceTxt.getText());
            int stock = Integer.parseInt(modpartinvTxt.getText());
            int min = Integer.parseInt(modpartminTxt.getText());
            int max = Integer.parseInt(modpartmaxTxt.getText());
            int machineId;
            String companyName;
            boolean partAddedSuccessfully = false;

            if (minValidate(min, max) && inventoryValidate(min, max, stock)) {

                if (modpartinhouse.isSelected()) {
                    try {
                        machineId = Integer.parseInt(modpartmachineidTxt.getText());
                        InHouse newInHousePart = new InHouse(id, name, price, stock, min, max, machineId);
                        Inventory.addPart(newInHousePart);
                        partAddedSuccessfully = true;
                    } catch (Exception e) {
                        showAlert(2);
                    }
                }

                if (modpartoutsourced.isSelected()) {
                    companyName = modpartmachineidTxt.getText();
                    Outsourced newOutsourcedPart = new Outsourced(id, name, price, stock, min, max,
                            companyName);
                    Inventory.addPart(newOutsourcedPart);
                    partAddedSuccessfully = true;
                }

                if (partAddedSuccessfully) {
                    Inventory.deletePart(partSelected);

                    stage = (Stage) ((Button)event.getSource()).getScene().getWindow();
                    scene = FXMLLoader.load(getClass().getResource("/view/MainScreen.fxml"));
                    stage.setScene(new Scene(scene));
                    stage.show();
                }
            }
        }
        catch(Exception e) {
            showAlert(1);
        }
    }
    /**
     * Sets swappable label to "Machine ID".
     */
    @FXML
    void inHouseRadioButtonSelected(ActionEvent event) {
        if (modpartinhouse.isSelected()) {
            labelswap.setText("Machine ID");
        }
    }

    /**
     * Sets swappable label to "Company Name".
     */
    @FXML
    void outsourcedRadioButtonSelected(ActionEvent event){
        if (modpartoutsourced.isSelected()) {
            labelswap.setText("Company Name");
        }
    }

    /**
     * Displays different alert messages based on specific cases
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
                alert.setContentText("Machine ID must only contain numbers.");
                alert.showAndWait();
                break;
            case 3:
                alert.setTitle("Error");
                alert.setHeaderText("Invalid value for Min");
                alert.setContentText("Min must be less than Max and a number greater than 0.");
                alert.showAndWait();
                break;
            case 4:
                alert.setTitle("Error");
                alert.setHeaderText("Invalid value for Inventory");
                alert.setContentText("Inventory must be a number equal to or between Min and Max.");
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
     * Validates that min is a number greater than 0 and less than max.
     */
    private boolean minValidate(int min, int max) {

        boolean isValid = true;

        if (min <= 0 || min >= max) {
            isValid = false;
            showAlert(3);
        }

        return isValid;
    }

    /**
     * Validates that inventory level is equal to or between minimum and maximum inventory level.
     */
    private boolean inventoryValidate(int min, int max, int stock) {

        boolean isValid = true;

        if (stock < min || stock > max) {
            isValid = false;
            showAlert(4);
        }

        return isValid;
    }
        @Override
    public void initialize(URL url, ResourceBundle resourceBundle){

        System.out.println("I am initialized");
    }
}