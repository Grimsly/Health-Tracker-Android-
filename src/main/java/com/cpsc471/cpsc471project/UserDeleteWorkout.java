package com.cpsc471.cpsc471project;

import android.content.DialogInterface;
import android.database.Cursor;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

public class UserDeleteWorkout extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Remove Workout from Schedule");
        setContentView(R.layout.activity_user_delete_workout);
        displayWorkoutList();
    }

    private void displayWorkoutList(){
        final MySQLiteHelper db = new MySQLiteHelper(this);
        String sqlStatement = " as w join scheduled_workouts as sw on w.WID = sw.WID where sw.UserID = " +String.valueOf(UserValues.userID)+ " order by sw.DayNo ASC;";
        Cursor todoCursor = db.getAllScheduledWorkoutsList(sqlStatement);
        if(todoCursor.getCount() == 0)
            setContentView(R.layout.no_workouts_schedule);
        else{// Find ListView to populate
            ListView Items = (ListView) findViewById(R.id.ScheduledWorkoutslistview);
            // Setup cursor adapter using cursor from last step
            ScheduledWorkoutsListAdapter todoAdapter = new ScheduledWorkoutsListAdapter(this, todoCursor);
            // Attach cursor adapter to the ListView
            Items.setAdapter(todoAdapter);
            Items.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    final String selected = ((TextView) view.findViewById(R.id.workout_name)).getText().toString();
                    AlertDialog alertDialog = new AlertDialog.Builder(UserDeleteWorkout.this).create();
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
                                    ScheduledWorkouts sw = new ScheduledWorkouts(db.getWorkout(selected).getID(),UserValues.userID,-1);
                                    db.deleteScheduledWorkout(sw);
                                    Schedule toupDate = db.getSchedule(UserValues.userID);
                                    toupDate.setCount(toupDate.getCount() - 1);
                                    db.updateScheduleCount(toupDate);
                                    dialog.dismiss();
                                    displayWorkoutList();
                                }
                            });
                    alertDialog.show();
                }
            });}
    }
}
