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

public class WorkoutsPage extends AppCompatActivity
{
    Button AddWorkoutButton;
    Button MainMenu_admin_button;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setTitle("Workouts");
        setContentView(R.layout.activity_workouts_page);

        AddWorkoutButton = (Button) findViewById(R.id.AddWorkoutButton);
        AddWorkoutButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                AddWorkout();
            }
        });

        AddWorkoutButton.setVisibility(View.VISIBLE);

        MySQLiteHelper db = new MySQLiteHelper(this);
        Cursor todoCursor = db.getAllWorkouts();
        if( todoCursor.getCount() == 0) {
            setContentView(R.layout.no_workouts);
            AddWorkoutButton = (Button) findViewById(R.id.AddWorkoutButton);
            AddWorkoutButton.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v)
                {
                    AddWorkout();
                }
            });
            AddWorkoutButton.setVisibility(View.VISIBLE);
        }
        else{// Find ListView to populate
            ListView Items = (ListView) findViewById(R.id.Workoutlistview);
            // Setup cursor adapter using cursor from last step
            WorkoutListAdapter todoAdapter = new WorkoutListAdapter(this, todoCursor);
            // Attach cursor adapter to the ListView
            Items.setAdapter(todoAdapter);
            Items.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    String selected = ((TextView) view.findViewById(R.id.workout_name)).getText().toString();
                    Log.i("CheckingforListResponse", selected);
                    ShowExercises(selected);
                }
            });
        }
    }

    public void ShowExercises(String pressedWorkout){
        Intent ShowExercises_Intent = new Intent(this, ShowExercisesInWorkout.class);
        ShowExercises_Intent.putExtra("WorkoutName",pressedWorkout);
        ShowExercises_Intent.putExtra("caller","WorkoutsPage");
        startActivity(ShowExercises_Intent);
    }

    public void AddWorkout()
    {
        Intent AddWorkout_Intent = new Intent(this, Add_Workout.class);
        startActivity(AddWorkout_Intent);
    }
    @Override
    public void onBackPressed()
    {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
