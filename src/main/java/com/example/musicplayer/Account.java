package com.example.musicplayer;

public abstract class Account {
    public Account(String username, String password){
        this.username = username;
        this.password = password;
    }
    public String username;
    public String password;
    public String phone;
    public String address;

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getPhone() {
        return phone;
    }

    public String getAddress() {
        return address;
    }
}
