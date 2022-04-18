module com.example.musicplayer {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.kordamp.bootstrapfx.core;
    requires com.almasb.fxgl.all;
    requires com.jfoenix;
    requires mp3agic;
    requires javafx.media;

    opens com.example.musicplayer to javafx.fxml;
    exports com.example.musicplayer;
}