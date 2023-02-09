package model;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class InventoryApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(InventoryApplication.class.getResource("/view/MainScreen.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1100, 500);
        stage.setTitle("Inventory Management System");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {

        //Add Parts InHouse
        Inventory.addPart(new InHouse(1, "Legend of Zelda - Tears of the Kingdom", 69.99, 500,60 ,70 , 17));
        Inventory.addPart(new InHouse(2, "Legend of Zelda - Breath of the Wild", 49.99, 40,30 ,50 , 10));
        Inventory.addPart(new InHouse(3, "Pokemon Sword", 59.99, 90,30 ,60 , 18));
        Inventory.addPart(new InHouse(4, "Super Mario Odyssey", 29.99, 400,30 ,60 , 19));

        //Add Parts OutSourced
        Inventory.addPart(new Outsourced(1, "Fortnite", 0.00, 1000,0 ,0 , "Epic"));
        Inventory.addPart(new Outsourced(1, "Kingdom Hearts 3", 59.99, 400,30 ,60 , "Square Enix"));
        Inventory.addPart(new Outsourced(1, "Octopath Traveler", 49.99, 300,30 ,60 , "Square Enix"));
        Inventory.addPart(new Outsourced(1, "Just Cause 3", 59.99, 700,20 ,60 , "Square Enix"));
        Inventory.addPart(new Outsourced(1, "Final Fantasy VII Remake", 49.99, 1200,30 ,60 , "Square Enix"));
        Inventory.addPart(new Outsourced(1, "Final Fantasy VII Remake Intergrade", 69.99, 800,40 ,70 , "Square Enix"));
        Inventory.addPart(new Outsourced(1, "Mario Party Superstars", 59.99, 300,40 ,60 , "NDcube"));

        //Add Products
        Inventory.addProduct(new Product(1, "Playstation God of War Bundle", 559.99, 10, 1 , 50));
        Inventory.addProduct(new Product(2, "Nintendo Switch Mario Kart Bundle", 299.99, 50, 1, 90));
        Inventory.addProduct(new Product(3, "Xbox Series X – Forza Horizon 5 Bundle", 559.99, 99,1, 200));

        launch();

    }
}