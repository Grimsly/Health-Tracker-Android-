package com.cpsc471.cpsc471project;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;


public class AddHistory extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_history);
    }

    public void addHealth(View view){
        EditText editText = (EditText) findViewById(R.id.height_entry);        //Gets the string in the username
        int height = Integer.parseInt(editText.getText().toString());
        editText = (EditText) findViewById(R.id.weight_entry);        //Gets the string in the username
        int weight = Integer.parseInt(editText.getText().toString());
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        HealthHistory history = new HealthHistory(UserValues.userID, df.format(date), weight, height);
        MySQLiteHelper db = new MySQLiteHelper(this);
        db.addHealthHistory(history);
        db.close();
        Intent intent = new Intent(this, DisplayHealth.class);
        startActivity(intent);
    }
}
