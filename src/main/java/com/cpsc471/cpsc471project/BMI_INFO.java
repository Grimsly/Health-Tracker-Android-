package com.cpsc471.cpsc471project;

/**
 * Created by ri1ey on 2017-04-07.
 */

public class BMI_INFO {
    private int InfoID;
    private int UpperLimit;
    private int LowerLimit;
    private String healthstatus;
    public BMI_INFO(){}
    public BMI_INFO(int UpperLimit, int LowerLimit, String status){
        super();
        this.UpperLimit = UpperLimit;
        this.LowerLimit = LowerLimit;
        this.healthstatus = status;
    }
    public void setInfoID(int ID){this.InfoID = ID;}
    public void setUpperLimit(int UL){this.UpperLimit = UL;}
    public void setLowerLimit(int LL){this.LowerLimit = LL;}
    public void setHealthstatus(String s){this.healthstatus = s;}
    public int getInfoID(){return this.InfoID;};
    public int getUpperLimit(){return this.UpperLimit;};
    public int getLowerLimit(){return this.LowerLimit;};
    public String getHealthstatus(){return this.healthstatus;};
}
