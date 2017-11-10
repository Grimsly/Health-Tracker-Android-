package com.cpsc471.cpsc471project;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

public class DisplayHealth extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Health History");
        setContentView(R.layout.activity_display_health);
        MySQLiteHelper db = new MySQLiteHelper(this);
        String SQL_health_history = " where UserID = " + UserValues.userID;
        Cursor todo = db.getAllHealthHistory(SQL_health_history);
        if( todo.getCount() == 0)
            setContentView(R.layout.no_health_history);
        else{// Find ListView to populate
            ListView Items = (ListView) findViewById(R.id.HealthList);
            // Setup cursor adapter using cursor from last step
            DisplayHealthAdapter todoAdapter = new DisplayHealthAdapter(this, todo);
            // Attach cursor adapter to the ListView
            Items.setAdapter(todoAdapter);}
    }

    public void gotoAddHistory(View view){
        Intent intent = new Intent(this, AddHistory.class);
        startActivity(intent);
    }
    @Override
    public void onBackPressed()
    {
        Intent intent = new Intent(this, UserMenuActivity.class);
        startActivity(intent);
    }
}
