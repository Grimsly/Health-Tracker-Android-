package com.cpsc471.cpsc471project;

/**
 * Created by Jason on 2017-03-23.
 */

public class User
{
    private int UserID;
    private String Username;
    private String Password;
    private int Privilege;

    public User() {}
    public User( String name, String pass, int privilege)
    {
        super();
        this.UserID = -1;
        this.Username = name;
        this.Password = pass;
        this.Privilege = privilege;
    }

    @Override
    public String toString()
    {
        return "User [UID=" + UserID + ", username=" + Username + ", password=" + Password + ", privilege=" + Privilege + "]";
    }

    public void setUserID(int uID) {this.UserID = uID;}
    public int getUserID() {return this.UserID;}

    public void setUsername(String name) {this.Username = name;}
    public String getUsername() {return this.Username;}

    public void setPassword(String newPassword) {this.Password = newPassword;}
    public String getPassword() {return this.Password;}

    public void setPrivilege(int UserPrivilege) {this.Privilege = UserPrivilege;}
    public int  getPrivilege() {return this.Privilege;}
}
