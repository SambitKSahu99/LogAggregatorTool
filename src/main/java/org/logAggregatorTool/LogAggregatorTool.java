package org.logAggregatorTool;

import org.logAggregatorTool.dao.AuditEntry;
import org.logAggregatorTool.dto.LogAggregatorAuditData;
import org.logAggregatorTool.operation.LogFilesFolderProcessor;
import org.logAggregatorTool.constants.LogAggregatorToolConstants;
import org.logAggregatorTool.utility.LogAggregatorToolValidations;

import java.util.Scanner;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class LogAggregatorTool {

    public static void main(String[] args) {
//      Taking user input through command line and validating folderPath
        String logFilesFolderPath = logFilesFolderPathUserInputProcessor();
        System.out.println(LogAggregatorToolConstants.PROCESSING_MESSAGE);
//      Creating a child thread
        ExecutorService logExecutorService = Executors.newSingleThreadExecutor();
        LogAggregatorAuditData logAggregatorAuditData = new LogAggregatorAuditData();
        LogFilesFolderProcessor logFilesFolderProcessor = new LogFilesFolderProcessor(logFilesFolderPath,logAggregatorAuditData);
        Future<String> logFuture = logExecutorService.submit(logFilesFolderProcessor);
        String userOutputMessage = null;
        try {
            userOutputMessage = logFuture.get();
        } catch (InterruptedException | ExecutionException exception) {
            System.out.println(LogAggregatorToolConstants.FAILURE + " " + exception.getMessage());
        }
        System.out.println(userOutputMessage);
        logExecutorService.shutdown();
//      Updating application logs in the AUDIT table
        (new AuditEntry()).auditEntryOperation(logAggregatorAuditData);
    }

    /**
     * This method will accept log file folder path as string , validate it and pass it to main method
     * @return Log file folder path as string
     */
    private static String logFilesFolderPathUserInputProcessor(){
        Scanner logFolderPathScanner = new Scanner(System.in);
        System.out.println(LogAggregatorToolConstants.WELCOME_MESSAGE);
        System.out.println(LogAggregatorToolConstants.ENTER_LOG_FILE_FOLDER_PATH_MESSAGE);
        String logFilesFolderPathUserInput = logFolderPathScanner.next();
        while(!LogAggregatorToolValidations.isValidFolderPath(logFilesFolderPathUserInput)){
            System.out.println(LogAggregatorToolConstants.INVALID_PATH_MESSAGE);
            logFilesFolderPathUserInput = logFolderPathScanner.next();
            if(logFilesFolderPathUserInput.equalsIgnoreCase(LogAggregatorToolConstants.USER_PERMISSION_N)){
                System.out.println(LogAggregatorToolConstants.EXIT_MESSAGE);
                System.exit(LogAggregatorToolConstants.DEFAULT_INT_VALUE);
            }
            else if(logFilesFolderPathUserInput.equalsIgnoreCase(LogAggregatorToolConstants.USER_PERMISSION_Y)) {
                System.out.println(LogAggregatorToolConstants.ENTER_LOG_FILE_FOLDER_PATH_MESSAGE);
                logFilesFolderPathUserInput = logFolderPathScanner.next();
            }
        }
        return logFilesFolderPathUserInput;
    }
}
