package com.cpsc471.cpsc471project;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import org.w3c.dom.Text;

public class WeightGoal extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Current Weight Goal");
        setContentView(R.layout.activity_weight_goal);
        MySQLiteHelper db = new MySQLiteHelper(this);
        String[] wg = db.getWeightGoal(String.valueOf(UserValues.userID));
        TextView weightGoalText = (TextView) findViewById(R.id.WeightGoaltextView);
        TextView infoText = (TextView) findViewById(R.id.InfoWeightGoaltextView);
        String [] lastHH = db.getLastHealthHistory(String.valueOf(UserValues.userID));
        if(lastHH[0].contains("null")){
            weightGoalText.setText("-");
            infoText.setText("Please Enter Health History Before Setting a Goal");
        }
        else if(wg[0].contains("null") || wg[1].contains("null")){
            //set text to no weight goal
            weightGoalText.setText("-");
            infoText.setText("No Info");
            findViewById(R.id.button12).setVisibility(View.VISIBLE);

        }
        else{
            int weightGoal = Integer.parseInt(wg[0]);
            int lastWeight = Integer.parseInt(lastHH[0]);
            weightGoalText.setText(String.valueOf(weightGoal));
            if( lastWeight <= weightGoal && wg[1].equals("Lower")){
                infoText.setText("Congradulations! You have met your current weight Goal, keep up the good work by setting a new one.");
            }
            else if(lastWeight >= weightGoal && wg[1].equals("Gain")){
                infoText.setText("Congradulations! You have met your current weight Goal, keep up the good work by setting a new one.");
            }
            else if(wg[1].equals("Lower")){
                infoText.setText("Keep pushing only " + String.valueOf(lastWeight - weightGoal) + "KG to burn until you meet your goal!");
            }
            else if(wg[1].equals("Gain")){
                infoText.setText("Keep pushing only " + String.valueOf(weightGoal - lastWeight) + "KG  to gain until you meet your goal!");
            }
            findViewById(R.id.button12).setVisibility(View.VISIBLE);
        }
    }
    public void newWeightGoal(View view){
        Intent WorkoutsPage_Intent = new Intent(this, SetWeightGoal.class);
        startActivity(WorkoutsPage_Intent);
    }
    @Override
    public void onBackPressed()
    {
        Intent intent = new Intent(this, UserMenuActivity.class);
        startActivity(intent);
    }
}

