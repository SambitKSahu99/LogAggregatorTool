package org.logAggregatorTool;

import org.logAggregatorTool.dao.AuditEntry;
import org.logAggregatorTool.dto.LogRecord;
import org.logAggregatorTool.operation.LogThread;
import org.logAggregatorTool.utility.Constants;
import org.logAggregatorTool.utility.Validations;

import java.util.Scanner;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class LogAggregatorTool {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        Scanner scanner = new Scanner(System.in);
        System.out.println(Constants.WELCOME_MESSAGE);
        System.out.println(Constants.ENTER_LOG_FILE_FOLDER_PATH_MESSAGE);
        String logFilesFolderPath = "";
//      Taking user input through command line and validating folderPath
        String userInput1 = scanner.next();
        if (Validations.validateFolderPath(userInput1)) {
            System.out.println(Constants.PROCESSING_MESSAGE);
            logFilesFolderPath = userInput1;
        } else {
            System.out.println(Constants.INVALID_PATH_MESSAGE);
            logFilesFolderPath = scanner.next();
            System.out.println(Constants.PROCESSING_MESSAGE);
        }
//      Creating a child thread
        ExecutorService logExecutorService = Executors.newFixedThreadPool(1);
        LogThread logThread = new LogThread(logFilesFolderPath);
        Future<String> logFuture = logExecutorService.submit(logThread);
        System.out.println(logFuture.get());
//      Logging Details To Database
        LogRecord logRecordData = logThread.auditDataSetter();
        (new AuditEntry()).auditEntryOperation(logRecordData);
    }
}
