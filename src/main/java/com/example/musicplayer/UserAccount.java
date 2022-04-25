// Factory Pattern
package com.example.musicplayer;

public abstract class UserAccount extends Account {
    public UserAccount(String username, String password){
        super(username, password);
    }
    public abstract void changePlan();
}

class FreeUser extends UserAccount{
    public FreeUser(String username, String password){
        super(username, password);
    }
    public void changePlan(){
        System.out.println("You are now a premium user");
    }
}

class PremiumUser extends UserAccount{
    public PremiumUser(String username, String password){
        super(username, password);
    }
    public void changePlan(){
        System.out.println("You are now a free user");
    }
}

class UserFactory{
    UserAccount getUser(String type, String username, String password){
        if(type.equals("free")){
            return new FreeUser(username, password);
        }
        else if(type.equals("premium")){
            return new PremiumUser(username, password);
        }
        else{
            return null;
        }
    }
}
