package com.example.musicplayer;

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
        System.out.println("Creating album...");
        // Fill this method please
    }
}
