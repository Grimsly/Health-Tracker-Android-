package com.cpsc471.cpsc471project;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import java.text.DecimalFormat;

public class ShowBMI extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Current BMI");
        setContentView(R.layout.activity_show_bmi);
        TextView bmiText = (TextView) findViewById(R.id.BMItextView);
        TextView healthText = (TextView) findViewById(R.id.BMI_healthinfo);
        MySQLiteHelper db = new MySQLiteHelper(this);
        String[] weight_N_height= db.getLastHealthHistory(String.valueOf(UserValues.userID));
        if(weight_N_height[0].contains("null") || weight_N_height[1].contains("null")) {
            bmiText.setText("-");
            healthText.setText("No Health History");
        }
        else {
            double bmi = (Double.parseDouble(weight_N_height[0]) / (Math.pow((Double.parseDouble(weight_N_height[1])/100), 2)));
            DecimalFormat df = new DecimalFormat("#.0");
            String healthStatus = db.getBMI_healthstatus((int)bmi);
            String Sbmi = String.valueOf(df.format(bmi));
            bmiText.setText(Sbmi);
            healthText.setText(healthStatus);
        }
    }
}
