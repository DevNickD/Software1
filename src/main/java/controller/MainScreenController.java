package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;
import java.net.URL;
import java.util.ResourceBundle;

public class MainScreenController implements Initializable {

    @FXML
    private TableColumn<?, ?> mainpartidCol;

    @FXML
    private TableColumn<?, ?> mainpartinvCol;

    @FXML
    private TableColumn<?, ?> mainpartnameCol;

    @FXML
    private TableColumn<?, ?> mainpartpriceCol;

    @FXML
    private TextField mainpartsSearch;

    @FXML
    private TableColumn<?, ?> mainprodidCol;

    @FXML
    private TableColumn<?, ?> mainprodinvCol;

    @FXML
    private TableColumn<?, ?> mainprodnameCol;

    @FXML
    private TableColumn<?, ?> mainprodpriceCol;

    @FXML
    private TextField mainproductsSearch;

    @FXML
    void onActionAddPart(ActionEvent event) {
        //System.out.println("Add Part Button Clicked");
    }

    @FXML
    void onActionAddProduct(ActionEvent event) {
        //System.out.println("Add Product Button Clicked");
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
        //System.out.println("Exit Button Clicked");
    }

    @FXML
    void onActionModifyPart(ActionEvent event) {
        //System.out.println("Modify Part Button Clicked");
    }

    @FXML
    void onActionModifyProduct(ActionEvent event) {
        //System.out.println("Modify Product Button Clicked");
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        System.out.println("I am initialized");
    }
}