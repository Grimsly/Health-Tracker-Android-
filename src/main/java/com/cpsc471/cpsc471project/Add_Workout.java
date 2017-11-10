package com.cpsc471.cpsc471project;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


public class Add_Workout extends AppCompatActivity
{

    EditText WorkoutName_Textbox;
    Button confirm_button;
    Button addExercise_button;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setTitle("New Workout");
        setContentView(R.layout.activity_add__workout);

        WorkoutName_Textbox = (EditText) findViewById(R.id.Workout_Name_TextView);

    }

    public void AddExerciseToWorkout(View view) {
        final MySQLiteHelper db = new MySQLiteHelper(this);
        Cursor checkExercises = db.getAllExercisesNames("");
        if (checkExercises.getCount() == 0){
            AlertDialog alertDialog = new AlertDialog.Builder(Add_Workout.this).create();
            alertDialog.setTitle("Error Creating Workout");
            alertDialog.setMessage("Please add exercises to database before creating a workout");
            alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
            alertDialog.show();
        }
        else{
            EditText nameText = (EditText) findViewById(R.id.Workout_Name_TextView);
            if ((nameText.getText().toString().equals(null) || nameText.getText().toString().equals("")))
                Log.i("deleteExercise", "One textbox missing data");
            else {
                try {
                    Workout workout = db.getWorkout(nameText.getText().toString());
                    nameText.setText("");
                    AlertDialog alertDialog = new AlertDialog.Builder(Add_Workout.this).create();
                    alertDialog.setTitle("Error adding workout");
                    alertDialog.setMessage("Workout already in Database");
                    alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            });
                    alertDialog.show();
                } catch (Exception e) {
                    db.addWorkout(new Workout(nameText.getText().toString()));
                    Workout workout = db.getWorkout(nameText.getText().toString());
                    db.close();
                    Intent intent = new Intent(this, AddExerciseToWorkout.class);
                    intent.putExtra("WID", workout.getID());
                    startActivity(intent);
                }
            }// Launch a new activity to add an exercise to the new workout

        }
    }
}
