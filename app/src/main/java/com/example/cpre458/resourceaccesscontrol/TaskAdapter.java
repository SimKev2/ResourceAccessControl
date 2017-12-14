package com.example.cpre458.resourceaccesscontrol;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckedTextView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by kev on 12/14/2017.
 */

public class TaskAdapter extends BaseAdapter {

    private Activity activity;
    private ArrayList<TaskListItem> data;
    private static LayoutInflater inflater;

    public TaskAdapter(Activity context, ArrayList<TaskListItem> list)
    {
        activity = context;
        data = list;
        inflater = (LayoutInflater)activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public Object getItem(int position)
    {
        return position;
    }

    public long getItemId(int position)
    {
        return position;
    }

    public int getCount()
    {
        return data.size();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;

        if(row == null)
            row = inflater.inflate(R.layout.task_list_single, null);

        TextView timetext = row.findViewById(R.id.time_text);
        TextView tasktext = row.findViewById(R.id.task_text);
        TaskListItem t = data.get(position);

        String s = "Time: " + t.getTime();
        timetext.setText(s);
        s = "Task: " + t.getName();
        tasktext.setText(s);
        return row;
    }
}
