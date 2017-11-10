package com.cpsc471.cpsc471project;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

public class DisplaySchedule extends AppCompatActivity {

    Button Add_workout_to_schedule_button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MySQLiteHelper db = new MySQLiteHelper(this);
        Schedule user_schedule = db.getSchedule(UserValues.userID);
        setTitle(UserValues.username + "'s Schedule: " + user_schedule.getCount()+ " Workouts");
        setContentView(R.layout.activity_display_schedule);
        int scheduleUserID = UserValues.userID;
        String sqlStatement = " as w join scheduled_workouts as sw on w.WID = sw.WID where sw.UserID = " +String.valueOf(UserValues.userID)+ " order by sw.DayNo ASC;";
        Cursor todoCursor = db.getAllScheduledWorkoutsList(sqlStatement);
        if(todoCursor.getCount() == 0) {
            setContentView(R.layout.no_workouts_schedule);
            Add_workout_to_schedule_button = (Button) findViewById(R.id.AddWorkoutToScheduleButton);
            Add_workout_to_schedule_button.setVisibility(View.VISIBLE);
        }
        else{// Find ListView to populate
            ListView Items = (ListView) findViewById(R.id.ScheduledWorkoutslistview);
            // Setup cursor adapter using cursor from last step
            ScheduledWorkoutsListAdapter todoAdapter = new ScheduledWorkoutsListAdapter(this, todoCursor);
            // Attach cursor adapter to the ListView
            Items.setAdapter(todoAdapter);
            Items.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    String selected = ((TextView) view.findViewById(R.id.workout_name)).getText().toString();
                    ShowScheduledExercises(selected);
                }
            });}

    }

    public void AddWorkoutSchedule(View view){
        Intent intent = new Intent(this, AddWorkoutToSchedule.class);
        startActivity(intent);
    }
    public void ShowScheduledExercises(String workoutName){
        Log.i("CheckingforListResponse", workoutName);
        Intent ShowExercises_Intent = new Intent(this, ShowExercisesInWorkout.class);
        ShowExercises_Intent.putExtra("WorkoutName",workoutName);
        ShowExercises_Intent.putExtra("caller","DisplaySchedule");
        startActivity(ShowExercises_Intent);
    }
    @Override
    public void onBackPressed()
    {
        Intent intent = new Intent(this, UserMenuActivity.class);
        startActivity(intent);
    }
}


