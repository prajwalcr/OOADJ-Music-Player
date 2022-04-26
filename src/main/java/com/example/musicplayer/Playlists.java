// Open Close Principle
package com.example.musicplayer;

import java.util.Scanner;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
interface playListItems
{
    void addItem();
}
class songItem implements playListItems
{
    Song S;
    String pname;
    songItem(String songName, String pname)
    {
        this.S = new Song("", "", songName, "", "", "", "");
        this.pname = pname;
    }
    public void addItem()
    {
        Connection c = null;
        Statement stmt = null;
        try {
            Class.forName("org.postgresql.Driver");
            c = DriverManager
                    .getConnection("jdbc:postgresql://localhost:5432/musicdb",
                            "postgres", "postgres");
            c.setAutoCommit(false);
            //System.out.println("Opened database successfully");

            stmt = c.createStatement();
            String sql = "INSERT INTO PLAYLISTS "+ "VALUES (" + "'"+  this.pname + "',"+ "'"+ this.S.getSongName() + "',"+  "NULL,"+  "'"+ Main.username + "'"+ ")";
            stmt.executeUpdate(sql);

            stmt.close();
            c.commit();
            c.close();
        } catch (Exception e) {
            System.err.println( e.getClass().getName()+": "+ e.getMessage() );
            System.exit(0);
        }
    }
}

/*---------*/
class podcastItem implements playListItems
{
    Podcast P;
    String pname;
    podcastItem(String podcastName, String pname)
    {
        this.P = new Podcast("", "", podcastName, "", "", "", "");
        this.pname = pname;
    }
    public void addItem()
    {
        Connection c = null;
        Statement stmt = null;
        try {
            Class.forName("org.postgresql.Driver");
            c = DriverManager
                    .getConnection("jdbc:postgresql://localhost:5432/musicdb",
                            "postgres", "postgres");
            c.setAutoCommit(false);
            //System.out.println("Opened database successfully");

            stmt = c.createStatement();

            String sql = "INSERT INTO PLAYLISTS "+ "VALUES (" + "'"+  this.pname + "',"+ "NULL," + "'" + this.P.getName()+ "'," + "'"+ Main.username + "'"+ ")";
            //System.out.println(sql);
            stmt.executeUpdate(sql);

            stmt.close();
            c.commit();
            c.close();
        } catch (Exception e) {
            System.err.println( e.getClass().getName()+": "+ e.getMessage() );
            System.exit(0);
        }
    }
}

public class Playlists {

    void createPlaylist()
    {
        Main.clearScreen();
        Scanner ob = new Scanner(System.in);
        String pname;
        System.out.println("Enter Playlist name");
        pname = ob.nextLine();
        System.out.println("Enter number of items");
        int num = ob.nextInt();
        int choice;
        while(num > 0)
        {
            System.out.println("Enter 1 to add song and 2 to add podcast");
            choice = ob.nextInt();
            // taking \n
            ob.nextLine();
            if(choice == 1)
            {
                System.out.println("Enter Song name");
                String sname = ob.nextLine();
                playListItems s = new songItem(sname, pname);
                s.addItem();
            }
            else if(choice == 2)
            {
                System.out.println("Enter podcast name");
                String pdname = ob.nextLine();
                playListItems p = new podcastItem(pdname, pname);
                p.addItem();
            }
            num--;
        }
    }
    public void displayPlaylists(String username)
    {
        Connection c = null;
        Statement stmt = null;
        try {
            Class.forName("org.postgresql.Driver");
            c = DriverManager
                    .getConnection("jdbc:postgresql://localhost:5432/musicdb",
                            "postgres", "postgres");
            c.setAutoCommit(false);
            //System.out.println("Opened database successfully");

            stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery( "SELECT * FROM PLAYLISTS;" );
            System.out.println("Playlists");
            System.out.println("Playlist Name" + "\t\t" + "Song" + "\t\t" + "Podcast" + "\t\t" + "Username");
            while ( rs.next() ) {

                String  artistname = rs.getString("name");
                String  song = rs.getString("song");
                String  podcast = rs.getString("podcast");
                String  uname = rs.getString("username");
                if(uname.equals(username))
                {
                    System.out.println(artistname + "\t\t" + song + "\t\t" + podcast + "\t\t" + username);
                }

            }
            rs.close();
            stmt.close();
            c.close();
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName()+": "+ e.getMessage() );
            System.exit(0);
        }
        //System.out.println("Operation done successfully");
    }
}
