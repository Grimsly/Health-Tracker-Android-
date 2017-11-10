package com.cpsc471.cpsc471project;

/**
 * Created by Jason on 2017-03-20.
 */

public class Workout
{
    private int WID;
    private String name;

    public Workout(){};
    public Workout(String name)
    {
        super();
        this.name = name;
    }

    @Override
    public String toString()
    {
        return "Workout [WID=" + this.WID + ", name=" + name + "]";
    }

    public void setID(int ID) {this.WID = ID;}
    public int getID() {return this.WID;}

    public void setName(String name) {this.name = name;}
    public String getName() {return this.name;}
}
