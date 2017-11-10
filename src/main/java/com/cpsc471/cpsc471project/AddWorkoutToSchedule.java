package com.cpsc471.cpsc471project;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

public class AddWorkoutToSchedule extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Select Workouts");
        setContentView(R.layout.activity_add_workout_to_schedule);
        final MySQLiteHelper db = new MySQLiteHelper(this);
        Cursor todoCursor = db.getAllWorkouts();
        if( todoCursor.getCount() == 0)
            setContentView(R.layout.no_workouts);
        else{
            ListView Items = (ListView) findViewById(R.id.AddWorkoutlistview);
            WorkoutListAdapter todoAdapter = new WorkoutListAdapter(this,todoCursor);
            Items.setAdapter(todoAdapter);
            Items.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    String selected = ((TextView) view.findViewById(R.id.workout_name)).getText().toString();
                    Log.i("CheckingforListResponse", selected);
                    //Need to update schedule count
                    Schedule toupDate = db.getSchedule(UserValues.userID);
                    //toupDate.setCount(toupDate.getCount() + 1);
                    //db.updateScheduleCount(toupDate);
                    //Add workout to schedule
                    Workout toAdd = db.getWorkout(selected);
                    db.addScheduledWorkout(new ScheduledWorkouts(toAdd.getID(),UserValues.userID,toupDate.getCount()));
                }
            });
        }
    }
    public void confirmSchedule(View view){
        Intent intent = new Intent(this, DisplaySchedule.class);
        startActivity(intent);
    }
}
