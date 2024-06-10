package org.logAggregatorDemo;

public class LogRecord {

    String logFileFolderPath;
    int totalFiles;
    String nameOfFiles;
    String dateOfOperation;
    String result;
    String outputFileName;
    String errorMessage;

    public LogRecord() {
    }

    public LogRecord
            (String logFileFolderPath, int totalFiles, String nameOfFiles, String dateOfOperation, String result, String outputFileName, String errorMessage) {
        this.logFileFolderPath = logFileFolderPath;
        this.totalFiles = totalFiles;
        this.nameOfFiles = nameOfFiles;
        this.dateOfOperation = dateOfOperation;
        this.result = result;
        this.outputFileName = outputFileName;
        this.errorMessage = errorMessage;
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

    public String getNameOfFiles() {
        return nameOfFiles;
    }

    public void setNameOfFiles(String nameOfFiles) {
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
