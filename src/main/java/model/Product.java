package model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * The class for creating products.
 * Each product contains a list of associated parts that may or may not be empty.
 *
 * @author Nicholas Donnarumma
 */
public class Product {

    /**
     * The list for containing all of a product's associated parts.
     */
    private ObservableList<Part> associatedParts = FXCollections.observableArrayList();

    /**
     * The product's id.
     */
    private int id;

    /**
     * The product's name.
     */
    private String name;

    /**
     * The product's price.
     */
    private double price;

    /**
     * The product's inventory level.
     */
    private int stock;

    /**
     * The product's minimum inventory level.
     */
    private int min;

    /**
     * The product's maximum inventory level.
     */
    private int max;

    /**
     * The constructor for a Product object.
     * Every product must contain an id, name, price, stock, min and max.
     */
    public Product(int id, String name, double price, int stock, int min, int max){
        this.id = id;
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.min = min;
        this.max = max;
    }

    /**
     * The Getter to get a product's id
     * @return the id
     */
    public int getId(){
        return id;
    }

    /**
     * The Setter to set a product's id
     * @param id the id to set
     */
    public void setId(int id){
        this.id = id;
    }

    /**
     * The Getter to get a product's name
     * @return the name
     */
    public String getName(){
        return name;
    }

    /**
     * The Setter to set a product's name
     * @param name the name to set
     */
    public void setName(String name){
        this.name = name;
    }

    /**
     * The Getter to get a product's price
     * @return the price
     */
    public double getPrice(){
        return price;
    }

    /**
     * The Setter to set a product's price
     * @param price the price to set
     */
    public void setPrice(double price){
        this.price = price;
    }

    /**
     * The Getter to get a product's stock
     * @return the inventory level
     */
    public int getStock(){
        return stock;
    }

    /**
     * The Setter to set a product's price
     * @param stock the stock to set
     */
    public void setStock(int stock){
        this.stock = stock;
    }

    /**
     * The Getter to get a product's minimum inventory level
     * @return the minimum inventory level
     */
    public int getMin(){
        return min;
    }

    /**
     * The Setter to set a product's minimum inventory level
     * @param min the minimum inventory level to set
     */
    public void setMin(int min){
        this.min = min;
    }

    /**
     * The Getter to get a product's maximum inventory level
     * @return the maximum inventory level
     */
    public int getMax(){
        return max;
    }

    /**
     * The Setter to set a product's maximum inventory level
     * @param max the maximum inventory level to set
     */
    public void setMax(int max){
        this.max = max;
    }

    /**
     * The method to copy a part from the list of all parts and add it to the list of associated parts
     * @param part the part that is associated with the product that will be added to the associated parts list
     */
    public void addAssociatedPart(Part part){
       associatedParts.add(part);
    }

    /**
     * The method to delete a part from the list of associated parts
     * @return true if the associated part was deleted successfully or false if part was not deleted
     */
    public boolean deleteAssociatedPart(Part selectedAssociatedPart){
        if (associatedParts.contains(selectedAssociatedPart)) {
            associatedParts.remove(selectedAssociatedPart);
            return true;
        }
        else
            return false;    }

    /**
     * The method to get all associated parts from the list of associated parts
     * @return the list of all the parts that are associated with the product
     */
    public ObservableList<Part> getAllAssociatedParts(){
        return associatedParts;
    }

}
