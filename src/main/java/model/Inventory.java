package model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Inventory {



    // Observable Lists from UML Diagram

    private static ObservableList<Part> allParts = FXCollections.observableArrayList();
    private static ObservableList<Product> allProducts = FXCollections.observableArrayList();


    // Required Methods from UML Diagram

    public static void addPart(Part newPart){
        allParts.add(newPart);
    }

    public static void addProduct(Product newProduct){
        allProducts.add(newProduct);
    }

    public static Part lookupPart(int partId){

        if(!allParts.isEmpty()){
            for(int i = 0; i < allParts.size(); i++){
                if(allParts.get(i).getId() == partId)
                    return allParts.get(i);
            }
        }
        return null;
    }

    public static Product lookupProduct(int productId){

        if(!allProducts.isEmpty()){
            for(int i = 0; i < allProducts.size(); i++){
                if(allProducts.get(i).getId() == productId)
                    return allProducts.get(i);
            }
        }
        return null;
    }

    public static ObservableList<Part> lookupPart(String partName){
        ObservableList<Part> findPart = FXCollections.observableArrayList();

        if(partName.length() == 0) {
            findPart = allParts;
        }
        else {
            for (int i = 0; i < allParts.size(); i++) {
                if (allParts.get(i).getName().toLowerCase().contains(partName.toLowerCase())) {
                    findPart.add(allParts.get(i));
                }
            }
        }

        return findPart;
    }

    public static ObservableList<Product> lookupProduct(String productName){
        ObservableList<Product> findProduct = FXCollections.observableArrayList();

        if(productName.length() == 0) {
            findProduct = allProducts;
        }
        else {
            for (int i = 0; i < allProducts.size(); i++) {
                if (allProducts.get(i).getName().toLowerCase().contains(productName.toLowerCase())) {
                    findProduct.add(allProducts.get(i));
                }
            }
        }

        return findProduct;
    }

    public static void updatePart(int index, Part selectedPart){
        allParts.set(index, selectedPart);
    }

    public static void updateProduct(int index, Product newProduct){
        allProducts.set(index, newProduct);
    }

    public static boolean deletePart(Part selectedPart){
        return allParts.remove(selectedPart);
    }

    public static boolean deleteProduct(Product selectedProduct){
        return allProducts.remove(selectedProduct);
    }

    public static ObservableList<Part> getAllParts(){
        return allParts;
    }


    public static ObservableList<Product> getAllProducts(){
        return allProducts;
    }


}
