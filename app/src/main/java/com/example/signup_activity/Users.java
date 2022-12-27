package com.example.signup_activity;

public class Users
{
    String profilepic, username,mail,name,password,id,lastmessage;

    public Users(String profilepic, String username, String mail, String name, String password, String id, String lastmessage) {
        this.profilepic = profilepic;
        this.username = username;
        this.mail = mail;
        this.name = name;
        this.password = password;
        this.id = id;
        this.lastmessage = lastmessage;
    }
    public Users(){}

    //Sign_up Constructor

    public Users(String Username, String Mail, String Password) {
        this.username = Username;
        this.mail = Mail;
        this.password = Password;

    }


    public String getProfilepic() {
        return profilepic;
    }

    public void setProfilepic(String profilepic) {
        this.profilepic = profilepic;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLastmessage() {
        return lastmessage;
    }

    public void setLastmessage(String lastmessage) {
        this.lastmessage = lastmessage;
    }
}
