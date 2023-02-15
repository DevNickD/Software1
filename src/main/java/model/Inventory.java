package model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * This class is the Inventory that will contain all parts and products
 *
 * @author Nicholas Donnarumma
 */
public class Inventory {

    /**
     * The list that will contain all the parts
     * This list will be used to fill a Parts table
     */
    private static ObservableList<Part> allParts = FXCollections.observableArrayList();

    /**
     * The list that will contain all the products
     * This list will be used to fill a Products table
     */
    private static ObservableList<Product> allProducts = FXCollections.observableArrayList();

    /**
     * The method to add a part to the list of all parts
     * @param newPart the part that will be added to parts list
     */
    public static void addPart(Part newPart){
        allParts.add(newPart);
    }

    /**
     * The method to add a product to the list of all products
     * @param newProduct the product that will be added to the products list
     */
    public static void addProduct(Product newProduct){
        allProducts.add(newProduct);
    }

    /**
     * The method to search for a part, in the list of all parts, that contains a matching id
     * @return any part that has id number that matches the number entered in search bar or null if no part is found
     */
    public static Part lookupPart(int partId){

        if(!allParts.isEmpty()){
            for(int i = 0; i < allParts.size(); i++){
                if(allParts.get(i).getId() == partId)
                    return allParts.get(i);
            }
        }
        return null;
    }

    /**
     * The method to search for a product, in the list of all products, that contains a matching id
     * @return any product that has id number that matches the number entered in search bar or null if no part is found
     */
    public static Product lookupProduct(int productId){

        if(!allProducts.isEmpty()){
            for(int i = 0; i < allProducts.size(); i++){
                if(allProducts.get(i).getId() == productId)
                    return allProducts.get(i);
            }
        }
        return null;
    }

    /**
     * The method to search for a part, in the list of all parts, that contains all or part of a word that is being searched
     * @return all parts that has a name that fully or partially matches the text entered in search bar
     */
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

    /**
     * The method to search for a product, in the list of all products, that contains all or part of a word that is being searched
     * @return all products that has a name that fully or partially matches the text entered in search bar
     */
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

    /**
     * The method to update a part by assigning a new part to a specific index of the parts list
     * @param index the index of the parts list that will be updated
     * @param selectedPart the part that will replace the selected part from the parts table
     */
    public static void updatePart(int index, Part selectedPart){
        allParts.set(index, selectedPart);
    }

    /**
     * The method to update a product by assigning a new product to a specific index of the products list
     * @param index the index of the products list that will be updated
     * @param newProduct the product that will replace the selected product from the product table
     */
    public static void updateProduct(int index, Product newProduct){
        allProducts.set(index, newProduct);
    }

    /**
     * The method to delete a part from the list of all parts
     * @return the selected part removed from parts list
     */
    public static boolean deletePart(Part selectedPart){
        return allParts.remove(selectedPart);
    }

    /**
     * The method to delete a product from the list of all products
     * @return the selected product removed from products list
     */
    public static boolean deleteProduct(Product selectedProduct){
        return allProducts.remove(selectedProduct);
    }

    /**
     * The method to get all the parts from the list of all parts
     * @return the list of all parts
     */
    public static ObservableList<Part> getAllParts(){
        return allParts;
    }

    /**
     * The method to get all the products from the list of all products
     * @return the list of all products
     */
    public static ObservableList<Product> getAllProducts(){
        return allProducts;
    }

}
