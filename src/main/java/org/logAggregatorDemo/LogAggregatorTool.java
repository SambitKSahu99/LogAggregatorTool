package org.logAggregatorDemo;

import java.util.Scanner;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class LogAggregatorTool {

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        Scanner scanner = new Scanner(System.in);
        System.out.println("\n**** Log Aggregator Tool ****");
        System.out.println("Please enter the folder path where log files are located :");
        String logFilesFolderPath = "";

//      Taking user input through command line and validating folderPath
        String userInput1 = scanner.next();

        if (Validations.validateFolderPath(userInput1)){
            System.out.println("Processing.....");
            logFilesFolderPath=userInput1;
        }
        else {
            System.out.println();
            System.out.println("Invalid Path , Please enter correct path : ");
            logFilesFolderPath = scanner.next();
            System.out.println("Processing.....");
        }

//      Creating a child thread
        ExecutorService logExecutorService = Executors.newFixedThreadPool(1);
        Future<String> logFuture = logExecutorService.submit(new LogThread(logFilesFolderPath));
        System.out.println(logFuture.get());

    }

}