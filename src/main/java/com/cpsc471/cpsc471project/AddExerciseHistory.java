package com.cpsc471.cpsc471project;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AddExerciseHistory extends AppCompatActivity {

    private String caller = "";
    private String WorkoutName = "";
    private String exerciseName = "";
    private String exerciseType = "";
    private String lastRecord= "";
    private int exercieID= -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Add Exercise Record");
        MySQLiteHelper db = new MySQLiteHelper(this);
        Bundle b = getIntent().getExtras();
        if(b!=null)
        {
            WorkoutName =(String) b.get("WorkoutName");
            caller = (String) b.get("caller");
            exerciseName = (String) b.get("exerciseName");
            exerciseType = (String) b.get("exerciseType");

        }
        exercieID = db.getExercise(exerciseName).getId();
        if(exerciseType.equals("Set/Rep"))
            setContentView(R.layout.activity_add_exercise_history);
        else if(exerciseType.equals("Time")) {
            setContentView(R.layout.activity_add_time_exercise_history);
            Spinner s = (Spinner) findViewById(R.id.SpinnerSpcial);
            String[] type_items = new String[]{"Sec", "Min"};
            ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, type_items);
            s.setAdapter(adapter);
        }
        TextView setHeader = (TextView)findViewById(R.id.NewExerciseRecord_header);
        setHeader.setText(exerciseName);
        setLastRecord();
    }

    private void setLastRecord(){
        MySQLiteHelper db = new MySQLiteHelper(this);
        TextView setLastRecord = (TextView)findViewById(R.id.lastRecord);
        String last_record = db.getLastExerciseHistory(String.valueOf(UserValues.userID),String.valueOf(exercieID));
        if(last_record.contains("null"))
            setLastRecord.setText("No History");
        else
            setLastRecord.setText(last_record);
        db.close();
    }

    public void submitSetRephistory(View view){
        EditText setsText = (EditText) findViewById(R.id.setsRecord_editText);
        EditText repsText = (EditText) findViewById(R.id.repsRecord_editText);
        EditText weightText = (EditText) findViewById(R.id.weightRecord_editText);
        String sets = setsText.getText().toString();
        String reps = repsText.getText().toString();
        String weight = weightText.getText().toString();
        MySQLiteHelper db = new MySQLiteHelper(this);
        if((sets.equals(null) || sets.equals("") || reps.equals(null) || reps.equals("") || weight.equals(null) || weight.equals(""))) {
            Log.i("createExerciseHist", "One textbox missing data");
        }
        else {
            Log.i("createExerciseHist", "All textbox have data");
            String record = "Sets:" +sets + " Reps:" + reps + " Weight:"+ weight+"KG";
            ExerciseHistory e = new ExerciseHistory();
            e.setUserID(UserValues.userID);
            //e.setExerciseID(db.getExercise(exerciseName).getId());
            e.setExerciseID(exercieID);
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date date = new Date();
            //e.setDate(DateFormat.getDateTimeInstance().format(new Date()));
            e.setDate(df.format(date));
            e.setRecords(record);
            db.addExerciseHistory(e);
            Intent intent = new Intent(this, ShowExercisesInWorkout.class);
            intent.putExtra("WorkoutName",WorkoutName);
            intent.putExtra("caller","DisplaySchedule");
            startActivity(intent);}
    }

    public void submitTimehistory(View view){
        EditText timeText = (EditText) findViewById(R.id.timeRecord_editText);
        String time = timeText.getText().toString();
        Spinner timeUnit = (Spinner) findViewById(R.id.SpinnerSpcial);
        String timeUnit_toadd = timeUnit.getSelectedItem().toString();
        MySQLiteHelper db = new MySQLiteHelper(this);
        if((time.equals(null) || time.equals("") )) {
            Log.i("createExerciseHist", "One textbox missing data");
        }
        else {
            Log.i("createExerciseHist", "All textbox have data");
            String record = time +" "+ timeUnit_toadd;
            ExerciseHistory e = new ExerciseHistory();
            e.setUserID(UserValues.userID);
            e.setExerciseID(db.getExercise(exerciseName).getId());
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date date = new Date();
            //e.setDate(DateFormat.getDateTimeInstance().format(new Date()));
            e.setDate(df.format(date));
            e.setRecords(record);
            db.addExerciseHistory(e);
            Intent intent = new Intent(this, ShowExercisesInWorkout.class);
            intent.putExtra("WorkoutName",WorkoutName);
            intent.putExtra("caller","DisplaySchedule");
            startActivity(intent);}
    }

}
