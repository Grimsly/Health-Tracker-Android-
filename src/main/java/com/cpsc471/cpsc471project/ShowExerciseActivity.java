package com.cpsc471.cpsc471project;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import com.cpsc471.cpsc471project.MySQLiteHelper;
import java.util.List;

public class ShowExerciseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Exercises");
        setContentView(R.layout.activity_show_exercise);
        int id = getIntent().getIntExtra("button_id",0);
        String SQL_exercise = " where muscleGroup = 'Nothing'";
        if(id == R.id.cardio)
            SQL_exercise = " where muscleGroup = 'Cardio'";
        else if(id == R.id.chest)
            SQL_exercise = " where muscleGroup = 'Chest'";
        else if(id == R.id.back)
            SQL_exercise = " where muscleGroup = 'Back'";
        else if(id == R.id.shoulder)
            SQL_exercise = " where muscleGroup = 'Shoulder'";
        else if(id == R.id.arm)
            SQL_exercise = " where muscleGroup = 'Arm'";
        else if(id == R.id.leg)
            SQL_exercise = " where muscleGroup = 'Leg'";
        MySQLiteHelper db = new MySQLiteHelper(this);
        Cursor todoCursor = db.getAllExercisesNames(SQL_exercise);
        if( todoCursor.getCount() == 0)
            setContentView(R.layout.no_exercises);
        else{// Find ListView to populate
            ListView Items = (ListView) findViewById(R.id.Exerciselistview);
            // Setup cursor adapter using cursor from last step
            ExerciseListAdapter todoAdapter = new ExerciseListAdapter(this, todoCursor);
            // Attach cursor adapter to the ListView
            Items.setAdapter(todoAdapter);}
    }
}