package com.example.cpre458.resourceaccesscontrol;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;


import java.util.ArrayList;

public class TaskInput extends AppCompatActivity implements  View.OnClickListener {

    TaskAdapter adapter;
    ListView lv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_input);
        //Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);

        /*FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/

        ((Button) findViewById(R.id.button_rms)).setOnClickListener(this);
        ((Button) findViewById(R.id.button_rms_pi)).setOnClickListener(this);
        ((Button) findViewById(R.id.button_rms_pc)).setOnClickListener(this);
    }

    public void onClick(View v)
    {
        switch( v.getId() ) {
            case R.id.button_rms:
                Log.d("button", "RMS pressed");
                createSchedule(0);
                break;
            case R.id.button_rms_pi:
                break;
            case R.id.button_rms_pc:
                break;
        }
    }


    public void createSchedule(int schedule_type)
    {
        ArrayList<Task> taskList;
        Resource[] resources = {};
        ArrayList<String> taskSchedule;

        taskList = null;//TODO

        switch (schedule_type) {
            case 0:
                Log.d("Gen task", "Generating...");
                taskList = generateTaskList();
                break;
            case 1:
                break;
            case 2:
                break;
            default:
                break;
        }

        Scheduling s = new Scheduling(taskList, resources);
        try {
            taskSchedule = s.RMSSchedule();
        }
        catch (Exception e)
        {
            taskSchedule = new ArrayList<>(); //no schedule produced, make empty list
        }

        //List<Entry> entries = new ArrayList<Entry>();
        ArrayList<TaskListItem> taskitemList = new ArrayList<>();
        int len = taskSchedule.size();
        for (int i=0;i<len;i++)
        {
            if (null != taskSchedule.get(i))
            {
                taskitemList.add(new TaskListItem(i, taskSchedule.get(i)));
            }
            else
            {
                taskitemList.add(new TaskListItem(i, "---"));
            }
        }

        adapter = new TaskAdapter(TaskInput.this, taskitemList);
        lv = findViewById(R.id.task_list);
        lv.setAdapter(adapter);
    }

    private ArrayList<Task> generateTaskList()
    {
        ArrayList<Task> ret = new ArrayList<>();
        Resource[] res1 = {};
        Task t1 = new Task("T1", 5, 10,20,1, res1);
        Task t2 = new Task("T2", 3, 10,15,2, res1);
        Task t3 = new Task("T3", 1, 10,10,3, res1);
        ret.add(t1);
        ret.add(t2);
        ret.add(t3);
        return ret;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_task_input, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
