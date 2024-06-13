package org.logAggregatorTool.operation;

import org.logAggregatorTool.constants.LogAggregatorToolConstants;
import org.logAggregatorTool.dto.LogAggregatorAuditData;
import org.logAggregatorTool.utility.LogAggregatorToolValidations;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Callable;

public class LogFilesFolderProcessor implements Callable<String> {

    private final String logFilesFolderPath;
    private final LogAggregatorAuditData logAggregatorAuditData;

    public LogFilesFolderProcessor(String logFilesFolderPath, LogAggregatorAuditData logAggregatorAuditData) {
        this.logFilesFolderPath = logFilesFolderPath;
        this.logAggregatorAuditData = logAggregatorAuditData;
    }

    /**
     * This method will extract only log files and return them as a File list
     *
     * @param filesList the list of files present in the folder
     * @return List of log files
     */
    private static List<File> logFileExtractor(List<File> filesList) {
        List<File> logFilesList = new ArrayList<>();
        List<String> invalidFilesNameList = new ArrayList<>();
        for (File file : filesList) {
            if (LogAggregatorToolValidations.isValidLogFile(file)) logFilesList.add(file);
            else invalidFilesNameList.add(file.getName());
        }
        if (!invalidFilesNameList.isEmpty())
            System.out.println(LogAggregatorToolConstants.INVALID_FILE_MESSAGE + invalidFilesNameList);
        return logFilesList;
    }

    @Override
    public String call() throws Exception {
        return processFilesAndAuditDataSetter();
    }

    /**
     * This method accepts the logFileFolderPath and passes it for further processing
     * and then return the message to main thread and set LogAggregatorAuditData data
     *
     * @return Return success message with output log file path or failure message
     */
    public String processFilesAndAuditDataSetter() {
        List<File> filesList = new ArrayList<>();
        List<File> logFileList;
        try {
            File[] filesArray = (new File(logFilesFolderPath)).listFiles();
            if (filesArray != null) filesList = Arrays.asList(filesArray);
            if (filesList.isEmpty()) throw new RuntimeException(LogAggregatorToolConstants.FILE_NOT_FOUND_MESSAGE);
            logFileList = logFileExtractor(filesList);
            if (logFileList.isEmpty())
                throw new RuntimeException(LogAggregatorToolConstants.LOG_FILE_NOT_FOUND_MESSAGE);
            LogFilesProcessor logFilesProcessor = new LogFilesProcessor();
            logFilesProcessor.logOperation(logFileList);
            List<String> logFileNames = new ArrayList<>();
            for (File logFile : logFileList) {
                logFileNames.add(logFile.getName());
            }
            logAggregatorAuditData.setLogFileFolderPath(logFilesFolderPath);
            logAggregatorAuditData.setTotalFiles(logFileList.size());
            logAggregatorAuditData.setNameOfFiles(logFileNames);
            logAggregatorAuditData.setResult(LogAggregatorToolConstants.SUCCESS);
            logAggregatorAuditData.setErrorMessage(LogAggregatorToolConstants.EMPTY_STRING);
            logAggregatorAuditData.setOutputFileName(new File(LogAggregatorToolConstants.OUTPUT_FILE_PATH).getName());
            return LogAggregatorToolConstants.SUCCESS_AND_OUTPUT_FILE_PATH_MESSAGE;
        } catch (Exception exception) {
            logAggregatorAuditData.setResult(LogAggregatorToolConstants.FAILURE);
            logAggregatorAuditData.setErrorMessage(exception.getMessage());
            logAggregatorAuditData.setOutputFileName(LogAggregatorToolConstants.EMPTY_STRING);
            return LogAggregatorToolConstants.FAILED_MESSAGE + exception.getMessage();
        }
    }
}
