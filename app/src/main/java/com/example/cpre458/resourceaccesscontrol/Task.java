package com.example.cpre458.resourceaccesscontrol;

import android.support.annotation.NonNull;

import java.util.Locale;

/**
 * Class for representing a single task.
 */

public class Task implements Comparable<Task> {
    public String name;
    public int execution_time;
    public int period;
    public int deadline;
    public int remainingExecutionTime;
    public int taskNum;

    public Task(String name, int execution_time, int period, int deadline, int taskNum) {
        this.name = name;
        this.execution_time = execution_time;
        this.period = period;
        this.deadline = deadline;
        this.remainingExecutionTime = execution_time;
        this.taskNum = taskNum;
    }

    @SuppressWarnings("MethodDoesntCallSuperMethod")
    @Override
    public Task clone() {
        return new Task(this.name, this.execution_time, this.period, this.deadline, this.taskNum);
    }

    @Override
    public int compareTo(@NonNull Task other) {
        return this.period - other.period;
    }

    @Override
    public String toString() {
        return String.format(Locale.US, "<%s (%d)>", this.name, this.taskNum);
    }
}
