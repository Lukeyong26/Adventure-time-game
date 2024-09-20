module com.example {
    requires transitive javafx.graphics;
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.media;
    requires org.json;

    opens com.example.dungeon to javafx.fxml;
    exports com.example.dungeon;
}
