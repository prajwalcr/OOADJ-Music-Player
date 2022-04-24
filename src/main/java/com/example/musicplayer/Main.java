package com.example.musicplayer;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;


public class Main extends Application {

    private ObservableList<Song> songData = FXCollections.observableArrayList();
    private static Stage primaryStage;
    private static volatile boolean javaFxLaunched = false;

    public static Connection c = null;
    public static Statement stmt = null;

    public static String username;
    public static String password;


    public Main() {

    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        setStage(primaryStage);
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ktPlayer.fxml"));
        Parent root =(Parent) fxmlLoader.load();
        Controller controller = fxmlLoader.getController();
        controller.setMain(this);

        Scene scene = new Scene(root, 820, 740);
        scene.getStylesheets().add(getClass().getResource("LightTheme.css").toExternalForm());

        scene.setFill(Color.TRANSPARENT);


        primaryStage.setTitle("ktPlayer 0.1v");
        primaryStage.setScene(scene);
        primaryStage.initStyle(StageStyle.TRANSPARENT);
        primaryStage.setResizable(true);
        primaryStage.show();
    }

    public static void main(String[] args) {
        try {
            Class.forName("org.postgresql.Driver");
            c = DriverManager.getConnection("jdbc:postgresql://localhost:5432/mvcdb",
                    "postgres", "postgres");
            stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery( "SELECT * FROM SONG;" );
            while ( rs!=null && rs.next() ) {
                String user = rs.getString("CUSTOMER");
                String song = rs.getString("SONG");
                System.out.println(song);
            }
            rs.close();
            stmt.close();

        } catch (Exception e) {
            e.printStackTrace();
            System.err.println(e.getClass().getName()+": "+e.getMessage());
            System.exit(0);
        }
        System.out.println("Opened database successfully");
//        launch(args);
        new Thread(()-> launch(args)).start();

        System.out.println("Authenticate through the app to continue...");
        int a;
        while(username == null){
            try{
                Thread.sleep(1000);
            }catch (Exception e){
                System.out.println(e);
            }

        }

        System.out.println("Welcome " + username);




    }


    public static Stage getStage() {
        return Main.primaryStage;
    }

    private void setStage(Stage stage) {
        Main.primaryStage = stage;
    }
}