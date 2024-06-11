package org.logAggregatorTool.operation;

import org.logAggregatorTool.dto.LogRecord;
import org.logAggregatorTool.utility.Constants;

import java.io.File;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Callable;

public class LogThread implements Callable<String> {

    private final String logFilesFolderPath;
    private int totalFiles;
    private List<String> nameOfFiles;
    private String result;
    private String errorMessage;
    private String outputFileName;

    public LogThread(String path) {
        logFilesFolderPath = path;
    }

    @Override
    public String call() throws Exception {
        List<File> logFileList = null;
        try {
            File[] fileList = (new File(logFilesFolderPath)).listFiles();
            if (fileList.length == 0) throw new RuntimeException(Constants.FILE_NOT_FOUND_MESSAGE);
            logFileList = Arrays.stream(fileList)
                    .filter(file -> file.getName().endsWith(Constants.LOG_FILE_EXTENSION))
                    .toList();
            if (logFileList.isEmpty()) throw new RuntimeException(Constants.LOG_FILE_NOT_FOUND_MESSAGE);
            LogFilesProcessor logFilesProcessor = new LogFilesProcessor();
            logFilesProcessor.logOperation(logFileList);
            this.totalFiles = logFileList.size();
            List<String> logFileNames = new ArrayList<>();
            for(File logFile:logFileList){
                logFileNames.add(logFile.getName());
            }
            this.nameOfFiles = logFileNames;
            this.result = Constants.SUCCESS;
            this.errorMessage="";
            this.outputFileName = new File(Constants.OUTPUT_FILE_PATH).getName();
            return Constants.SUCCESS_AND_OUTPUT_FILE_PATH_MESSAGE;
        } catch (Exception e) {
            this.result = Constants.FAILURE;
            this.errorMessage = e.getMessage();
            this.outputFileName = "";
            return Constants.FAILED_MESSAGE + e.getMessage();
        }
    }

    public LogRecord auditDataSetter(){
        LogRecord logRecordData = new LogRecord();
        logRecordData.setLogFileFolderPath(this.logFilesFolderPath);
        logRecordData.setTotalFiles(this.totalFiles);
        logRecordData.setNameOfFiles(this.nameOfFiles);
        logRecordData.setDateOfOperation(LocalDateTime.now().toString());
        logRecordData.setResult(this.result);
        logRecordData.setErrorMessage(this.errorMessage);
        logRecordData.setOutputFileName(this.outputFileName);
        return logRecordData;
    }
}

