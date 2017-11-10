package com.cpsc471.cpsc471project;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity
{

    Button MainMenu_exercises_button;
    Button MainMenu_workouts_button;
    Button MainMenu_userInfo_button;
    Button MainMenu_Login_button;
    Button MainMenu_admin_button;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setTitle("Main Menu");
        setContentView(R.layout.activity_main);
        MySQLiteHelper db = new MySQLiteHelper(this);
        db.addUser(new User("Admin","password", 1));
        db.close();

        MainMenu_exercises_button = (Button) findViewById(R.id.MainMenu_exercises_button);
        MainMenu_exercises_button.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                GoToexercises();// Go to Exercises page
            }
        });

        MainMenu_workouts_button = (Button) findViewById(R.id.MainMenu_workouts_button);
        MainMenu_workouts_button.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                // Go to Workouts page
                GoToWorkoutsPage();
            }
        });

        MainMenu_admin_button = (Button) findViewById(R.id.MainMenu_admin_button);
        if(UserValues.privilege == 1)
            MainMenu_admin_button.setVisibility(View.VISIBLE);
        MainMenu_admin_button.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                // Go to Admin page
                GoToAdminPage();
            }
        });

        MainMenu_userInfo_button = (Button) findViewById(R.id.MainMenu_userInfo_button);
        if(UserValues.loggedIn == true)
            MainMenu_userInfo_button.setVisibility(View.VISIBLE);
        MainMenu_userInfo_button.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                GoToUserMenu();// Go to User Info page
            }
        });

        MainMenu_Login_button = (Button) findViewById(R.id.MainMenu_login_button);
        if(UserValues.loggedIn == false)
            MainMenu_Login_button.setText("LOGIN");
        else if(UserValues.loggedIn == true)
            MainMenu_Login_button.setText("LOGOUT");
        MainMenu_Login_button.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if(UserValues.loggedIn == false)
                    GoToLogin();// Go to login page
                else if(UserValues.loggedIn == true){
                    UserValues.log_out();
                    startActivity(getIntent());
                }

            }
        });

    }

    public void GoToWorkoutsPage()
    {
        Intent WorkoutsPage_Intent = new Intent(this, WorkoutsPage.class);
        startActivity(WorkoutsPage_Intent);
    }

    public void GoToexercises(){
        Intent intent = new Intent(this, MuscleGroup.class);
        startActivity(intent);
    }

    public void GoToUserMenu(){
        Intent intent = new Intent(this, UserMenuActivity.class);
        startActivity(intent);
    }

    public void GoToLogin(){            //Added by Xian
        Intent intent = new Intent(this, loggingIn.class);
        startActivity(intent);
    }

    public void GoToAdminPage(){
        Intent intent = new Intent(this, AdminPage.class);
        startActivity(intent);
    }

    @Override
    public void onBackPressed()
    {

    }
}
