package com.cpsc471.cpsc471project;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import com.cpsc471.cpsc471project.Exercise;
import com.cpsc471.cpsc471project.MySQLiteHelper;

public class AddExerciseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("New Exercise");
        setContentView(R.layout.activity_add_exercise);

        Spinner muscleGroup_dropdown = (Spinner)findViewById(R.id.muscleGroup_spinner);
        String[] muscleGroup_items = new String[]{"Arm","Back","Cardio","Chest","Leg","Shoulder"};
        ArrayAdapter<String> muscleGroup_adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, muscleGroup_items);
        muscleGroup_dropdown.setAdapter(muscleGroup_adapter);

        Spinner type_dropdown = (Spinner)findViewById(R.id.type_spinner);
        String[] type_items = new String[]{"Set/Rep", "Time"};
        ArrayAdapter<String> type_adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, type_items);
        type_dropdown.setAdapter(type_adapter);
    }

    public void submitexercise(View view){
        EditText nameText = (EditText) findViewById(R.id.add_name);
        //EditText mgText = (EditText) findViewById(R.id.add_muscleGroup);
        Spinner muscleGroupSpinner = (Spinner) findViewById(R.id.muscleGroup_spinner);
        String muscleGroupText = muscleGroupSpinner.getSelectedItem().toString();
        Spinner typeSpinner = (Spinner) findViewById(R.id.type_spinner);
        String typeText = typeSpinner.getSelectedItem().toString();
        MySQLiteHelper db = new MySQLiteHelper(this);
        if((nameText.getText().toString().equals(null) || nameText.getText().toString().equals(""))) {
            Log.i("deleteExercise", "One textbox missing data");
        }
        else {
            try{
                Exercise exercise = db.getExercise(nameText.getText().toString());
                nameText.setText("");
                AlertDialog alertDialog = new AlertDialog.Builder(AddExerciseActivity.this).create();
                alertDialog.setTitle("Error adding exercise");
                alertDialog.setMessage("Exercise already in Database");
                alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });
                alertDialog.show();
            }catch(Exception ex){
            Log.i("deleteExercise", "All textbox have data");
            Exercise e = new Exercise();
            e.setName(nameText.getText().toString());
            e.setMuscleGroup(muscleGroupText);
            e.setType(typeText);
            db.addExercise(e);
            Intent intent = new Intent(this, MuscleGroup.class);
            startActivity(intent);}
        }
    }
}
