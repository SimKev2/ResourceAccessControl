package com.example.cpre458.resourceaccesscontrol;

import android.support.annotation.NonNull;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;

/**
 * Class for representing a single task.
 */

public class Task implements Comparable<Task> {
    public String name;
    public int execution_time;
    public int period;
    public int priority;
    public int deadline;
    public int remainingExecutionTime;
    public int taskNum;
    public Resource[] requiredResources;
    public Resource[] currentResources;

    public Task(String name, int execution_time, int period, int deadline, int taskNum, Resource[] reqs) {
        this.name = name;
        this.execution_time = execution_time;
        this.period = period;
        this.priority = period;
        this.deadline = deadline;
        this.remainingExecutionTime = execution_time;
        this.taskNum = taskNum;
        this.requiredResources = reqs;
        this.currentResources = new Resource[reqs.length];
    }

    public boolean canExecute() {
        List<Resource> current = Arrays.asList(this.currentResources);
        for (Resource r : this.requiredResources) {
            if (!current.contains(r) && !r.canLock()) {
                return false;
            }
        }
        return true;
    }

    public void startExecution() {
        for (int i = 0; i < this.requiredResources.length; i++) {
            this.requiredResources[i].lock(this);
            this.currentResources[i] = this.requiredResources[i];
        }
    }

    public void startCeilingExecution() {
        for (int i = 0; i < this.requiredResources.length; i++) {
            this.requiredResources[i].lock(this);
            if (this.requiredResources[i].priority > this.priority) {
                this.priority = this.requiredResources[i].priority;
                System.out.format(
                    "\tRaising %s to priority %d\n",
                    this.toString(),
                    this.requiredResources[i].priority);
            }
            this.currentResources[i] = this.requiredResources[i];
        }
    }

    public void releaseResources() {
        for (Resource r : this.requiredResources) {
            r.unlock();
        }
    }

    @SuppressWarnings("MethodDoesntCallSuperMethod")
    @Override
    public Task clone() {
        return new Task(this.name, this.execution_time, this.period, this.deadline, this.taskNum, this.requiredResources);
    }

    @Override
    public int compareTo(@NonNull Task other) {
        return this.priority - other.priority;
    }

    @Override
    public String toString() {
        return String.format(Locale.US, "<%s (%d)>", this.name, this.taskNum);
    }
}
