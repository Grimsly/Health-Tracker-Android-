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
 * Created by ri1ey on 2017-04-04.
 */

public class DisplayExerciseHistoryAdapter extends CursorAdapter{
    public DisplayExerciseHistoryAdapter (Context context, Cursor cursor){super (context, cursor, 0);}
    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return LayoutInflater.from(context).inflate(R.layout.show_exercise_history, parent, false);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        // Find fields to populate in inflated template
        TextView dateBody = (TextView) view.findViewById(R.id.exercise_history_date);
        TextView historyBody = (TextView) view.findViewById(R.id.exercise_history_records);
        TextView nameBody = (TextView) view.findViewById(R.id.exercise_history_name);
        // Extract properties from cursor
        Log.i("Cursortest",cursor.getString(cursor.getColumnIndexOrThrow("date")) );
        String datebody = cursor.getString(cursor.getColumnIndexOrThrow("date"));
        String recordsbody = cursor.getString(cursor.getColumnIndexOrThrow("records"));
        String namebody = cursor.getString(cursor.getColumnIndexOrThrow("name"));
        // Populate fields with extracted properties
        dateBody.setText(datebody);
        historyBody.setText(recordsbody);
        nameBody.setText(namebody);
    }
}
