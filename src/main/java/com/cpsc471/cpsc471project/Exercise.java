package com.cpsc471.cpsc471project;

/**
 * Created by ri1ey on 2017-03-19.
 */


public class Exercise {
    private int eID;
    private String name;
    private String muscleGroup;
    private String type;
    //private int repetitions;
    //private int sets;
    public Exercise(){}
    public Exercise(String name, String muscleGroup, String type) {
        super();
        this.name = name;
        this.muscleGroup = muscleGroup;
        this.type = type;
    }

    //getters & setters
    @Override
    public String toString() {
        return name;
    }
    public void setId(int ID){
        this.eID = ID;
    }
    public void setName(String name){
        this.name = name;
    }
    public void setMuscleGroup(String MG){
        this.muscleGroup = MG;
    }
    public void setType(String type){
        this.type = type;
    }
    public int getId(){
        return this.eID;
    }
    public String getName(){
        return this.name;
    }
    public String getMuscleGroup(){
        return this.muscleGroup;
    }
    public String getType(){
        return this.type;
    }
}
