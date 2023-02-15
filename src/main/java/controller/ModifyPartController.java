package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import model.*;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Controller class that adds functionality to the Modify Part Screen
 *
 * @author Nicholas Donnarumma
 */
public class ModifyPartController implements Initializable {

    /**
     * The label that gets swapped between machine ID and Company Name.
     */
    @FXML
    private Label labelswap;

    /**
     * The part id text field for user input.
     * I copy and pasted this from AddPartController instead of from the
     * sample controller skeleton in the ModifyPart.fxml file. This was
     * a mistake because I forgot to give the label this fx:id name and
     * this caused a logical error that I needed to diagnose.
     */
    @FXML
    private TextField modpartidTxt;

    /**
     * The radio button for In House parts.
     */
    @FXML
    private RadioButton modpartinhouse;

    /**
     * The part inventory level text field for user input.
     */
    @FXML
    private TextField modpartinvTxt;

    /**
     * The part machine ID text field for user input.
     */
    @FXML
    private TextField modpartmachineidTxt;

    /**
     * The part maximum level text field for user input.
     */
    @FXML
    private TextField modpartmaxTxt;

    /**
     * The part minimum level text field for user input.
     */
    @FXML
    private TextField modpartminTxt;

    /**
     * The part name text field for user input.
     */
    @FXML
    private TextField modpartnameTxt;

    /**
     * The radio button for Outsourced parts.
     */
    @FXML
    private RadioButton modpartoutsourced;

    /**
     * The part price text field for user input.
     */
    @FXML
    private TextField modpartpriceTxt;

    /**
     * The part object that is selected.
     */
    private Part partSelected;

    Stage stage;
    Parent scene;

