package com.cpsc471.cpsc471project;

/**
 * Created by Jason on 2017-03-23.
 */

public class ExercisesInWorkout
{
    private int WID;
    private int EID;

    public ExercisesInWorkout() {}
    public ExercisesInWorkout(int wID, int eID)
    {
        super();
        this.WID = wID;
        this.EID = eID;
    }

    @Override
    public String toString()
    {
        return "ExercisesInWorkout [WID=" + WID + ", EID=" + EID + "]";
    }

    public void setWorkoutID(int wID) {this.WID = wID;}
    public int getWorkoutID() {return this.WID;}

    public void setExerciseID(int eID) {this.EID = eID;}
    public int getExerciseID() {return this.EID;}
}