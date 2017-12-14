package com.example.cpre458.resourceaccesscontrol;

/**
 * Created by kev on 12/14/2017.
 */

public class TaskListItem {

    private int time;
    private String name;

    public TaskListItem(int time, String name)
    {
        this.time = time;
        this.name = name;
    }

    public String getName()
    {
        return name;
    }

    public int getTime()
    {
        return time;
    }

}
