package com.example.musicplayer;
import java.util.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
interface searchDB
{
    void find(String name);
}
class searchSong implements searchDB
{
    public void find(String songName)
    {

        Connection c = null;
        Statement stmt = null;
        try {
            Class.forName("org.postgresql.Driver");
            c = DriverManager
                    .getConnection("jdbc:postgresql://localhost:5432/musicdb",
                            "postgres", "postgres");
            c.setAutoCommit(false);
            System.out.println("Opened database For Searching");

            stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery( "SELECT * FROM SONGS;" );
           List<String> songs=new ArrayList<String>();
            while ( rs.next() ) {

                String  title = rs.getString("title");
                if(title.indexOf(songName) != -1)
                {
                    songs.add(title);
                }

            }
            if(songs.size() == 0)
            {
                System.out.println("No Songs Found with this Name");
            }
            else
            {
                System.out.println(songs.size() + " Songs Found with name " + songName);
                for(String s: songs)
                {

                    System.out.println(s);

                }
            }
            rs.close();
            stmt.close();
            c.close();
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName()+": "+ e.getMessage() );
            System.exit(0);
        }
        //System.out.println("Operation done successfully Search song");
    }
}
class searchArtist implements searchDB {
    public void find(String artistName) {

        Connection c = null;
        Statement stmt = null;
        try {
            Class.forName("org.postgresql.Driver");
            c = DriverManager
                    .getConnection("jdbc:postgresql://localhost:5432/musicdb",
                            "postgres", "postgres");
            c.setAutoCommit(false);
            System.out.println("Opened database For Searching");

            stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM USERS;");
            List<String> artists = new ArrayList<String>();
            while (rs.next()) {

                String name = rs.getString("username");
                int artist = rs.getInt("is_artist");
                if (artist == 1 && name.indexOf(artistName) != -1) {
                    artists.add(name);
                }

            }
            if (artists.size() == 0) {
                System.out.println("No Artists Found with this Name");
            } else {
                System.out.println(artists.size() + " Artists Found with name " + artistName);
                for (String a : artists) {

                    System.out.println(a);

                }
            }
            rs.close();
            stmt.close();
            c.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        //System.out.println("Operation done successfully Search Artist");
    }
}
class searchPodcast implements searchDB
{
    public void find(String podName)
    {

        Connection c = null;
        Statement stmt = null;
        try {
            Class.forName("org.postgresql.Driver");
            c = DriverManager
                    .getConnection("jdbc:postgresql://localhost:5432/musicdb",
                            "postgres", "postgres");
            c.setAutoCommit(false);
            System.out.println("Opened database For Searching");

            stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery( "SELECT * FROM PODCASTS;" );
            List<String> pods=new ArrayList<String>();
            while ( rs.next() ) {

                String  title = rs.getString("title");
                if(title.indexOf(podName) != -1)
                {
                    pods.add(title);
                }

            }
            if(pods.size() == 0)
            {
                System.out.println("No Podcasts Found with this Name");
            }
            else
            {
                System.out.println(pods.size() + " Songs Found with name " + podName);
                for(String s: pods)
                {

                    System.out.println(s);

                }
            }
            rs.close();
            stmt.close();
            c.close();
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName()+": "+ e.getMessage() );
            System.exit(0);
        }
        //System.out.println("Operation done successfully Search song");
    }
}
class searchAlbum implements searchDB
{
    public void find(String albumName)
    {

        Connection c = null;
        Statement stmt = null;
        try {
            Class.forName("org.postgresql.Driver");
            c = DriverManager
                    .getConnection("jdbc:postgresql://localhost:5432/musicdb",
                            "postgres", "postgres");
            c.setAutoCommit(false);
            System.out.println("Opened database For Searching");

            stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery( "SELECT * FROM SONGS;" );
            List<String> songs=new ArrayList<String>();
            while ( rs.next() ) {

                String  album = rs.getString("album");

                String song = rs.getString("title");

                if(album.equals(albumName))
                {
                    System.out.println("Album: " + album + " AlbumName: " + albumName);
                    songs.add(song);
                }

            }
            if(songs.size() == 0)
            {
                System.out.println("No Album Found with this Name");
            }
            else
            {
                System.out.println(songs.size() + " Songs Found in Album " + albumName);
                for(String s: songs)
                {

                    System.out.println(s);

                }
            }
            rs.close();
            stmt.close();
            c.close();
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName()+": "+ e.getMessage() );
            System.exit(0);
        }
        //System.out.println("Operation done successfully Search song");
    }
}
class SearchFactory {
    public static searchDB getData(String choice)
    {
        // To add the others later
        if(choice.equals("searchsong"))
        {
            return new searchSong();
        }
        else if(choice.equals("searchartist"))
        {
            return new searchArtist();
        }
        else if(choice.equals("searchalbum"))
        {

            return new searchAlbum();
        }
        else if(choice.equals("searchpods"))
        {
            return new searchPodcast();
        }
        return null;
    }
}
public class Search
{
    public void display()
    {
        Main.clearScreen();
        System.out.println("SEARCH");

        Scanner ob = new Scanner(System.in);

        int op;
        searchDB s;
        do
        {
            System.out.println("1. Search Song");
            System.out.println("2. Search Artist");
            System.out.println("3. Search Album");
            System.out.println("4. Search Podcast");
            System.out.println("5. Exit");
            System.out.println("Enter your choice");
            op = ob.nextInt();
            switch(op)
            {
                case 1:
                    // Create a Search Object
                    System.out.println("Enter Song name to Search");
                    // Reading the \n from the previous input statemtnt
                    ob.nextLine();
                    String songname = ob.nextLine();
                    s = SearchFactory.getData("searchsong");

                    s.find(songname);
                    //System.out.println("Hit Enter to continue..");
                    //songname = ob.next();
                    break;
                case 2:
                    System.out.println("Enter Artist name to Search");
                    // Reading the \n from the previous input statemtnt
                    ob.nextLine();
                    String artistname = ob.nextLine();
                    s = SearchFactory.getData("searchartist");

                    s.find(artistname);

                    break;
                case 3:
                    System.out.println("Enter Album name to Search");
                    // Reading the \n from the previous input statemtnt
                    ob.nextLine();
                    String albumname = ob.nextLine();
                    s = SearchFactory.getData("searchalbum");

                    s.find(albumname);
                    break;
                case 4:
                    System.out.println("Enter Podcast name to Search");
                    // Reading the \n from the previous input statemtnt
                    ob.nextLine();
                    String podname = ob.nextLine();
                    s = SearchFactory.getData("searchpods");

                    s.find(podname);
                    break;
                case 5:
                    break;
            }
        }while(op != 5);
    }
}
