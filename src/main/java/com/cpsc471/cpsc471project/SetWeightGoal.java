package com.cpsc471.cpsc471project;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

public class SetWeightGoal extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("New Weight Goal");
        setContentView(R.layout.activity_set_weight_goal);

        Spinner type_dropdown = (Spinner)findViewById(R.id.Goaltype_spinner);
        String[] type_items = new String[]{"Lower", "Gain"};
        ArrayAdapter<String> type_adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, type_items);
        type_dropdown.setAdapter(type_adapter);
    }

    public void doneNewWeightGoal(View view){
        EditText nameText = (EditText) findViewById(R.id.addWeightGoal);
        String typeText = nameText.getText().toString();
        Spinner typeSpinner = (Spinner) findViewById(R.id.Goaltype_spinner);
        String goaltypeText = typeSpinner.getSelectedItem().toString();
        if((nameText.getText().toString().equals(null) || nameText.getText().toString().equals(""))){

        }
        else{
            MySQLiteHelper db = new MySQLiteHelper(this);
            String [] currentWG = db.getWeightGoal(String.valueOf(UserValues.userID));
            if(currentWG[0].contains("null")){
                db.addWeightGoal(new Weight_goal(UserValues.userID, Integer.parseInt(typeText), goaltypeText));
                Intent intent = new Intent(this, WeightGoal.class);
                startActivity(intent);
            }
            else{
                db.deleteWeightGoal(new Weight_goal(UserValues.userID, Integer.parseInt(currentWG[0]), currentWG[1]));
                db.addWeightGoal(new Weight_goal(UserValues.userID, Integer.parseInt(typeText), goaltypeText));
                Intent intent = new Intent(this, WeightGoal.class);
                startActivity(intent);
            }
        }

    }
}
