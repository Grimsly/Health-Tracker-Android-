package com.cpsc471.cpsc471project;

/**
 * Created by ri1ey on 2017-03-19.
 */

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import com.cpsc471.cpsc471project.Exercise;
import com.cpsc471.cpsc471project.Workout;
import com.cpsc471.cpsc471project.ExercisesInWorkout;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MySQLiteHelper extends SQLiteOpenHelper{

    // Exercises table name
    private static final String TABLE_EXERCISES = "exercises";
    // Workout table name
    private static final String TABLE_WORKOUT = "workout";
    // User table name
    private static final String TABLE_USER = "user";
    // Exercises in Workout table name
    private static final String TABLE_EXERCISES_IN_WORKOUT = "exercises_in_workout";
    // Health History table name
    private static final String TABLE_HEALTH_HISTORY = "health_history";
    // Schedule table name
    private static final String TABLE_SCHEDULE = "schedule";
    // Exercise History table name
    private static final String TABLE_EXERCISE_HISTROY = "exercise_history";
    // Scheduled Workouts table name
    private static final String TABLE_SCHEDULED_WORKOUTS = "scheduled_workouts";
    // Weight Goal table name
    private static String TABLE_WEIGHT_GOAL = "weight_goal";
    // BMI Health Info
    private static String TABLE_BMI_INFO = "bmi_info";

    // Exercises Table Columns names
    private static final String EXERCISES_KEY_ID = "eID";
    private static final String EXERCISES_KEY_NAME = "name";
    private static final String EXERCISES_KEY_MUSCLEGROUP = "muscleGroup";
    private static final String EXERCISES_KEY_TYPE = "type";

    // Workout Table Columns names
    private static final String WORKOUT_KEY_ID = "WID";
    private static final String WORKOUT_KEY_NAME = "name";
    // User Table Columns names
    private static final String USER_KEY_ID = "UID";
    private static final String USER_KEY_USERNAME = "username";
    private static final String USER_KEY_PASSWORD = "password";
    private static final String USER_KEY_PRIVILEGE = "privilege";
    // Exercises in Workout Table Columns names
    private static final String EXERCISES_IN_WORKOUT_KEY_EID = "EID";
    private static final String EXERCISES_IN_WORKOUT_KEY_WID = "WID";
    // Health History Table Columns names
    private static final String HEALTH_HISTORY_KEY_USERID = "UserID";
    private static final String HEALTH_HISTORY_KEY_DATE = "date";
    private static final String HEALTH_HISTORY_KEY_WEIGHT = "weight";
    private static final String HEALTH_HISTORY_KEY_HEIGHT = "height";
    // Exercise History Table Columns names
    private static final String EXERCISE_HISTORY_KEY_USERID = "UserID";
    private static final String EXERCISE_HISTORY_KEY_EXERCISEID = "ExerciseID";
    private static final String EXERCISE_HISTORY_KEY_DATE = "date";
    private static final String EXERCISE_HISTORY_KEY_RECORDS = "records";
    // Scedule Table coloumns names
    private static final String SCHEDULE_KEY_USERID = "UserID";
    private static final String SCHEDULE_KEY_COUNT = "count";
    // Scheduled Workouts coloumns names
    private static final String SCHEDULED_WORKOUTS_KEY_WID = "WID";
    private static final String SCHEDULED_WORKOUTS_KEY_SID = "UserID";
    private static final String SCHEDULED_WORKOUTS_KEY_DAYNO = "DayNo";
    // BMI Info coloumn names
    private static final String BMI_INFO_KEY_INFOID = "InfoID";
    private static final String BMI_INFO_KEY_UPPERLIMIT = "UpperLimit";
    private static final String BMI_INFO_KEY_LOWERLIMIT = "LowerLimit";
    private static final String BMI_INFO_KEY_HEALTH_STATUS = "healthstatus";
    // Health goal col names
    private static final String WEIGHT_GOAL_KEY_USERID = "UserID";
    private static final String WEIGHT_GOAL_KEY_WEIGHT = "weight";
    private static final String WEIGHT_GOAL_KEY_TYPE = "type";


    private static final String[] EXERCISES_COLUMNS = {EXERCISES_KEY_ID,EXERCISES_KEY_NAME,EXERCISES_KEY_MUSCLEGROUP,EXERCISES_KEY_TYPE};
    private static final String[] WORKOUT_COLUMNS = {WORKOUT_KEY_ID,WORKOUT_KEY_NAME};
    private static final String[] USER_COLUMNS = {USER_KEY_ID,USER_KEY_USERNAME,USER_KEY_PASSWORD,USER_KEY_PRIVILEGE};
    private static final String[] EXERCISES_IN_WORKOUT_COLUMNS = {EXERCISES_IN_WORKOUT_KEY_EID,EXERCISES_IN_WORKOUT_KEY_WID};
    private static final String[] HEALTH_HISTORY_COLUMNS = {HEALTH_HISTORY_KEY_USERID,HEALTH_HISTORY_KEY_DATE,HEALTH_HISTORY_KEY_WEIGHT,HEALTH_HISTORY_KEY_HEIGHT};
    private static final String[] EXERCISE_HISTORY_COLUMNS = {EXERCISE_HISTORY_KEY_USERID,EXERCISE_HISTORY_KEY_EXERCISEID,EXERCISE_HISTORY_KEY_DATE,EXERCISE_HISTORY_KEY_RECORDS};
    private static final String[] SCHEDULE_COLUMNS = {SCHEDULE_KEY_USERID,SCHEDULE_KEY_COUNT};
    private static final String[] SCHEDULED_WORKOUTS_COLUMNS = {SCHEDULED_WORKOUTS_KEY_WID,SCHEDULED_WORKOUTS_KEY_SID,SCHEDULED_WORKOUTS_KEY_DAYNO};
    private static final String[] BMI_INFO_COLUMNS = {BMI_INFO_KEY_INFOID,BMI_INFO_KEY_UPPERLIMIT,BMI_INFO_KEY_LOWERLIMIT,BMI_INFO_KEY_HEALTH_STATUS};
    private static final String[] WEIGHT_GOAL_COLUMNS = {WEIGHT_GOAL_KEY_USERID,WEIGHT_GOAL_KEY_WEIGHT, WEIGHT_GOAL_KEY_TYPE};
    // Database Version
    private static final int DATABASE_VERSION = 18;
    // Database Name
    private static final String DATABASE_NAME = "ExerciseDB";

    public MySQLiteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onOpen(SQLiteDatabase db){
        super.onOpen(db);
        if (!db.isReadOnly()) {
            // Enable foreign key constraints
            db.execSQL("PRAGMA foreign_keys=ON;");
        }
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // SQL statement to create exercise table

        String CREATE_EXERCISE_TABLE = "CREATE TABLE exercises ( " +
                "eID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "name TEXT UNIQUE, "+
                "muscleGroup TEXT, " + "type TEXT" + ")";

        // SQL statement to create workout table
        String CREATE_WORKOUT_TABLE = "CREATE TABLE workout ( " +
                "WID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "name TEXT UNIQUE "+ ")";

        // SQL statement to create user table
        String CREATE_USER_TABLE = "CREATE TABLE user ( " +
                "UID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "username TEXT UNIQUE, "+
                "password TEXT, " + "privilege INTEGER" + ")";

        // SQL statement to create exercises_in_workout table
        String CREATE_EXERCISES_IN_WORKOUT_TABLE = "CREATE TABLE exercises_in_workout ( " +
                "WID INTEGER, " +
                "EID INTEGER, "+
                "FOREIGN KEY(WID) REFERENCES workout(WID) ON DELETE CASCADE, "+
                "FOREIGN KEY(EID) REFERENCES exercises(eID) ON DELETE CASCADE" + ")";

        // SQL statement to create health_history table
        String CREATE_HEALTH_HISTORY_TABLE = "CREATE TABLE health_history ( " +
                "UserID integer, " +
                "date text, " +
                "weight integer, " +
                "height integer, " +
                "FOREIGN KEY(UserID) REFERENCES user(UID) ON DELETE CASCADE)";
        //SQL statement to create schedule table
        String CREATE_SCHEDULE_TABLE = "CREATE TABLE schedule ( " +
                "UserID integer UNIQUE, " +
                "count integer, " +
                "FOREIGN KEY(UserID) REFERENCES user(UID) ON DELETE CASCADE)";
        //SQL statement to create Exercise History table
        String CREATE_EXERCISE_HISTORY_TABLE = "CREATE TABLE exercise_history ( " +
                "UserID INTEGER, " +
                "ExerciseID INTEGER, "+
                "date text, "+
                "records text, "+
                "FOREIGN KEY(UserID) REFERENCES user(UID) ON DELETE CASCADE, "+
                "FOREIGN KEY(ExerciseID) REFERENCES exercises(eID) ON DELETE CASCADE" + ")";
        //SQL statement to create scheduled workouts table
        String CREATE_SCHEDULED_WORKOUTS_TABLE = "CREATE TABLE scheduled_workouts ( " +
                "WID INTEGER, " +
                "UserID INTEGER, "+
                "DayNo INTEGER, "+
                "PRIMARY KEY(WID, UserID), "+
                "FOREIGN KEY(WID) REFERENCES workout(WID) ON DELETE CASCADE, "+
                "FOREIGN KEY(UserID) REFERENCES schedule(UserID) ON DELETE CASCADE" + ")";

        String CREATE_WEIGHT_GOAL_TABLE = "CREATE TABLE weight_goal ( " +
                "UserID INTEGER UNIQUE, "+
                "weight INTEGER, "+
                "type TEXT, " +
                "FOREIGN KEY(UserID) REFERENCES user(UID) ON DELETE CASCADE" +")";
        String CREATE_BMI_INFO_TABLE = "CREATE TABLE bmi_info ( "+
                "InfoID INTEGER PRIMARY KEY AUTOINCREMENT, "+
                "UpperLimit INTEGER, "+
                "LowerLimit INTEGER, "+
                "healthstatus text)";

        // create exercise table
        db.execSQL(CREATE_EXERCISE_TABLE);
        // create workout table
        db.execSQL(CREATE_WORKOUT_TABLE);
        // create user table
        db.execSQL(CREATE_USER_TABLE);
        // create exercises_in_workout table
        db.execSQL(CREATE_EXERCISES_IN_WORKOUT_TABLE);
        // create health_history table
        db.execSQL(CREATE_HEALTH_HISTORY_TABLE);
        // create schedule table
        db.execSQL(CREATE_SCHEDULE_TABLE);
        // create exercise history table
        db.execSQL(CREATE_EXERCISE_HISTORY_TABLE);
        // create scheduled workouts table
        db.execSQL(CREATE_SCHEDULED_WORKOUTS_TABLE);

        db.execSQL(CREATE_WEIGHT_GOAL_TABLE);
        db.execSQL(CREATE_BMI_INFO_TABLE);
        populateBMI_INFO(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older exercise table if existed
        db.execSQL("DROP TABLE IF EXISTS exercises");
        // Drop older workout table if existed
        db.execSQL("DROP TABLE IF EXISTS workout");
        // Drop older user table if existed
        db.execSQL("DROP TABLE IF EXISTS user");
        // Drop older exercises_in_workout table if existed
        db.execSQL("DROP TABLE IF EXISTS exercises_in_workout");
        // Drop old health_history table if existed
        db.execSQL("DROP TABLE IF EXISTS health_history");
        // Drop old schedule table if exists
        db.execSQL("DROP TABLE IF EXISTS schedule");
        // Drop old Exercise history table if exists
        db.execSQL("DROP TABLE IF EXISTS exercise_history");
        // Drop old Sceduled workouts table if exists
        db.execSQL("DROP TABLE IF EXISTS scheduled_workouts");
        db.execSQL("DROP TABLE IF EXISTS weight_goal");
        db.execSQL("DROP TABLE IF EXISTS bmi_info");

        // create fresh tables
        this.onCreate(db);
    }

    private void populateBMI_INFO(SQLiteDatabase db){
        BMI_INFO[] bmi_list = {new BMI_INFO(18,1,"Under Weight"),new BMI_INFO(24,19,"Healthy"),new BMI_INFO(29,25,"Over Weight"),new BMI_INFO(38,30,"Obese"),new BMI_INFO(50,39,"Extremely Obese")};
        // 1. get reference to writable DB
        // 2. create Contentvalues to add key column
        for(int i = 0; i < 5; i++) {
            ContentValues values = new ContentValues();
            values.put(BMI_INFO_KEY_UPPERLIMIT, bmi_list[i].getUpperLimit());
            values.put(BMI_INFO_KEY_LOWERLIMIT, bmi_list[i].getLowerLimit());
            values.put(BMI_INFO_KEY_HEALTH_STATUS, bmi_list[i].getHealthstatus());
            // 3. insert
            db.insert(TABLE_BMI_INFO, null, values);
        }

        // 4. close
        //for logging
        Log.i("PopulatedBMIInfo", "Populated BMI Info Table");
        //db.close();
    }

    // Get All Exercises
    public String getBMI_healthstatus(int bmi) {
        // 1. build the query
        String query = "select InfoID as _id, healthstatus from "+ TABLE_BMI_INFO + " where LowerLimit <= " + String.valueOf(bmi) + " AND UpperLimit >= "+ String.valueOf(bmi);

        // 2. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        // return exercises
        cursor.moveToFirst();
        String status = cursor.getString(cursor.getColumnIndexOrThrow("healthstatus"));
        return status;
    }

    public void addExercise(Exercise exercise){
        //for logging
        Log.i("addExercise", exercise.toString());
        // 1. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();
        // 2. create Contentvalues to add key column
        ContentValues values = new ContentValues();
        values.put(EXERCISES_KEY_NAME, exercise.getName());
        values.put(EXERCISES_KEY_MUSCLEGROUP, exercise.getMuscleGroup());
        values.put(EXERCISES_KEY_TYPE, exercise.getType());
        // 3. insert
        try{
            db.insertOrThrow(TABLE_EXERCISES,null,values);}
        catch (android.database.sqlite.SQLiteException e){
            if (e.getMessage().toString().contains("no such table")){
                Log.e("No Table exception", "Creating table " + TABLE_EXERCISES + "because it doesn't exist!" );
                // create table
                // re-run query, etc.
                // SQL statement to create exercise table
                String CREATE_EXERCISE_TABLE = "CREATE TABLE exercises ( " +
                        "eID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        "name TEXT, "+
                        "muscleGroup TEXT, " + "type TEXT" + ")";

                // create exercise table
                db.execSQL(CREATE_EXERCISE_TABLE);
                db.insert(TABLE_EXERCISES,null,values);
            }
        }
        // 4. close
        db.close();
    }

    public Exercise getExercise(String name){
        // 1. get reference to readable DB
        SQLiteDatabase db = this.getReadableDatabase();

        // 2. build query
        Cursor cursor =
                db.query(TABLE_EXERCISES, // a. table
                        EXERCISES_COLUMNS, // b. column names
                        " name = ?", // c. selections
                        new String[] { name }, // d. selections args
                        null, // e. group by
                        null, // f. having
                        null, // g. order by
                        null); // h. limit

        // 3. if we got results get the first one
        if (cursor != null)
            cursor.moveToFirst();

        // 4. build exercise object
        Exercise exercise = new Exercise();
        exercise.setId(Integer.parseInt(cursor.getString(0)));
        exercise.setName(cursor.getString(1));
        exercise.setMuscleGroup(cursor.getString(2));
        exercise.setType(cursor.getString(3));

        //log
        Log.i("getExercise("+name+")", exercise.toString());

        // 5. return exercise
        return exercise;
    }

    public int updateExercise(Exercise exercise){
        // 1. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();

        // 2. create ContentValues to add key coloumn
        ContentValues values = new ContentValues();
        values.put(EXERCISES_KEY_NAME, exercise.getName());
        values.put(EXERCISES_KEY_MUSCLEGROUP, exercise.getMuscleGroup());
        values.put(EXERCISES_KEY_TYPE, exercise.getType());

        // 3. updating row
        int i = db.update(TABLE_EXERCISES,
                values,
                EXERCISES_KEY_ID+" = ?",
                new String[] {String.valueOf(exercise.getId()) });
        // 4. close
        db.close();
        return i;
    }

    // Get All Exercises
    public ArrayList<Exercise> getAllExercises(String sql_where) {
        ArrayList<Exercise> exercises = new ArrayList<Exercise>();

        // 1. build the query
        String query = "select * from "+ TABLE_EXERCISES + sql_where;

        // 2. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        // 3. go over each row, build book and add it to list
        Exercise exercise = null;
        if (cursor.moveToFirst()) {
            do {
                exercise = new Exercise();
                exercise.setId(Integer.parseInt(cursor.getString(0)));
                exercise.setName(cursor.getString(1));
                exercise.setMuscleGroup(cursor.getString(2));
                exercise.setType(cursor.getString(3));

                // Add book to books
                exercises.add(exercise);
            } while (cursor.moveToNext());
        }

        Log.i("getAllExercises()", exercises.toString());

        // return exercises
        return exercises;
    }

    // Get All Exercises
    public Cursor getAllExercisesNames(String SQL_Where) {
        // 1. build the query
        String query = "select eID as _id, name from "+ TABLE_EXERCISES + SQL_Where;

        // 2. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        // return exercises
        cursor.moveToFirst();
        return cursor;
    }

    public void deleteExercise(Exercise exercise){
        // 1. get reference to writable db
        SQLiteDatabase db = this.getWritableDatabase();

        // 2. delete
        db.delete(TABLE_EXERCISES,
                EXERCISES_KEY_ID+" = ?",
                new String[] {String.valueOf(exercise.getId() )});

        // 3. close
        db.close();

        //log
        Log.i("deleteExercise", exercise.toString());
    }

    public void addWorkout(Workout workout){
        //for logging
        Log.i("addWorkout", workout.toString());
        // 1. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();
        // 2. create Contentvalues to add key column
        ContentValues values = new ContentValues();
        values.put(WORKOUT_KEY_NAME, workout.getName());
        // 3. insert
        try{
            db.insertOrThrow(TABLE_WORKOUT,null,values);}
        catch (android.database.sqlite.SQLiteException e){
            if (e.getMessage().toString().contains("no such table")){
                Log.e("No Table exception", "Creating table " + TABLE_WORKOUT + "because it doesn't exist!" );
                // create table
                // re-run query, etc.
                // SQL statement to create exercise table
                String CREATE_WORKOUT_TABLE = "CREATE TABLE workout ( " +
                        "WID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        "name TEXT UNIQUE "+ ")";
                // create exercise table
                db.execSQL(CREATE_WORKOUT_TABLE);
                db.insert(TABLE_WORKOUT,null,values);
            }
        }
        // 4. close
        db.close();
    }

    public Workout getWorkout(String name){
        // 1. get reference to readable DB
        SQLiteDatabase db = this.getReadableDatabase();

        // 2. build query
        Cursor cursor =
                db.query(TABLE_WORKOUT, // a. table
                        WORKOUT_COLUMNS, // b. column names
                        " name = ?", // c. selections
                        new String[] { name }, // d. selections args
                        null, // e. group by
                        null, // f. having
                        null, // g. order by
                        null); // h. limit

        // 3. if we got results get the first one
        if (cursor != null)
            cursor.moveToFirst();

        // 4. build exercise object
        Workout workout = new Workout();
        workout.setID(Integer.parseInt(cursor.getString(0)));
        workout.setName(cursor.getString(1));

        //log
        Log.i("getWorkout("+ name +")", workout.toString());

        // 5. return exercise
        return workout;
    }

    public void deleteWorkout(Workout workout){
        // 1. get reference to writable db
        SQLiteDatabase db = this.getWritableDatabase();

        // 2. delete
        db.delete(TABLE_WORKOUT,
                WORKOUT_KEY_ID+" = ?",
                new String[] {String.valueOf(workout.getID() )});

        // 3. close
        db.close();

        //log
        Log.i("deleteWorkout", workout.toString());
    }

    // Get All Workouts For Listview
    public Cursor getAllWorkouts() {
        // 1. build the query
        String query = "select WID as _id, name from "+ TABLE_WORKOUT ;

        // 2. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        // return workouts
        cursor.moveToFirst();
        return cursor;
    }


    public void addExerciseToWorkout(ExercisesInWorkout exercise){
        //for logging
        Log.i("addExerciseToWorkout", exercise.toString());
        // 1. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();
        // 2. create Contentvalues to add key column
        ContentValues values = new ContentValues();
        values.put(EXERCISES_IN_WORKOUT_KEY_EID, exercise.getExerciseID());
        values.put(EXERCISES_IN_WORKOUT_KEY_WID, exercise.getWorkoutID());
        // 3. insert
        try{
            db.insertOrThrow(TABLE_EXERCISES_IN_WORKOUT,null,values);}
        catch (android.database.sqlite.SQLiteException e){
            if (e.getMessage().toString().contains("no such table")){
                Log.e("No Table exception", "Creating table " + TABLE_EXERCISES_IN_WORKOUT + "because it doesn't exist!" );
                // create table
                // re-run query, etc.
                // SQL statement to create exercise table
                String CREATE_EXERCISES_IN_WORKOUT_TABLE = "CREATE TABLE exercises_in_workout ( " +
                        "WID INTEGER, " +
                        "EID INTEGER, "+
                        "FOREIGN KEY(WID) REFERENCES workout(WID) ON DELETE CASCADE, "+
                        "FOREIGN KEY(EID) REFERENCES exercises(eID) ON DELETE CASCADE" + ")";
                // create exercise table
                db.execSQL(CREATE_EXERCISES_IN_WORKOUT_TABLE);
                db.insert(TABLE_EXERCISES_IN_WORKOUT,null,values);
            }
        }
        // 4. close
        db.close();
    }

    public void deleteExerciseInWorkout(ExercisesInWorkout exercise){
        // 1. get reference to writable db
        SQLiteDatabase db = this.getWritableDatabase();

        // 2. delete
        db.delete(TABLE_EXERCISES_IN_WORKOUT,
                EXERCISES_IN_WORKOUT_KEY_EID +"=? and "+ EXERCISES_IN_WORKOUT_KEY_WID + "=?",
                new String[] {String.valueOf(exercise.getExerciseID()), String.valueOf(exercise.getWorkoutID())});

        // 3. close
        db.close();

        //log
        Log.i("deleteExerciseInWorkout", exercise.toString());
    }

    // Get All Workouts For Listview
    public Cursor getExercisesInWorkout(String SQL_Where) {
        // 1. build the query
        String query = "select eID as _id, name from "+ TABLE_EXERCISES + SQL_Where;

        // 2. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        // return workouts
        cursor.moveToFirst();
        return cursor;
    }
    //Add user to database
    public void addUser(User user){
        //for logging
        Log.i("addUser", user.toString());
        // 1. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();
        // 2. create Contentvalues to add key column
        ContentValues values = new ContentValues();
        values.put(USER_KEY_USERNAME, user.getUsername());
        values.put(USER_KEY_PASSWORD, user.getPassword());
        values.put(USER_KEY_PRIVILEGE, user.getPrivilege());
        // 3. insert
        try{
            db.insertOrThrow(TABLE_USER,null,values);}
        catch (android.database.sqlite.SQLiteException e){
            if (e.getMessage().toString().contains("no such table")){
                Log.e("No Table exception", "Creating table " + TABLE_USER + "because it doesn't exist!" );
                // create table
                // re-run query, etc.
                // SQL statement to create user table
                String CREATE_USER_TABLE = "CREATE TABLE user ( " +
                        "UID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        "username TEXT UNIQUE, "+
                        "password TEXT, " + "privilege INTEGER" + ")";

                // create exercise table
                db.execSQL(CREATE_USER_TABLE);
                db.insert(TABLE_USER,null,values);
            }
        }
        // 4. close
        db.close();
    }

    public User getUser(String name){
        // 1. get reference to readable DB
        SQLiteDatabase db = this.getReadableDatabase();

        // 2. build query
        Cursor cursor =
                db.query(TABLE_USER, // a. table
                        USER_COLUMNS, // b. column names
                        " username = ?", // c. selections
                        new String[] { name }, // d. selections args
                        null, // e. group by
                        null, // f. having
                        null, // g. order by
                        null); // h. limit

        // 3. if we got results get the first one
        if (cursor.getCount() == 0){
            User no_user = new User("","",-1);
            return no_user;
            }
        else {
            cursor.moveToFirst();

            // 4. build exercise object
            User user = new User();
            user.setUserID(Integer.parseInt(cursor.getString(0)));
            user.setUsername(cursor.getString(1));
            user.setPassword(cursor.getString(2));
            user.setPrivilege(Integer.parseInt(cursor.getString(3)));

            //log
            Log.i("getUser("+name+")", user.toString());

            // 5. return exercise
            return user;
        }
    }

    public void deleteUser(User user){
        // 1. get reference to writable db
        SQLiteDatabase db = this.getWritableDatabase();

        // 2. delete
        db.delete(TABLE_USER,
                USER_KEY_ID+" = ?",
                new String[] {String.valueOf(user.getUserID())});

        // 3. close
        db.close();

        //log
        Log.i("deleteUser", user.toString());
    }

    // Get All Exercises
    public Cursor getAllUsers() {
        // 1. build the query
        String query = "select UID as _id, username from "+ TABLE_USER + " where privilege <> 1";

        // 2. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        // return exercises
        cursor.moveToFirst();
        return cursor;
    }

    public void addHealthHistory(HealthHistory HH){
        //for logging
        Log.i("addHealthHistory", HH.toString());
        // 1. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();
        // 2. create Contentvalues to add key column
        ContentValues values = new ContentValues();
        values.put(HEALTH_HISTORY_KEY_USERID, HH.getUserID());
        values.put(HEALTH_HISTORY_KEY_DATE, HH.getDate());
        values.put(HEALTH_HISTORY_KEY_WEIGHT, HH.getUserWeight());
        values.put(HEALTH_HISTORY_KEY_HEIGHT, HH.getUserHeight());
        // 3. insert
        try{
            db.insertOrThrow(TABLE_HEALTH_HISTORY,null,values);}
        catch (android.database.sqlite.SQLiteException e){
            if (e.getMessage().toString().contains("no such table")){
                Log.e("No Table exception", "Creating table " + TABLE_HEALTH_HISTORY + "because it doesn't exist!" );
                // create table
                // re-run query, etc.
                // SQL statement to create health_history table
                String CREATE_HEALTH_HISTORY_TABLE = "CREATE TABLE health_history ( " +
                        "UserID integer, " +
                        "date text, " +
                        "weight integer, " +
                        "height integer, " +
                        "FOREIGN KEY(UserID) REFERENCES user(UID) ON DELETE CASCADE)";

                // create exercise table
                db.execSQL(CREATE_HEALTH_HISTORY_TABLE);
                db.insert(TABLE_HEALTH_HISTORY,null,values);
            }
        }
        // 4. close
        db.close();
    }

    // Get All Health History
    public Cursor getAllHealthHistory(String SQL_Where) {
        // 1. build the query
        String query = "select UserID as _id, date, weight, height from "+ TABLE_HEALTH_HISTORY + SQL_Where;

        // 2. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        // return exercises
        cursor.moveToFirst();
        return cursor;
    }

    public int updateUser(User user){
        // 1. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();

        // 2. create ContentValues to add key coloumn
        ContentValues values = new ContentValues();
        values.put(USER_KEY_USERNAME, user.getUsername());
        values.put(USER_KEY_PASSWORD, user.getPassword());
        values.put(USER_KEY_PRIVILEGE, user.getPrivilege());

        // 3. updating row
        int i = db.update(TABLE_USER,
                values,
                USER_KEY_ID+" = ?",
                new String[] {String.valueOf(user.getUserID()) });
        // 4. close
        db.close();
        return i;
    }

    public void updateScheduleCount(Schedule schedule){
        // 1. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();

        // 2. create ContentValues to add key coloumn
        ContentValues values = new ContentValues();
        values.put(SCHEDULE_KEY_COUNT, schedule.getCount());
        // 3. updating row
        int i = db.update(TABLE_SCHEDULE,
                values,
                SCHEDULE_KEY_USERID+" = ?",
                new String[] {String.valueOf(schedule.getUserID()) });
        // 4. close
        db.close();
    }
    // Add a new schedule
    public void addSchedule(Schedule schedule){
        //for logging
        Log.i("addSchedule", schedule.toString());
        // 1. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();
        // 2. create Contentvalues to add key column
        ContentValues values = new ContentValues();
        values.put(SCHEDULE_KEY_USERID, schedule.getUserID());
        values.put(SCHEDULE_KEY_COUNT, schedule.getCount());
        // 3. insert
        try{
            db.insertOrThrow(TABLE_SCHEDULE,null,values);}
        catch (android.database.sqlite.SQLiteException e){
            if (e.getMessage().toString().contains("no such table")){
                Log.e("No Table exception", "Creating table " + TABLE_SCHEDULE + "because it doesn't exist!" );
                // create table
                // re-run query, etc.
                //SQL statement to create schedule table
                String CREATE_SCHEDULE_TABLE = "CREATE TABLE schedule ( " +
                        "UserID integer UNIQUE, " +
                        "count integer, " +
                        "FOREIGN KEY(UserID) REFERENCES user(UID) ON DELETE CASCADE)";

                // create exercise table
                db.execSQL(CREATE_SCHEDULE_TABLE);
                db.insert(TABLE_SCHEDULE,null,values);
            }
        }
        // 4. close
        db.close();
    }
    // get schedule
    public Schedule getSchedule(int UserID){
        // 1. get reference to readable DB
        SQLiteDatabase db = this.getReadableDatabase();

        // 2. build query
        Cursor cursor =
                db.query(TABLE_SCHEDULE, // a. table
                        SCHEDULE_COLUMNS, // b. column names
                        " UserID = ?", // c. selections
                        new String[] { String.valueOf(UserID) }, // d. selections args
                        null, // e. group by
                        null, // f. having
                        null, // g. order by
                        null); // h. limit

        // 3. if we got results get the first one
        if (cursor.getCount() == 0){
            Schedule no_schedule = new Schedule(-1,-1);
            return no_schedule;
        }
        else {
            cursor.moveToFirst();

            // 4. build exercise object
            Schedule schedule = new Schedule();
            schedule.setUserID(Integer.parseInt(cursor.getString(0)));
            schedule.setCount(Integer.parseInt(cursor.getString(1)));

            //log
            Log.i("getSchedule("+UserID+")", schedule.toString());

            // 5. return exercise
            return schedule;
        }
    }

    // Add a new scheduled workout
    public void addScheduledWorkout(ScheduledWorkouts scheduledworkout){
        //for logging
        Log.i("addScheduledWorkout", scheduledworkout.toString());
        // 1. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();
        // 2. create Contentvalues to add key column
        ContentValues values = new ContentValues();
        values.put(SCHEDULED_WORKOUTS_KEY_WID, scheduledworkout.getWID());
        values.put(SCHEDULED_WORKOUTS_KEY_SID, scheduledworkout.getUserSID());
        values.put(SCHEDULED_WORKOUTS_KEY_DAYNO, scheduledworkout.getDayNo() + 1);
        // 3. insert
        try{
            db.insertOrThrow(TABLE_SCHEDULED_WORKOUTS,null,values);
            Schedule toupDate = this.getSchedule(UserValues.userID);
            toupDate.setCount(toupDate.getCount() + 1);
            this.updateScheduleCount(toupDate);
        }
        catch (android.database.sqlite.SQLiteException e){
            if (e.getMessage().toString().contains("no such table")){
                Log.e("No Table exception", "Creating table " + TABLE_SCHEDULED_WORKOUTS + "because it doesn't exist!" );
                // create table
                // re-run query, etc.
                //SQL statement to create scheduled workouts table
                String CREATE_SCHEDULED_WORKOUTS_TABLE = "CREATE TABLE scheduled_workouts ( " +
                        "WID INTEGER NOT NULL, " +
                        "UserID INTEGER NOT NULL, "+
                        "DayNo INTEGER, "+
                        "PRIMARY KEY(WID, UserID), "+
                        "FOREIGN KEY(WID) REFERENCES workout(WID) ON DELETE CASCADE, "+
                        "FOREIGN KEY(UserID) REFERENCES schedule(UserID) ON DELETE CASCADE" + ")";

                // create exercise table
                db.execSQL(CREATE_SCHEDULED_WORKOUTS_TABLE);
                db.insert(TABLE_SCHEDULED_WORKOUTS,null,values);
            }
        }
        // 4. close
        db.close();
    }

    // get schedule
    public ScheduledWorkouts getScheduledWorkout(int UserSID){
        // 1. get reference to readable DB
        SQLiteDatabase db = this.getReadableDatabase();

        // 2. build query
        Cursor cursor =
                db.query(TABLE_SCHEDULED_WORKOUTS, // a. table
                        SCHEDULED_WORKOUTS_COLUMNS, // b. column names
                        " UserSID = ?", // c. selections
                        new String[] { String.valueOf(UserSID) }, // d. selections args
                        null, // e. group by
                        null, // f. having
                        null, // g. order by
                        null); // h. limit

        // 3. if we got results get the first one
        if (cursor.getCount() == 0){
            ScheduledWorkouts no_scheduled = new ScheduledWorkouts(-1,-1,-1);
            return no_scheduled;
        }
        else {
            cursor.moveToFirst();

            // 4. build exercise object
            ScheduledWorkouts scheduled = new ScheduledWorkouts();
            scheduled.setWID(Integer.parseInt(cursor.getString(0)));
            scheduled.setUserSID(Integer.parseInt(cursor.getString(1)));
            scheduled.setDayNo(Integer.parseInt(cursor.getString(2)));

            //log
            Log.i("getSchedule("+UserSID+")", scheduled.toString());

            // 5. return exercise
            return scheduled;
        }
    }
    // Get All Scheduled Workouts
    public Cursor getAllScheduledWorkoutsList(String SQL_Where) {
        // 1. build the query
        String query = "select w.WID as _id, w.name from "+ TABLE_WORKOUT + SQL_Where;

        // 2. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        // return exercises
        cursor.moveToFirst();
        return cursor;
    }

    public void addExerciseHistory(ExerciseHistory exerciseH){
        //for logging
        Log.i("addExerciseHistory", exerciseH.toString());
        // 1. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();
        // 2. create Contentvalues to add key column
        ContentValues values = new ContentValues();
        values.put(EXERCISE_HISTORY_KEY_USERID, exerciseH.getUserID());
        values.put(EXERCISE_HISTORY_KEY_EXERCISEID, exerciseH.getExerciseID());
        values.put(EXERCISE_HISTORY_KEY_DATE, exerciseH.getDate());
        values.put(EXERCISE_HISTORY_KEY_RECORDS, exerciseH.getRecords());
        // 3. insert
        try{
            db.insertOrThrow(TABLE_EXERCISE_HISTROY,null,values);
        }
        catch (android.database.sqlite.SQLiteException e){
            if (e.getMessage().toString().contains("no such table")){
                Log.e("No Table exception", "Creating table " + TABLE_EXERCISE_HISTROY + "because it doesn't exist!" );
                // create table
                // re-run query, etc.
                String CREATE_EXERCISE_HISTORY_TABLE = "CREATE TABLE exercise_history ( " +
                        "UserID INTEGER, " +
                        "ExerciseID INTEGER, "+
                        "date text, "+
                        "records text, "+
                        "FOREIGN KEY(UserID) REFERENCES user(UID) ON DELETE CASCADE, "+
                        "FOREIGN KEY(ExerciseID) REFERENCES exercises(eID) ON DELETE CASCADE" + ")";

                // create exercise table
                db.execSQL(CREATE_EXERCISE_HISTORY_TABLE);
                db.insert(TABLE_EXERCISE_HISTROY,null,values);
            }
        }
        // 4. close
        db.close();
    }
    public Cursor getAllExerciseHistory(String UserID) {
        // 1. build the query
        //String query = "select UserID as _id, date, records  from "+ TABLE_EXERCISE_HISTROY + SQL_Where;
        String query = "select eh.UserID as _id, e.name as name, eh.date as date, eh.records as records from exercise_history as eh join exercises as e on eh.ExerciseID = e.eID where eh.UserID = " + UserID;
        // 2. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        // return exercises
        cursor.moveToFirst();
        return cursor;
    }
    public String getLastExerciseHistory(String UserID, String ExerciseID) {
        // 1. build the query
        //String query = "select UserID as _id, date, records  from "+ TABLE_EXERCISE_HISTROY + SQL_Where;
        String query = "select UserID as _id, max(date) as date, records  from "+ TABLE_EXERCISE_HISTROY + " where UserID = " + UserID +" and ExerciseID = "+ ExerciseID;
        // 2. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        // return exercises
        cursor.moveToFirst();
        String last_date = cursor.getString(cursor.getColumnIndexOrThrow("date"));
        String last_record = cursor.getString(cursor.getColumnIndexOrThrow("records"));
        return last_date+ " " +last_record;
    }

    public String[] getLastHealthHistory(String UserID) {
        // 1. build the query
        //String query = "select UserID as _id, date, records  from "+ TABLE_EXERCISE_HISTROY + SQL_Where;
        String query = "select UserID as _id, max(date) as date, weight, height  from "+ TABLE_HEALTH_HISTORY + " where UserID = " + UserID;
        // 2. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        // return exercises
        Log.i("CursorCount", String.valueOf(cursor.getCount()));
        cursor.moveToFirst();
        if(cursor.isNull(2) || cursor.isNull(3)) {
            return new String[]{"null","null"};
        }
        else{
            return new String[]{cursor.getString(cursor.getColumnIndexOrThrow("weight")), cursor.getString(cursor.getColumnIndexOrThrow("height"))};
        }
    }

    public void deleteScheduledWorkout(ScheduledWorkouts workout){
        // 1. get reference to writable db
        SQLiteDatabase db = this.getWritableDatabase();

        // 2. delete
        db.delete(TABLE_SCHEDULED_WORKOUTS,
                SCHEDULED_WORKOUTS_KEY_SID +"=? and "+ SCHEDULED_WORKOUTS_KEY_WID + "=?",
                new String[] {String.valueOf(workout.getUserSID()), String.valueOf(workout.getWID())});

        // 3. close
        db.close();

        //log
        Log.i("deleteWorkoutInSched", workout.toString());
    }
    public void deleteWeightGoal(Weight_goal wg){
        // 1. get reference to writable db
        SQLiteDatabase db = this.getWritableDatabase();

        // 2. delete
        db.delete(TABLE_WEIGHT_GOAL,
                WEIGHT_GOAL_KEY_USERID +"=?" ,
                new String[] {String.valueOf(wg.getUserID())});

        // 3. close
        db.close();

        //log
        Log.i("deleteWeighGoal", wg.toString());
    }

    public void addWeightGoal(Weight_goal wg){
        //for logging
        Log.i("addWeightGoal", wg.toString());
        // 1. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();
        // 2. create Contentvalues to add key column
        ContentValues values = new ContentValues();
        values.put(WEIGHT_GOAL_KEY_USERID, wg.getUserID());
        values.put(WEIGHT_GOAL_KEY_WEIGHT, wg.getWeight());
        values.put(WEIGHT_GOAL_KEY_TYPE, wg.getType());
        // 3. insert

        db.insert(TABLE_WEIGHT_GOAL,null,values);

        // 4. close
        db.close();
    }

    public String [] getWeightGoal(String UserID) {
        // 1. build the query
        //String query = "select UserID as _id, date, records  from "+ TABLE_EXERCISE_HISTROY + SQL_Where;
        String query = "select UserID as _id, weight, type  from "+ TABLE_WEIGHT_GOAL + " where UserID = " + UserID;
        // 2. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        // return exercises
        cursor.moveToFirst();
        if(cursor.getCount() == 0) {
            return new String[]{"null","null"};
        }
        else{
            return new String[]{cursor.getString(cursor.getColumnIndexOrThrow("weight")),cursor.getString(cursor.getColumnIndexOrThrow("type"))};
        }
    }

}
