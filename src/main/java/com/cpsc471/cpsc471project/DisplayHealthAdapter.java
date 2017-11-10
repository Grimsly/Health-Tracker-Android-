package com.cpsc471.cpsc471project;

import android.content.Context;
import android.database.Cursor;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

/**
 * Created by xm on 2017/04/01.
 */

public class DisplayHealthAdapter extends CursorAdapter {

    public DisplayHealthAdapter (Context context, Cursor cursor){super (context, cursor, 0);}
    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return LayoutInflater.from(context).inflate(R.layout.show_health_history, parent, false);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        // Find fields to populate in inflated template
        TextView dateBody = (TextView) view.findViewById(R.id.health_history_date);
        TextView historyBody = (TextView) view.findViewById(R.id.health_history_info);
        // Extract properties from cursor
        Log.i("Cursortest",cursor.getString(cursor.getColumnIndexOrThrow("date")) );
        String datebody = cursor.getString(cursor.getColumnIndexOrThrow("date"));
        String historybody = "Weight: " +cursor.getString(cursor.getColumnIndexOrThrow("weight")) + " KG " +" Height: " + cursor.getString(cursor.getColumnIndexOrThrow("height")) + " CM";

        // Populate fields with extracted properties
        dateBody.setText(datebody);
        historyBody.setText(historybody);
    }
}
