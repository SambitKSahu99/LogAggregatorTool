package org.logAggregatorDemo;

import java.io.File;

public class LogAggregatorTool {

    public static void main(String[] args) {

//      Taking user input through command line and validating folderPath
        String folderPath = args[0];
        if (Validations.validateFolderPath(folderPath)) System.out.println("Processing");
        else throw new RuntimeException("Invalid path");

//      Creating a child thread
        Runnable runnable = new LogThread(folderPath);
        Thread subThread = new Thread(runnable);
        subThread.start();


    }


}