package org.logAggregatorTool;

import org.logAggregatorTool.dao.AuditEntry;
import org.logAggregatorTool.dto.LogAggregatorAuditData;
import org.logAggregatorTool.operation.LogFilesFolderProcessor;
import org.logAggregatorTool.constants.LogAggregatorToolConstants;
import org.logAggregatorTool.utility.LogAggregatorToolValidations;

import java.time.LocalDateTime;
import java.util.Scanner;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class LogAggregatorTool {

    public static void main(String[] args) {
        LogAggregatorAuditData logAggregatorAuditData = new LogAggregatorAuditData();
        AuditEntry auditEntry = new AuditEntry();
//      Taking user input through command line and validating folderPath
        String logFilesFolderPath = new LogAggregatorTool().userInputProcessor(logAggregatorAuditData,auditEntry);
        System.out.println(LogAggregatorToolConstants.PROCESSING_MESSAGE);
//      Creating a child thread
        ExecutorService logExecutorService = Executors.newSingleThreadExecutor();
        LogFilesFolderProcessor logFilesFolderProcessor = new LogFilesFolderProcessor(logFilesFolderPath, logAggregatorAuditData);
        Future<String> logFuture = logExecutorService.submit(logFilesFolderProcessor);
        String userOutputMessage = null;
        try {
            userOutputMessage = logFuture.get();
        } catch (InterruptedException | ExecutionException exception) {
            System.out.println(LogAggregatorToolConstants.FAILURE +LogAggregatorToolConstants.SINGLE_SPACE+ exception.getMessage());
        }
        System.out.println(userOutputMessage);
        logExecutorService.shutdown();
//      Updating application logs in the AUDIT table
        auditEntry.auditEntryOperation(logAggregatorAuditData);
    }

    /**
     * This method will accept log file folder path as string , validate it and pass it to main method
     *
     * @return Log file folder path as string
     */
    private String userInputProcessor(LogAggregatorAuditData logAggregatorAuditData,AuditEntry auditEntry) {
        Scanner logFolderPathScanner = new Scanner(System.in);
        System.out.println(LogAggregatorToolConstants.WELCOME_MESSAGE);
        System.out.println(LogAggregatorToolConstants.ENTER_LOG_FILE_FOLDER_PATH_MESSAGE);
        String logFilesFolderPathUserInput = logFolderPathScanner.next();
        while (!LogAggregatorToolValidations.isValidFolderPath(logFilesFolderPathUserInput)) {
            System.out.println(LogAggregatorToolConstants.INVALID_PATH_MESSAGE);
            logFilesFolderPathUserInput = logFolderPathScanner.next();
            if (logFilesFolderPathUserInput.equalsIgnoreCase(LogAggregatorToolConstants.USER_PERMISSION_N)) {
                System.out.println(LogAggregatorToolConstants.EXIT_MESSAGE);
                logAggregatorAuditData.setLogFileFolderPath(LogAggregatorToolConstants.DEFAULT_STRING_VALUE);
                logAggregatorAuditData.setResult(LogAggregatorToolConstants.FAILURE);
                logAggregatorAuditData.setOutputFileName(LogAggregatorToolConstants.DEFAULT_STRING_VALUE);
                logAggregatorAuditData.setErrorMessage(LogAggregatorToolConstants.USER_EXIT_MESSAGE);
                logAggregatorAuditData.setDateOfOperation(LocalDateTime.now().toString());
                auditEntry.auditEntryOperation(logAggregatorAuditData);
                System.exit(LogAggregatorToolConstants.DEFAULT_INT_VALUE);
            } else if (logFilesFolderPathUserInput.equalsIgnoreCase(LogAggregatorToolConstants.USER_PERMISSION_Y)) {
                System.out.println(LogAggregatorToolConstants.ENTER_LOG_FILE_FOLDER_PATH_MESSAGE);
                logFilesFolderPathUserInput = logFolderPathScanner.next();
            }
        }
        return logFilesFolderPathUserInput;
    }
}
