package model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Inventory {

    // Declare Fields

    private static ObservableList<Part> allParts = FXCollections.observableArrayList();
    private static ObservableList<Product> allProducts = FXCollections.observableArrayList();


    // Declare Methods

    public static void addPart(Part newPart){
        allParts.add(newPart);
    }

    public static void addProduct(Product newProduct){
        allProducts.add(newProduct);
    }

    public static Part lookupPart(int partId){
        return Part;
    }

    public static Product lookupProduct(int productId){
        return Product;
    }

    public static ObservableList<Part> lookupPart(String partName){
        return ObservableList<Part>;
    }

    public static ObservableList<Product> lookupProduct(String productName){
        return ObservableList<Product>;
    }

    public static void updatePart(int index, Part selectedPart){

    }

    public static void updateProduct(int index, Product newProduct){

    }

    public static boolean deletePart(Part selectedPart){
        return false;
    }

    public static boolean deleteProduct(Product selectedProduct){
        return false;
    }

    public static ObservableList<Part> getAllParts(){
        return allParts;
    }


    public static ObservableList<Product> getAllProducts(){
        return allProducts;
    }
}