    /**
     * Displays different alert messages based on specific cases
     *
     * @param alertType the variable for which case gets executed and has it's error message displayed
     */
    private void showAlert(int alertType) {

        Alert alert = new Alert(Alert.AlertType.ERROR);

        switch (alertType) {
            case 1:
                alert.setTitle("There is an error adding the part");
                alert.setHeaderText("Form cannot contain empty fields or invalid values.");
                alert.setContentText("Inventory, Max, Min and Machine ID must contain only whole numbers. Price must contain only whole numbers or a decimal number.");
                alert.showAndWait();
                break;
            case 2:
                alert.setTitle("Error");
                alert.setHeaderText("Value for Machine ID is invalid");
                alert.setContentText("Machine ID must only contain numbers and cannot be left blank");
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
     * Validates that inventory level is equal to or between minimum and maximum inventory level
     *
     * @return isValid/true if inventory level is between the minimum and maximum inventory level
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
     *
     * @return isValid/true if minimum inventory level is greater than 0 and less than max
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
     * Sets swappable label to "Machine ID".
     * RUNTIME ERROR
     * A third error was an error related to JavaFX  In the Modify Part Screen of the Application I
     * couldn't get the labels to swap based on radio button selection even though the logic seemed
     * correct for the method  I was confused for a while but the I decided to double check the fxml
     * file and noticed I never saved the action event modInHouseSelected in the ModifyPart.fxml file
     * The onAction field was just blank then it all made sense to me
     *
     * I also forgot to give the label a fx:id in ModifyPart/fxml because I thought I
     * added it after I gave the label in AddPart.fxml an fx:id.
     * Perhaps if I gave the label a different id name, such as modlabelswap instead of
     * labelswap, I would have noticed my mistake sooner.
     *
     * @param event the event to be executed when the inHouse radio button is selected
     */
    @FXML
    public void modInHouseSelected(ActionEvent event) {
        if (modpartinhouse.isSelected()) {
            labelswap.setText("Machine ID");
        }
    }

    /**
     * Sets swappable label to "Company Name".
     *
     * Label wouldn't change and I couldn't figure out why for some time. Then I realized
     * I never saved the action event modOutsourcedSelected in the ModifyPart.fxml file.
     *
     * @param event the event to be executed when the Outsourced radio button is selected
     */
    @FXML
    public void modOutsourcedSelected(ActionEvent event){
        if (modpartoutsourced.isSelected()) {
            labelswap.setText("Company Name");
        }
    }

    /**
     * Displays confirmation screen to make sure user wants to cancel changes and go back to main screen.
     *
     * @param event the event to be executed when the cancel button is clicked on Modify Part Screen
     */
    @FXML
    void onActionCancelModifyForm(ActionEvent event) throws IOException {

        if(MainScreenController.confirmAction("Cancel?", "Are you sure you want to cancel your changes and return to the main screen?")) {
            stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
            scene = FXMLLoader.load(getClass().getResource("/view/MainScreen.fxml"));
            stage.setScene(new Scene(scene));
            stage.show();
        }
    }

    /**
     * Modifies part in inventory based on changes made then program returns to the Main Screen.
     * Error messages are displayed for incorrect inputs.
     * The fields are validated to prevent empty and/or invalid values.
     * RUNTIME ERROR
     * One more error I remember having was that the name field is blank message appeared on all Screens
     * except the Modify Part Screen  I forgot to add a branch statement to implement that functionality
     * Even though the alert functionality existed in the alert method, I wasn't making use of it in the
     * onActionSave Method
     *
     * @param event the event that executes when the save button is clicked on the Modify Part Screen
     */
    @FXML
    void onActionSaveModifyForm(ActionEvent event) throws IOException {
        try {
            // Get the index of the selected Part
            int partIndex = Inventory.getAllParts().indexOf(partSelected);
            int id = partSelected.getId();
            String name = modpartnameTxt.getText();
            Double price = Double.parseDouble(modpartpriceTxt.getText());
            int stock = Integer.parseInt(modpartinvTxt.getText());
            int min = Integer.parseInt(modpartminTxt.getText());
            int max = Integer.parseInt(modpartmaxTxt.getText());
            int machineId;
            String companyName;
            boolean partAddedSuccessfully = false;

            if (name.isEmpty()) {
                showAlert(5);
            } else {
                if (minValidate(min, max) && inventoryValidate(min, max, stock)) {

                    if (modpartinhouse.isSelected()) {
                        try {
                            machineId = Integer.parseInt(modpartmachineidTxt.getText());
                            InHouse newInHousePart = new InHouse(0, "blank", 5, 5, 5, 5, 5);
                            newInHousePart.setId(id);
                            newInHousePart.setName(name);
                            newInHousePart.setPrice(price);
                            newInHousePart.setStock(stock);
                            newInHousePart.setMin(min);
                            newInHousePart.setMax(max);
                            newInHousePart.setMachineId(machineId);
                            Inventory.updatePart(partIndex, newInHousePart);
                            partAddedSuccessfully = true;
                        } catch (Exception e) {
                            showAlert(2);
                        }
                    }

                    if (modpartoutsourced.isSelected()) {
                        companyName = modpartmachineidTxt.getText();
                        Outsourced newOutsourcedPart = new Outsourced(5, "blank", 5, 5, 5, 5, "name");
                        newOutsourcedPart.setId(id);
                        newOutsourcedPart.setName(name);
                        newOutsourcedPart.setPrice(price);
                        newOutsourcedPart.setStock(stock);
                        newOutsourcedPart.setMin(min);
                        newOutsourcedPart.setMax(max);
                        newOutsourcedPart.setCompanyName(companyName);
                        Inventory.updatePart(partIndex, newOutsourcedPart);
                        partAddedSuccessfully = true;
                    }

                    if (partAddedSuccessfully) {

                        stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
                        scene = FXMLLoader.load(getClass().getResource("/view/MainScreen.fxml"));
                        stage.setScene(new Scene(scene));
                        stage.show();
                    }
                }
            }
        }
        catch(Exception e) {
            showAlert(1);
        }
    }


    /**
     * Initializes controller after user is taken to Modify Part Screen.
     * Text fields are populated with the part selected in the Main Screen.
     */
        @Override
    public void initialize(URL url, ResourceBundle resourceBundle){
            partSelected = MainScreenController.getPart();

            if (partSelected instanceof InHouse) {
                modpartinhouse.setSelected(true);
                labelswap.setText("Machine ID");
                modpartmachineidTxt.setText(String.valueOf(((InHouse) partSelected).getMachineId()));
            }

            if (partSelected instanceof Outsourced){
                modpartoutsourced.setSelected(true);
                labelswap.setText("Company Name");
                modpartmachineidTxt.setText(((Outsourced) partSelected).getCompanyName());
            }

            modpartidTxt.setText(String.valueOf(partSelected.getId()));
            modpartnameTxt.setText(partSelected.getName());
            modpartinvTxt.setText(String.valueOf(partSelected.getStock()));
            modpartpriceTxt.setText(String.valueOf(partSelected.getPrice()));
            modpartmaxTxt.setText(String.valueOf(partSelected.getMax()));
            modpartminTxt.setText(String.valueOf(partSelected.getMin()));
        }
}
