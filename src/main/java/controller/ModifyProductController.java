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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.Inventory;
import model.Part;
import model.Product;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Controller class that adds functionality to the modify product screen.
 *
 * @author Nicholas Donnarumma
 */
public class ModifyProductController implements Initializable {

    @FXML
    private TableView<Part> modprodaddTableView;

    @FXML
    private TableColumn<Part, Integer> modprodaddinvCol;

    @FXML
    private TableColumn<Part, Integer> modprodaddpartidCol;

    @FXML
    private TableColumn<Part, String> modprodaddpartnameCol;

    @FXML
    private TableColumn<Part, Double> modprodaddpriceCol;

    @FXML
    private TextField modprodidTxt;

    @FXML
    private TextField modprodinvTxt;

    @FXML
    private TextField modprodmaxTxt;

    @FXML
    private TextField modprodminTxt;

    @FXML
    private TextField modprodnameTxt;

    @FXML
    private TextField modprodpriceTxt;

    @FXML
    private TableView<Part> modprodremoveTableView;

    @FXML
    private TableColumn<?, ?> modprodremoveinvCol;

    @FXML
    private TableColumn<?, ?> modprodremovepartidCol;

    @FXML
    private TableColumn<?, ?> modprodremovepartnameCol;

    @FXML
    private TableColumn<?, ?> modprodremovepriceCol;

    Stage stage;
    Parent scene;

    public boolean update(int id, Product product){
        int index = -1;
        for(Product productSearched : Inventory.getAllProducts()){
            index++;
            if(productSearched.getId() == id){
                Inventory.getAllProducts().set(index, product);
                return true;
            }
        }
        return false;
    }

    public boolean delete(int id){
        for(Part part : Inventory.getAllParts()){
            if (part.getId() == id){
                return Inventory.getAllParts().remove(part);
            }
        }
        return false;
    }
    @FXML
    void onActionAddPart(ActionEvent event) {

    }

    @FXML
    void onActionCancel(ActionEvent event) throws IOException {
        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/MainScreen.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    @FXML
    void onActionRemovePart(ActionEvent event) {


    }

    @FXML
    void onActionSave(ActionEvent event) throws IOException {
        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/MainScreen.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    @FXML
    private TextField modprodSearch;
    /*
    private ObservableList<Part> associatedParts = FXCollections.observableArrayList();
    private ObservableList<Part> originalParts = FXCollections.observableArrayList();

    @FXML public void searchPartButton(ActionEvent event) {
        String term = modprodSearch.getText();
        ObservableList foundParts = Inventory.lookupPart(term);
        if(foundParts.isEmpty()) {
            MainScreenController.confirmDialog("No Match", "Unable to locate a Part name with: " + term);
        } else {
            modprodaddTableView.setItems(foundParts);
        }
    }
*/



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
       /*
       if (search(2){
           System.out.println("No Match!");
       }
       else{
            System.out.println("No Match!");
       }

       if(update(1, new Product(1, "PS5", 500.00, 11, 2 , 60))){
            System.out.println("System successful");
        }
        else{
            System.out.println("Update failed");
        }


        if(delete(3)){
            System.out.println("Deleted!");
        }
        else{
            System.out.println("No Match!");
        }
    */

        //Table and Columns parts that aren't associated
        modprodaddTableView.setItems(Inventory.getAllParts());
        modprodaddpartidCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        modprodaddpartnameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        modprodaddinvCol.setCellValueFactory(new PropertyValueFactory<>("stock"));
        modprodaddpriceCol.setCellValueFactory(new PropertyValueFactory<>("price"));

        //Table and Columns for associated parts
        modprodremoveTableView.setItems(Inventory.getAllParts());
        modprodremovepartidCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        modprodremovepartnameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        modprodremoveinvCol.setCellValueFactory(new PropertyValueFactory<>("stock"));
        modprodremovepriceCol.setCellValueFactory(new PropertyValueFactory<>("price"));    }
}