package com.example.cpre458.resourceaccesscontrol;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Package for handling scheduling tasks.
 */

public class Scheduling {
    public int scheduleDuration;

    private ArrayList<Task> taskList;
    private Resource[] resources;

    public Scheduling(ArrayList<Task> taskList, Resource[] resources) {
        this.taskList = taskList;
        Collections.sort(this.taskList);
        this.resources = resources;

        this.scheduleDuration = lcm(this.taskList);
    }

    public void RMSSchedule() throws Exception {
        ArrayList<Task> taskQueue = new ArrayList<>();
        Task currentTask = null;
        Task prevTask = null;
        Task checkTask;
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
            for (int i = 0; i < taskQueue.size(); i++) {
                checkTask = taskQueue.get(i);
                if (currentTask == checkTask) {
                    break;
                }

                if (checkTask.canExecute()) {
                    checkTask.startExecution();
                    currentTask = checkTask;
                    break;
                }
                System.out.format("\tCannot execute %s\n", checkTask);
            }

            if (prevTask != null && currentTask != prevTask) {
                System.out.format("\tPreempted %s, with %s\n", prevTask, currentTask);
            }

            if (currentTask == null) {
                throw new Exception("Schedule not feasible");
            }

            System.out.format("\tExecuting task: %s\n", currentTask);
            currentTime += 1;
            currentTask.remainingExecutionTime -= 1;
            if (currentTask.remainingExecutionTime == 0) {
                taskQueue.remove(currentTask);
                currentTask.releaseResources();
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
