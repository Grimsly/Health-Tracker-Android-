package com.cpsc471.cpsc471project;

/**
 * Created by ri1ey on 2017-03-19.
 */


import android.content.Context;
import android.database.Cursor;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

/**
 * Created by ri1ey on 2017-03-19.
 */

public class ExerciseListAdapter extends CursorAdapter {
    public ExerciseListAdapter(Context context, Cursor cursor) {
        super(context, cursor, 0);
    }

    // The newView method is used to inflate a new view and return it,
    // you don't bind any data to the view at this point.
    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return LayoutInflater.from(context).inflate(R.layout.show_exercises, parent, false);
    }

    // The bindView method is used to bind all data to a given view
    // such as setting the text on a TextView.
    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        // Find fields to populate in inflated template
        TextView tvBody = (TextView) view.findViewById(R.id.exercise_name);
        // Extract properties from cursor
        Log.i("Cursortest",cursor.getString(cursor.getColumnIndexOrThrow("name")) );
        String body = cursor.getString(cursor.getColumnIndexOrThrow("name"));
        // Populate fields with extracted properties
        tvBody.setText(body);
    }
}

