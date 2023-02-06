package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainScreenController implements Initializable {

    Stage stage;
    Parent scene;

    @FXML
    void onActionAddPart(ActionEvent event) throws IOException {

        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/AddPart.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();

    }

    @FXML
    void onActionAddProduct(ActionEvent event) throws IOException {

        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/AddProduct.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
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
        System.exit(0);
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


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        System.out.println("I am initialized");
    }
}