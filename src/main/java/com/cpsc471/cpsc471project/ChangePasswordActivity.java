package com.cpsc471.cpsc471project;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class ChangePasswordActivity extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);
        setTitle("Change Password");

        final EditText newPassword_EditText = (EditText) findViewById(R.id.newPassword_editText);
        final EditText newPasswordConfirm_EditText = (EditText) findViewById(R.id.newPasswordConfirm_editText);
        Button ChangePassword_confirmButton = (Button) findViewById(R.id.ChangePassword_confirmButton);
        ChangePassword_confirmButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                final String newPassword = newPassword_EditText.getText().toString();
                String newPassword_reentered = newPasswordConfirm_EditText.getText().toString();

                if(newPassword.equals(newPassword_reentered)) {
                    AlertDialog alertDialog = new AlertDialog.Builder(ChangePasswordActivity.this).create();
                    alertDialog.setTitle("Password change confirmed");
                    alertDialog.setMessage("Password changed");
                    alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    changePassword(newPassword);
                                    dialog.dismiss();
                                }
                            });
                    alertDialog.show();
                }
                else {
                    AlertDialog alertDialog = new AlertDialog.Builder(ChangePasswordActivity.this).create();
                    alertDialog.setTitle("Error changing password");
                    alertDialog.setMessage("Password's do not match");
                    alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            });
                    alertDialog.show();
                }
            }
        });
    }

    public void changePassword(String Password)
    {
        MySQLiteHelper db = new MySQLiteHelper(this);
        User thisUser = db.getUser(UserValues.username);
        thisUser.setPassword(Password);
        int temp = db.updateUser(thisUser);
        thisUser.setPassword(Password);
        Intent intent = new Intent(this, DisplayProfile.class);
        startActivity(intent);
    }
}
