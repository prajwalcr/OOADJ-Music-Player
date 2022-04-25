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
/*For menu Driven Section*/
import java.util.Scanner;
/**/

public class Main extends Application {

    private ObservableList<Song> songData = FXCollections.observableArrayList();
    private static Stage primaryStage;
    private static volatile boolean javaFxLaunched = false;

    public static Connection c = null;
    public static Statement stmt = null;

    public static String username;
    public static String password;
    public static boolean isArtist;
    public static boolean isPremium;


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
    public static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public static void main(String[] args) {
        createTables();
//        try {
//            Class.forName("org.postgresql.Driver");
//            c = DriverManager.getConnection("jdbc:postgresql://localhost:5432/musicdb",
//                    "postgres", "postgres");
//            stmt = c.createStatement();
//            ResultSet rs = stmt.executeQuery( "SELECT * FROM SONG;" );
//            while ( rs!=null && rs.next() ) {
//                String user = rs.getString("CUSTOMER");
//                String song = rs.getString("SONG");
//                System.out.println(song);
//            }
//            rs.close();
//            stmt.close();
//
//        } catch (Exception e) {
//            e.printStackTrace();
//            System.err.println(e.getClass().getName()+": "+e.getMessage());
//            System.exit(0);
//        }
//        System.out.println("Opened database successfully");
//        launch(args);
        new Thread(()-> launch(args)).start();

        System.out.println("Authenticate through the app to continue...");
        while(username == null || password == null){
            try{
                Thread.sleep(1000);
            }catch (Exception e){
                System.out.println(e);
            }

        }
        clearScreen();
        System.out.println("Welcome " + username);
        /*Menu Driven Section*/
        Scanner ob = new Scanner(System.in);

        int op;

        do
        {
            System.out.println("1. Search");
            System.out.println("2. Create Playlist");
            System.out.println("3. Exit");
            System.out.println("Enter your choice");
            op = ob.nextInt();
            switch(op)
            {
                case 1:
                    // Create a Search Object
                    Search s = new Search();
                    s.display();
                    break;
                case 2:
                    // Create a Create Playlists
                    Playlists p = new Playlists();
                    p.createPlaylist();
                    break;

                case 4:
                    System.exit(0);
                    break;
            }
        }while(op != 4);




    }


    public static Stage getStage() {
        return Main.primaryStage;
    }

    private void setStage(Stage stage) {
        Main.primaryStage = stage;
    }

    public static void createTables(){
        System.out.println("Entered");
        try {
            Class.forName("org.postgresql.Driver");
            c = DriverManager.getConnection("jdbc:postgresql://localhost:5432/musicdb",
                    "postgres", "postgres");
            stmt = c.createStatement();
            String sql = "CREATE TABLE IF NOT EXISTS USERS(USERNAME TEXT PRIMARY KEY, PASSWORD TEXT, IS_ARTIST INTEGER);";
            stmt.executeUpdate(sql);
            sql = "CREATE TABLE IF NOT EXISTS SONGS(TITLE TEXT, ARTIST TEXT, ALBUM TEXT, WIDTH TEXT, DURATION TEXT, PRIMARY KEY(TITLE, ARTIST));";
            stmt.executeUpdate(sql);
            sql = "CREATE TABLE IF NOT EXISTS PODCASTS(NAME TEXT, SPEAKER TEXT, DURATION TEXT, PRIMARY KEY(NAME, SPEAKER));";
            stmt.executeUpdate(sql);
            sql = "CREATE TABLE IF NOT EXISTS USERS_SONGS(USERNAME TEXT, TITLE TEXT, ARTIST TEXT, PRIMARY KEY(USERNAME, TITLE, ARTIST), CONSTRAINT  FK_USU FOREIGN KEY(USERNAME) REFERENCES USERS(USERNAME), CONSTRAINT FK_USS FOREIGN KEY(TITLE, ARTIST) REFERENCES SONGS(TITLE, ARTIST));";
            stmt.executeUpdate(sql);
            sql = "CREATE TABLE IF NOT EXISTS PLAYLISTS(NAME TEXT, SONG TEXT, PODCAST TEXT, USERNAME TEXT);";
            stmt.executeUpdate(sql);
            sql = "CREATE TABLE IF NOT EXISTS USERS_SONGS_PLAYLISTS(USERNAME TEXT, TITLE TEXT, ARTIST TEXT, TAG TEXT, PRIMARY KEY(USERNAME, TITLE, ARTIST, TAG), CONSTRAINT FK_USPU FOREIGN KEY(USERNAME) REFERENCES USERS(USERNAME), CONSTRAINT FK_USPS FOREIGN KEY(TITLE, ARTIST) REFERENCES SONGS(TITLE, ARTIST), CONSTRAINT FK_USPP FOREIGN KEY(TAG) REFERENCES PLAYLISTS(TAG));";
            stmt.executeUpdate(sql);
            stmt.close();
            System.out.println("Opened database successfully");
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println(e.getClass().getName()+": "+e.getMessage());
            System.exit(0);
        }
    }
}