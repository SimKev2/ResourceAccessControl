package com.example.cpre458.resourceaccesscontrol;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Package for handling scheduling tasks.
 */

public class Scheduling {
    public int scheduleDuration;

    private ArrayList<Task> taskList;

    // Can change this to accept list of strings or something
    public Scheduling(ArrayList<Task> taskList) {
        // TODO: Verify the taskList is schedulable

        this.taskList = taskList;
        Collections.sort(this.taskList);
        this.scheduleDuration = lcm(this.taskList);
    }

    public void RMSSchedule() {
        ArrayList<Task> taskQueue = new ArrayList<>();
        Task currentTask = null;
        Task prevTask = null;
        int currentTime = 0;

        while (currentTime < this.scheduleDuration) {
            System.out.format("Time: %d\n", currentTime);

            for (Task t : this.taskList) {
                if (currentTime % t.period == 0) {
                    Task newTask = t.clone();
                    newTask.taskNum = currentTime / t.period;
                    taskQueue.add(newTask);
                    System.out.format("\tTask Arrived: %s\n", newTask);
                }
            }

            if (taskQueue.isEmpty()) {
                System.out.format("\tNo task\n\n");
                currentTime += 1;
                currentTask = null;
                prevTask = currentTask;
                continue;
            }

            Collections.sort(taskQueue);
            currentTask = taskQueue.get(0);

            if (prevTask != null && currentTask != prevTask) {
                System.out.format("\tPreempted %s, with %s\n", prevTask, currentTask);
            }

            System.out.format("\tExecuting task: %s\n", currentTask);
            currentTime += 1;
            currentTask.remainingExecutionTime -= 1;
            if (currentTask.remainingExecutionTime == 0) {
                taskQueue.remove(currentTask);
                System.out.format("\tFinished task: %s\n", currentTask);
                currentTask = null;
                prevTask = null;
            } else {
                prevTask = currentTask;
            }
        }
    }

    private static int gcd(int a, int b) {
        int temp;
        while (b > 0) {
            temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }

    private static int lcm(int a, int b) {
        return a * (b / gcd(a, b));
    }

    private static int lcm(ArrayList<Task> input) {
        int result = input.get(0).period;
        for (int i = 1; i < input.size(); i++) {
            result = lcm(result, input.get(i).period);
        }
        return result;
    }


}
