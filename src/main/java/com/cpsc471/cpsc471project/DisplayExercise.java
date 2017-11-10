package com.cpsc471.cpsc471project;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

public class DisplayExercise extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Exercise History");
        setContentView(R.layout.activity_display_exercise);
        MySQLiteHelper db = new MySQLiteHelper(this);
        String SQL_exercise_history = "'"+UserValues.userID+"'";
        Cursor todo = db.getAllExerciseHistory(SQL_exercise_history);
        if( todo.getCount() == 0)
            setContentView(R.layout.no_exercise_history);
        else{// Find ListView to populate
            ListView Items = (ListView) findViewById(R.id.ExerciseHistorylistview);
            // Setup cursor adapter using cursor from last step
            DisplayExerciseHistoryAdapter todoAdapter = new DisplayExerciseHistoryAdapter(this, todo);
            // Attach cursor adapter to the ListView
            Items.setAdapter(todoAdapter);}

    }
}
