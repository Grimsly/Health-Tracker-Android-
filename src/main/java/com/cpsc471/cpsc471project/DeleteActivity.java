package com.cpsc471.cpsc471project;

import android.content.DialogInterface;
import android.database.Cursor;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

public class DeleteActivity extends AppCompatActivity {

    private Cursor todoCursor;
    private final MySQLiteHelper db = new MySQLiteHelper(this);;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Delete Tool");
        setContentView(R.layout.activity_delete);
        int id = getIntent().getIntExtra("button_id",0);
        if(id == R.id.Admin_deleteExerciseButton) {
            displayExercises();
        }
        else if(id == R.id.Admin_deleteUserButton) {
            displayUsers();
        }
        else if(id == R.id.Admin_deleteWorkoutButton) {
            displayWorkouts();
        }


    }
    private void displayExercises(){
        todoCursor = db.getAllExercisesNames("");
        if( todoCursor.getCount() == 0)
            setContentView(R.layout.no_exercises);
        else{
            ListView Items = (ListView) findViewById(R.id.Userlistview);
            // Setup cursor adapter using cursor from last step
            ExerciseListAdapter todoAdapter = new ExerciseListAdapter(this, todoCursor);
            // Attach cursor adapter to the ListView
            Items.setAdapter(todoAdapter);
            Items.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    final String selected = ((TextView) view.findViewById(R.id.exercise_name)).getText().toString();
                    Log.i("CheckingforListResponse", selected);
                    AlertDialog alertDialog = new AlertDialog.Builder(DeleteActivity.this).create();
                    alertDialog.setTitle("Confirm Delete Exercise ("+ selected +")");
                    alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, "Cancel",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            });
                    alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "Yes",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    Exercise to_delete = db.getExercise(selected);
                                    db.deleteExercise(to_delete);
                                    dialog.dismiss();
                                    displayExercises();
                                }
                            });
                    alertDialog.show();
                }
            });
        }
    }

    private void displayUsers(){
        todoCursor = db.getAllUsers();
        if( todoCursor.getCount() == 0)
            setContentView(R.layout.no_users);
        else {
            ListView Items = (ListView) findViewById(R.id.Userlistview);
            // Setup cursor adapter using cursor from last step
            UserListAdapter todoAdapter = new UserListAdapter(this, todoCursor);
            // Attach cursor adapter to the ListView
            Items.setAdapter(todoAdapter);
            Items.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    final String selected = ((TextView) view.findViewById(R.id.user_name)).getText().toString();
                    Log.i("CheckingforListResponse", selected);
                    AlertDialog alertDialog = new AlertDialog.Builder(DeleteActivity.this).create();
                    alertDialog.setTitle("Confirm Delete User ("+ selected +")");
                    alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, "Cancel",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            });
                    alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "Yes",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    User to_delete = db.getUser(selected);
                                    db.deleteUser(to_delete);
                                    dialog.dismiss();
                                    displayUsers();
                                }
                            });
                    alertDialog.show();
                }
            });
        }
    }

    private void displayWorkouts(){
        todoCursor = db.getAllWorkouts();
        if( todoCursor.getCount() == 0)
            setContentView(R.layout.no_workouts);
        else{
            ListView Items = (ListView) findViewById(R.id.Userlistview);
            // Setup cursor adapter using cursor from last step
            WorkoutListAdapter todoAdapter = new WorkoutListAdapter(this, todoCursor);
            // Attach cursor adapter to the ListView
            Items.setAdapter(todoAdapter);
            Items.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    final String selected = ((TextView) view.findViewById(R.id.workout_name)).getText().toString();
                    Log.i("CheckingforListResponse", selected);
                    AlertDialog alertDialog = new AlertDialog.Builder(DeleteActivity.this).create();
                    alertDialog.setTitle("Confirm Delete Workout ("+ selected +")");
                    alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, "Cancel",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            });
                    alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "Yes",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    Workout to_delete = db.getWorkout(selected);
                                    db.deleteWorkout(to_delete);
                                    dialog.dismiss();
                                    displayWorkouts();

                                }
                            });
                    alertDialog.show();
                }
            });
        }
    }

}
