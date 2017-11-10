package com.cpsc471.cpsc471project;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class loggingIn extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Login");
        setContentView(R.layout.activity_logging_in);
    }

    public void logIn(View view){
        EditText editTextP = (EditText) findViewById(R.id.new_username);        //Gets the string in the username
        String username = editTextP.getText().toString();
        EditText editTextU = (EditText) findViewById(R.id.check_password);          //Gets string from password
        String password = editTextU.getText().toString();
        if(!username.isEmpty() && !password.isEmpty()) {
            MySQLiteHelper db = new MySQLiteHelper(this);
            User user_check = db.getUser(username);
            db.close();
            if (user_check.getPassword().equals(password)) {
                UserValues.username = username;         //Change the values of the shared values
                UserValues.loggedIn = true;
                UserValues.userID = user_check.getUserID();
                UserValues.privilege = user_check.getPrivilege();
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
            } else {
                editTextU.setText("");
                AlertDialog alertDialog = new AlertDialog.Builder(loggingIn.this).create();
                alertDialog.setTitle("Login Error");
                alertDialog.setMessage("Incorrect Username/Password");
                alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });
                alertDialog.show();
            }
        }
        //end else statement
    }

    public void signUp(View view){
        Intent intent = new Intent(this, SignUp.class);
        startActivity(intent);
    }

    @Override
    public void onBackPressed()
    {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
