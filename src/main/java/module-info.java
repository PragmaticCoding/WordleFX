module ca.pragmaticcoding.wordle {
    requires javafx.controls;
    requires javafx.fxml;
    requires kotlin.stdlib;
    requires org.kordamp.ikonli.javafx;


    opens ca.pragmaticcoding.wordle to javafx.fxml;
    exports ca.pragmaticcoding.wordle;
}