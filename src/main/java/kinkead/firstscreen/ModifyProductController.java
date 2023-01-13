package kinkead.firstscreen;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class ModifyProductController implements Initializable {

    public Label TheLabel;
    public int count = 1;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        System.out.println("I am initialized");
    }

    public void OnButtonClicked(ActionEvent actionEvent) {

        System.out.println("I am clicked!");
        if (count == 1) {
            TheLabel.setText("You clicked the button : " + count++ + " time");
        } else {
            TheLabel.setText("You clicked the button : " + count++ + " times");
        }
    }
}