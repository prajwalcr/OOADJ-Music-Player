package com.example.musicplayer;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.Scanner;

public abstract class ArtistAccount extends Account{
    public ArtistAccount(String username, String password){
        super(username, password);
    }

    public abstract void createAlbum();
}

class Artist extends ArtistAccount{
    public Artist(String username, String password) {
        super(username, password);
    }
    public void createAlbum(){
        try {
            Connection c = null;
            Statement stmt = null;

            Class.forName("org.postgresql.Driver");
            c = DriverManager
                    .getConnection("jdbc:postgresql://localhost:5432/musicdb",
                            "postgres", "postgres");
            c.setAutoCommit(false);
            //System.out.println("Opened database successfully");
            stmt = c.createStatement();

            System.out.println("Creating album...");
            // Fill this method please
            Scanner ob = new Scanner(System.in);
            System.out.println("Enter Album Name");
            String aname = ob.nextLine();

            System.out.println("Enter Number of songs to put in album");
            int num = ob.nextInt();
            ob.nextLine();
            while(num > 0)
            {
                // for \n

                System.out.println("Enter SongName");
                String sng = ob.nextLine();
                String sql = "INSERT INTO ALBUMS "+ "VALUES (" + "'"+  this.username + "',"+ "'" + sng + "'," + "'" + aname + "'" + ")";
                //System.out.println(sql);
                stmt.executeUpdate(sql);
                num--;
            }
            stmt.close();
            c.commit();
            c.close();
        } catch (Exception e) {
            System.err.println( e.getClass().getName()+": "+ e.getMessage() );
            System.exit(0);
        }
    }
}
