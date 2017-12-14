package com.example.cpre458.resourceaccesscontrol;

import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class SchedulingTest {
    @Test
    public void scheduleTest() throws Exception {
        Resource[] reqs = new Resource[2];
        Resource r1 = new Resource();
        Resource r2 = new Resource();

        reqs[0] = r1;
        reqs[1] = r2;

        Resource[] senderReqs = new Resource[1];
        Resource[] cpuReqs = new Resource[2];
        Resource[] ioReqs = new Resource[1];

        senderReqs[0] = r1;

        cpuReqs[0] = r1;
        cpuReqs[1] = r2;

        ioReqs[0] = r2;

        ArrayList<Task> testList = new ArrayList<>();
        testList.add(new Task(
                "Sender", 2, 10, 10, 0, senderReqs));
        testList.add(new Task(
                "CPU", 2, 5, 5, 0, cpuReqs));
        testList.add(new Task(
                "IO", 4, 15, 15, 0, ioReqs));

        Scheduling scheduler = new Scheduling(testList, reqs);
        System.out.format(scheduler.RMSSchedule().toString());
    }
}
