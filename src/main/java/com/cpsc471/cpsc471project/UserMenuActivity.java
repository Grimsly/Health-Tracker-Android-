package com.cpsc471.cpsc471project;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class UserMenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("User Menu");
        setContentView(R.layout.activity_user_menu);
    }

    public void gotoExercise(View view){
        Intent intent = new Intent(this, DisplayExercise.class);
        startActivity(intent);
    }

    public void gotoHealth(View view){
        Intent intent = new Intent(this, DisplayHealth.class);
        startActivity(intent);
    }

    public void gotoSchedule(View view){
        Intent intent = new Intent(this, DisplaySchedule.class);
        startActivity(intent);
    }

    public void gotoProfile(View view){
        Intent intent = new Intent(this, DisplayProfile.class);
        startActivity(intent);
    }

    public void gotoBMI(View view){
        Intent intent = new Intent(this, ShowBMI.class);
        startActivity(intent);
    }

    public void gotoWeightGoal(View view){
        Intent intent = new Intent(this, WeightGoal.class);
        startActivity(intent);
    }

    @Override
    public void onBackPressed()
    {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

}
