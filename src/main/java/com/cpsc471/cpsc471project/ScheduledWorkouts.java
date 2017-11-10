package com.cpsc471.cpsc471project;

/**
 * Created by ri1ey on 2017-04-04.
 */

public class ScheduledWorkouts {
    private int WID;
    private int UserSID;
    private int DayNo;
    public ScheduledWorkouts() {}
    public ScheduledWorkouts(int WID, int UserSID, int DayNo){
        super();
        this.WID = WID;
        this.UserSID = UserSID;
        this.DayNo = DayNo;
    }

    @Override
    public String toString() { return "Scheduled Workout [WID="+WID+", UserSID="+UserSID+", DayNo="+DayNo+"]";}
    public int getWID(){return this.WID;}
    public int getUserSID(){return this.UserSID;}
    public int getDayNo(){return this.DayNo;}
    public void setWID(int WID){this.WID = WID;}
    public void setUserSID(int UserSID){this.UserSID = UserSID;}
    public void setDayNo(int DayNo){this.DayNo = DayNo;}


}
