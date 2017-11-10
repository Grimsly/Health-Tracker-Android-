package com.cpsc471.cpsc471project;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class DisplayProfile extends AppCompatActivity {

    MySQLiteHelper db;
    Cursor cursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_profile);
        setTitle("Profile");

        db = new MySQLiteHelper(this);

        TextView userProfile_header = (TextView) findViewById(R.id.DisplayProfile_header);
        userProfile_header.setText(UserValues.username);

        Button changePassword_button = (Button) findViewById(R.id.changePassword_button);
        changePassword_button.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                // Change password for this user
                changePassword();
            }
        });

        Button deleteUser_button = (Button) findViewById(R.id.DisplayProfile_deleteUserButton);
        deleteUser_button.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                // Delete this user
                AlertDialog alertDialog = new AlertDialog.Builder(DisplayProfile.this).create();
                alertDialog.setTitle("Confirm Delete User");
                alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, "Cancel",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });
                alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "Yes",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                deleteThisUser();
                                dialog.dismiss();

                            }
                        });
                alertDialog.show();
            }
        });
    }

    public void changePassword()
    {
        Intent changePassword_intent = new Intent (this, ChangePasswordActivity.class);
        startActivity(changePassword_intent);
    }

    public void deleteThisUser()
    {
        User userToDelete = db.getUser(UserValues.username);
        db.deleteUser(userToDelete);
        UserValues.log_out();
        Intent backToMain_intent = new Intent(this, MainActivity.class);
        startActivity(backToMain_intent);
    }
    public void deleteWorkout(View view){
        Intent intent = new Intent(this, UserDeleteWorkout.class);
        startActivity(intent);
    }
}
