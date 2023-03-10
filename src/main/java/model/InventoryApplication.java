package model;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * This class is for loading the Application
 *
 * @author Nicholas Donnarumma
 */
public class InventoryApplication extends Application {
    /**
     * This start method is used to load the fxml for the Main screen
     */
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(InventoryApplication.class.getResource("/view/MainScreen.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1075, 500);
        stage.setTitle("Inventory Management System");
        stage.setScene(scene);
        stage.show();
    }
    /**
     * This main method is used to launch the application and fill the tables with sample data
     * Javadocs will be in separate zip file path is file:///C:/Users/Nick/Documents/javadoc/kinkead.firstscreen/module-summary.html
     * FUTURE ENHANCEMENT
     * The application is useful but could use some future enhancements to extend functionality
     * One enhancement would be to add a button to the main screen product table that will
     * remove all associated parts from a product That way, the user won't need to select
     * the modify button, go to a new screen and remove the parts one by one
     * FUTURE ENHANCEMENT
     * Another enhancement that can be done is the ability to select and add multiple parts
     * from the top table in the Add Product and Modify Product Screens If a product contains
     * a large amount of associated parts then it will be very tedious to have to add the parts
     * to the product one at a time
     */
    public static void main(String[] args) {

        //Add Parts InHouse
        Inventory.addPart(new InHouse(1111, "Legend of Zelda - Tears of the Kingdom", 69.99, 50,10 ,70 , 17));
        Inventory.addPart(new InHouse(1112, "Legend of Zelda - Breath of the Wild", 49.99, 40,30 ,50 , 10));
        Inventory.addPart(new InHouse(1113, "Pokemon Sword", 59.99, 40,30 ,60 , 18));
        Inventory.addPart(new InHouse(1114, "Super Mario Odyssey", 29.99, 40,30 ,60 , 19));

        //Add Parts OutSourced
        Inventory.addPart(new Outsourced(1115, "Fortnite", 0.00, 1000,100 ,5000 , "Epic"));
        Inventory.addPart(new Outsourced(1116, "Kingdom Hearts 3", 59.99, 40,30 ,60 , "Square Enix"));
        Inventory.addPart(new Outsourced(1117, "Octopath Traveler", 49.99, 35,30 ,60 , "Square Enix"));
        Inventory.addPart(new Outsourced(1118, "Just Cause 3", 59.99, 70,20 ,600 , "Square Enix"));
        Inventory.addPart(new Outsourced(1119, "Final Fantasy VII Remake", 49.99, 1200,30 ,1600 , "Square Enix"));
        Inventory.addPart(new Outsourced(2110, "Final Fantasy VII Remake Intergrade", 69.99, 80,40 ,90 , "Square Enix"));
        Inventory.addPart(new Outsourced(2211, "Mario Party Superstars", 59.99, 50,40 ,60 , "NDcube"));

        //Add Products
        Inventory.addProduct(new Product(1111, "Playstation God of War Bundle", 559.99, 10, 1 , 50));
        Inventory.addProduct(new Product(1112, "Nintendo Switch Mario Kart Bundle", 299.99, 50, 1, 90));
        Inventory.addProduct(new Product(2113, "Xbox Series X ??? Forza Horizon 5 Bundle", 559.99, 99,1, 200));

        launch();

    }
}