package com.cpsc471.cpsc471project;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class AdminPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_page);
        setTitle("Administration");

    }

    public void showTableSelected(View view){
        Intent intent = new Intent(this, DeleteActivity.class);
        intent.putExtra("button_id", view.getId());
        startActivity(intent);
    }
}
