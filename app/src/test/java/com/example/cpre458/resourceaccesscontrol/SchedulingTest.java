package com.example.cpre458.resourceaccesscontrol;

import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class SchedulingTest {
    @Test
    public void scheduleTest() throws Exception {
        ArrayList<Task> testList = new ArrayList<>();
        testList.add(new Task("Sender", 3, 10, 10, 0));
        testList.add(new Task("CPU", 2, 9, 5, 0));
        testList.add(new Task("IO", 5, 15, 15, 0));

        Scheduling scheduler = new Scheduling(testList);
        scheduler.RMSSchedule();
    }
}
