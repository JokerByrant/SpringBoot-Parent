package com.sxh.runner;

import org.springframework.boot.CommandLineRunner;
import org.springframework.cloud.task.listener.annotation.AfterTask;
import org.springframework.cloud.task.listener.annotation.BeforeTask;
import org.springframework.cloud.task.listener.annotation.FailedTask;
import org.springframework.cloud.task.repository.TaskExecution;
import org.springframework.stereotype.Component;

/**
 * @author sxh
 * @date 2021/4/7
 */
@Component
public class HelloWorldRunner implements CommandLineRunner {
    @Override
    public void run(String... args) {
        System.out.println("Hello World!");
    }

    @AfterTask
    public void onTaskEnd(TaskExecution arg0) {
        System.out.println("Task Completed Sucessfully");
    }

    @FailedTask
    public void onTaskFailed(TaskExecution arg0, Throwable arg1) {
        System.out.println("Task Failed");
    }

    @BeforeTask
    public void onTaskStartup(TaskExecution arg0) {
        System.out.println("Task started");
    }
}
