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

public class ShowExercisesInWorkout extends AppCompatActivity {

    private String caller = "";
    private String WorkoutName = "";
    //Button ShowExerciseInWorkout_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Exercises in Workout");
        setContentView(R.layout.activity_show_exercises_in_workout);
        Bundle b = getIntent().getExtras();
        //ShowExerciseInWorkout_button = (Button) findViewById(R.id.AddExerciseHistoryButton);
        //ShowExerciseInWorkout_button.setOnClickListener(new View.OnClickListener()
        //{
            //@Override
           // public void onClick(View v)
            //{
                //GoToAddExerciseHistoryPage();
            //}
        //});
        if(b!=null)
        {
            WorkoutName =(String) b.get("WorkoutName");
            caller = (String) b.get("caller");
            //if(caller.equals("DisplaySchedule"))
                //ShowExerciseInWorkout_button.setVisibility(View.VISIBLE);
        }
        String SQL_Where = " where eID in (select eID from workout as w join exercises_in_workout as ew on w.WID = ew.WID where w.name = '" + WorkoutName + "')";
        final MySQLiteHelper db = new MySQLiteHelper(this);
        Cursor todoCursor = db.getExercisesInWorkout(SQL_Where);
        if( todoCursor.getCount() == 0)
            setContentView(R.layout.no_exercises);
        else{// Find ListView to populate
            ListView Items = (ListView) findViewById(R.id.Exerciselistview);
            // Setup cursor adapter using cursor from last step
            ExerciseListAdapter todoAdapter = new ExerciseListAdapter(this, todoCursor);
            // Attach cursor adapter to the ListView
            Items.setAdapter(todoAdapter);
            Items.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    if(caller.equals("DisplaySchedule")) {
                        String selected = ((TextView) view.findViewById(R.id.exercise_name)).getText().toString();
                        Exercise exercise_selected = db.getExercise(selected);
                        GoToAddExerciseHistoryPage(exercise_selected);
                        Log.i("CheckingforListResponse", selected);
                    }
                }
            });
        }
    }

    public void GoToAddExerciseHistoryPage(Exercise exercise){
        if(exercise.getType().equals("Set/Rep")){
            Intent intent = new Intent(this, AddExerciseHistory.class);
            intent.putExtra("WorkoutName",WorkoutName);
            intent.putExtra("caller","DisplaySchedule");
            intent.putExtra("exerciseName", exercise.getName());
            intent.putExtra("exerciseType", exercise.getType());
            startActivity(intent);}
        else if(exercise.getType().equals("Time")){
            Intent intent = new Intent(this, AddExerciseHistory.class);
            intent.putExtra("WorkoutName",WorkoutName);
            intent.putExtra("caller","DisplaySchedule");
            intent.putExtra("exerciseName", exercise.getName());
            intent.putExtra("exerciseType", exercise.getType());
            startActivity(intent);
        }
    }
    @Override
    public void onBackPressed()
    {
        if(caller.equals("DisplaySchedule")){
            Intent intent = new Intent(this, DisplaySchedule.class);
            startActivity(intent);}
        else if(caller.equals("WorkoutsPage")){
            Intent intent = new Intent(this, WorkoutsPage.class);
            startActivity(intent);
    }
}
}

/*


 */