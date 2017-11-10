package com.cpsc471.cpsc471project;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import java.util.List;
import com.cpsc471.cpsc471project.Exercise;
import com.cpsc471.cpsc471project.Workout;
import com.cpsc471.cpsc471project.MySQLiteHelper;

public class MuscleGroup extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Exercises");
        setContentView(R.layout.activity_muscle_group);
        //Example to populate database and test functions



    }

    public void showexercisetype(View view){
        Intent intent = new Intent(this, ShowExerciseActivity.class);
        intent.putExtra("button_id", view.getId());
        startActivity(intent);
    }
    public void addexercise(View view){
        Intent intent = new Intent(this, AddExerciseActivity.class);
        startActivity(intent);
    }

    @Override
    public void onBackPressed()
    {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}

