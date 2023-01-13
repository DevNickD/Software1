module kinkead.firstscreen {
    requires javafx.controls;
    requires javafx.fxml;


    opens kinkead.firstscreen to javafx.fxml;
    exports kinkead.firstscreen;
}