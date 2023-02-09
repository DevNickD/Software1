package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Inventory;
import model.Part;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ModifyPartController implements Initializable {

    @FXML
    private TextField modpartidTxt;

    @FXML
    private RadioButton modpartinhouse;

    @FXML
    private TextField modpartinvTxt;

    @FXML
    private TextField modpartmachineidTxt;

    @FXML
    private TextField modpartmaxTxt;

    @FXML
    private TextField modpartminTxt;

    @FXML
    private TextField modpartnameTxt;

    @FXML
    private RadioButton modpartoutsourced;

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

    @FXML
    void onActionCancelModifyForm(ActionEvent event) throws IOException {

        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/MainScreen.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    @FXML
    void onActionSaveModifyForm(ActionEvent event) throws IOException {

        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/MainScreen.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }




    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        System.out.println("I am initialized");
    }
}