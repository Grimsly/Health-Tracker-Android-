package com.cpsc471.cpsc471project;

/**
 * Created by Jason on 2017-03-23.
 */

public class HealthHistory
{
    private int UserID;
    private String Date;
    private int UserWeight;
    private int UserHeight;

    public HealthHistory() {}
    public HealthHistory(int uID, String date, int weight, int height)
    {
        super();
        this.UserID = uID;
        this.Date = date;
        this.UserWeight = weight;
        this.UserHeight = height;
    }

    @Override
    public String toString()
    {
        return "HealthHistory [UserID=" + UserID + "Date=" + Date + "Weight=" + UserWeight + "Height=" + UserHeight + "]";
    }

    public void setUserID(int uID) {this.UserID = uID;}
    public int getUserID() {return this.UserID;}

    public void setDate(String date) {this.Date = date;}
    public String getDate() {return this.Date;}

    public void setUserWeight(int weight) {this.UserWeight = weight;}
    public int getUserWeight() {return this.UserWeight;}

    public void setUserHeight(int height) {this.UserHeight = height;}
    public int getUserHeight() {return this.UserHeight;}
}
