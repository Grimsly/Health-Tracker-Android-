package com.cpsc471.cpsc471project;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class SignUp extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Sign Up");
        setContentView(R.layout.activity_sign_up);
    }

    public void signUp(View view) {
        EditText editTextU = (EditText) findViewById(R.id.new_username);        //Gets the string in the username
        String username = editTextU.getText().toString();
        EditText editTextP = (EditText) findViewById(R.id.new_password);          //Gets string from password
        String password = editTextP.getText().toString();
        EditText editTextNP = (EditText) findViewById(R.id.check_password);          //Gets string from password
        String password_check = editTextNP.getText().toString();
        if(!username.isEmpty() && !password.isEmpty()){
            if (password.equals(password_check)) {
            MySQLiteHelper db = new MySQLiteHelper(this);
            if (db.getUser(username).getUserID() == -1) {
                User new_user = new User(username, password, 0);
                db.addUser(new_user);
                new_user = db.getUser(new_user.getUsername());
                db.addSchedule(new Schedule(new_user.getUserID(), 0));
                db.close();
                Intent intent = new Intent(this, loggingIn.class);
                startActivity(intent);
            }
            else {
                //Popup user already exists
                editTextU.setText("");
                AlertDialog alertDialog = new AlertDialog.Builder(SignUp.this).create();
                alertDialog.setTitle("Sign up Error");
                alertDialog.setMessage("Username already exists. Please try another.");
                alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });
                alertDialog.show();
            }
        } else {
                //Popup password don't match
                AlertDialog alertDialog = new AlertDialog.Builder(SignUp.this).create();
                alertDialog.setTitle("Sign up Error");
                alertDialog.setMessage("Passwords don't match.");
                alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });
                alertDialog.show();
                editTextP.setText("");
                editTextNP.setText("");
        }
    }
    }
}
