package com.cpsc471.cpsc471project;


/**
 * Created by Jason on 2017-03-20.
 */

public class ExerciseHistory
{
    private int UserID;
    private int ExerciseID;
    private String Date;
    private String Records;
//    private String Sets;
//    private String Repetitions;
//    private String Time;

    public ExerciseHistory() {};
    public ExerciseHistory(int uID, int eID, String date, String records)
    {
        super();
        this.UserID = uID;
        this.ExerciseID = eID;
        this.Date = date;
        this.Records = records;
//        this.Sets = sets;
//        this.Repetitions = reps;
//        this.Time = time;
    }

    @Override
    public String toString()
    {
        return "ExerciseHistory [UserID=" + UserID + ", ExerciseID=" + ExerciseID + ", Date=" + Date + ", Records=" + Records + "]";
    }

    public void setUserID(int uID) {this.UserID = uID;}
    public int getUserID() {return this.UserID;}

    public void setExerciseID(int eID) {this.ExerciseID = eID;}
    public int getExerciseID() {return this.ExerciseID;}

    public void setDate(String date) {this.Date = date;}
    public String getDate() {return this.Date;}

    public void setRecords(String records) {this.Records = records;}
    public String getRecords() {return this.Records;}
}
