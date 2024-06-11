package org.logAggregatorTool.dto;

import java.util.List;

public class LogRecord {

    String logFileFolderPath;
    int totalFiles;
    List<String> nameOfFiles;
    String dateOfOperation;
    String result;
    String outputFileName;
    String errorMessage;

    public LogRecord() {
    }

    public String getLogFileFolderPath() {
        return logFileFolderPath;
    }

    public void setLogFileFolderPath(String logFileFolderPath) {
        this.logFileFolderPath = logFileFolderPath;
    }

    public int getTotalFiles() {
        return totalFiles;
    }

    public void setTotalFiles(int totalFiles) {
        this.totalFiles = totalFiles;
    }

    public List<String> getNameOfFiles() {
        return nameOfFiles;
    }

    public void setNameOfFiles(List<String> nameOfFiles) {
        this.nameOfFiles = nameOfFiles;
    }

    public String getDateOfOperation() {
        return dateOfOperation;
    }

    public void setDateOfOperation(String dateOfOperation) {
        this.dateOfOperation = dateOfOperation;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getOutputFileName() {
        return outputFileName;
    }

    public void setOutputFileName(String outputFileName) {
        this.outputFileName = outputFileName;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
