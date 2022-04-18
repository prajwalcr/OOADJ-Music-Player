module com.example.musicplayer {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.kordamp.bootstrapfx.core;
    requires com.almasb.fxgl.all;

    opens com.example.musicplayer to javafx.fxml;
    exports com.example.musicplayer;
}