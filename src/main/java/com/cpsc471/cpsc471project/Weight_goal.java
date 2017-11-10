package com.cpsc471.cpsc471project;

/**
 * Created by ri1ey on 2017-04-07.
 */

public class Weight_goal {
    private int UserID;
    private int weight;
    private String type;
    public Weight_goal(){};
    public Weight_goal(int UserID, int weight, String type){
        this.UserID = UserID;
        this.weight = weight;
        this.type = type;
    }
    public void setUserID(int id){this.UserID = id;}
    public void setWeight(int w){this.weight = w;}
    public void setType(String type){this.type = type;}
    public int getUserID(){return this.UserID;}
    public int getWeight(){return this.weight;}
    public String getType(){return this.type;}
}
