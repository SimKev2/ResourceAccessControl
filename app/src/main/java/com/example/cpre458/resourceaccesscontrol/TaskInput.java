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
                createSchedule(1);
                break;
            case R.id.button_rms_pc:
                createSchedule(2);
                break;
        }
    }


    public void createSchedule(int schedule_type)
    {
        ArrayList<Task> taskList;
        ArrayList<String> taskSchedule = null;

        Resource r1 = new Resource();
        Resource[] resources = {r1};

        taskList = generateTaskList(resources);
        Scheduling s = new Scheduling(taskList, resources);
        try {
            switch (schedule_type) {
                case 0:
                    taskSchedule = s.RMSSchedule();
                    break;
                case 1:
                    taskSchedule = s.PriorityInheritance();
                    break;
                case 2:
                    taskSchedule = s.PriorityCeiling();
                    break;
                default:
                    break;
            }
        }
        catch (Exception e)
        {
            taskSchedule = new ArrayList<>(); //no schedule produced, make empty list
        }

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

    private ArrayList<Task> generateTaskList(Resource[] res1)
    {
        ArrayList<Task> ret = new ArrayList<>();
//        Task t1 = new Task("T1", 2, 5,5,1, res1);
//        Task t2 = new Task("T2", 4, 10,10,2, res1);
//        Task t3 = new Task("T3", 8, 20,20,3, res1);

        Resource[] none = {};

        Task t1 = new Task("T1", 2, 5,5,1, res1);
        Task t2 = new Task("T2", 4, 10,10,2, none);
        Task t3 = new Task("T3", 5, 20,20,3, res1);
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
