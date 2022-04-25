package com.example.musicplayer;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class StoreSong {
    public Song song;
    Connection c;
    Statement stmt;
    public StoreSong(Song song){
        this.song = song;
    }
    public void saveSong(){
        try{
            Class.forName("org.postgresql.Driver");
            String sql;
            c = DriverManager.getConnection("jdbc:postgresql://localhost:5432/musicdb",
                    "postgres", "postgres");
            stmt = c.createStatement();
            System.out.println("Inserting...");
            sql = "INSERT INTO SONGS VALUES(\'" + song.getSongName() + "\', \'" + song.getArtistName() + "\', \'" + song.getFormat() + "\', \'" + song.getDuration() + "\', \'" + song.getRate() + "\') ON CONFLICT (TITLE, ARTIST) DO NOTHING;";
            stmt.executeUpdate(sql);
            stmt.close();
            c.close();
        }catch (Exception e){
            System.out.println(e);
        }
    }
}
