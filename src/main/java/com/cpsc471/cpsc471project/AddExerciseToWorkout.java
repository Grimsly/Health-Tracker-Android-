package com.cpsc471.cpsc471project;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

public class AddExerciseToWorkout extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Select Exercises");
        setContentView(R.layout.activity_add_exercise_to_workout);
        final int WID = getIntent().getIntExtra("WID",0);
        String SQL_exercise = " order by name";
        final MySQLiteHelper db = new MySQLiteHelper(this);
        Cursor todoCursor = db.getAllExercisesNames(SQL_exercise);
        if(todoCursor.getCount() == 0)
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
                    String selected = ((TextView) view.findViewById(R.id.exercise_name)).getText().toString();
                    Log.i("CheckingforListResponse", selected);
                    Exercise exercise = db.getExercise(selected);
                    ExercisesInWorkout exerciseToAdd = new ExercisesInWorkout(WID, exercise.getId());
                    db.addExerciseToWorkout(exerciseToAdd);
                }
            });
        }

    }
    public void confirmWorkout(View view){
        Intent intent = new Intent(this, WorkoutsPage.class);
        startActivity(intent);
    }
    @Override
    public void onBackPressed()
    {

        //thats it
    }
}
