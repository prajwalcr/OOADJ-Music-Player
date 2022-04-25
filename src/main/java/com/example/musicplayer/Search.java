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
        System.out.println("Operation done successfully Search");
    }
}
//class searchArtist implements searchDB
//{
//;
//}
//class searchPodcast implements searchDB
//{
//;
//}
//class searchAlbum implements searchDB
//{
//;
//}
class SearchFactory {
    public static searchDB getData(String choice)
    {
        // To add the others later
        if(choice == "searchsong")
        {
            return new searchSong();
        }
        else if(choice == "searchartist")
        {
            ;
        }
        return null;
    }
}
public class Search
{
    public void display()
    {
        Main.clearScreen();
        System.out.println("SEARCH and FILTER");

        Scanner ob = new Scanner(System.in);

        int op;
        searchDB s;
        do
        {
            System.out.println("1. Search Song");
            System.out.println("2. Search Artist");
            System.out.println("3. Search Album");
            System.out.println("4. Search Podcast");
            System.out.println("Enter your choice");
            op = ob.nextInt();
            switch(op)
            {
                case 1:
                    // Create a Search Object
                    System.out.println("Enter Song name to Search");
                    // BUffer
                    ob.nextLine();
                    String songname = ob.nextLine();
                    s = SearchFactory.getData("searchsong");

                    s.find(songname);
                    //System.out.println("Hit Enter to continue..");
                    //songname = ob.next();
                    break;
                case 2:
                    // Create a Playlists Objects
                    break;
                case 3:
                    // Create Podcasts Object
                    break;
                case 4:
                    break;
            }
        }while(op != 4);
    }
}
