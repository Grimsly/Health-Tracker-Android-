package com.cpsc471.cpsc471project;


/**
 * Created by Jason on 2017-03-23.
 */

public class Schedule
{
    private int count;
    private int UserID;

    public Schedule() {}
    public Schedule( int uID, int count)
    {
        super();
        this.UserID = uID;
        this.count = count;
    }

    @Override
    public String toString()
    {
        return "Schedule [SID=" + count + ", UserID=" + UserID + "]";
    }
    public void setCount(int count) {this.count = count;}
    public int getCount() {return this.count;}

    public void setUserID(int uID) {this.UserID = uID;}
    public int getUserID() {return this.UserID;}
}
