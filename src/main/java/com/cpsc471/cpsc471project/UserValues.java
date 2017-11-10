package com.cpsc471.cpsc471project;

/**
 * Created by ri1ey on 2017-03-29.
 */

public class UserValues {
    public static String username;
    public static int userID;
    public static int privilege;
    public static boolean loggedIn = false;

    public static void log_out(){
        username = "";
        userID = -1;
        privilege = -1;
        loggedIn = false;
    }
}
