package org.logAggregatorTool.utility;

import org.logAggregatorTool.constants.LogAggregatorToolConstants;

import java.io.File;

public class LogAggregatorToolValidations {
    /**
     * Accepts the log File Folder logFileFolderPath and validates it
     *
     * @param logFileFolderPath log Files Folder logFileFolderPath
     * @return boolean value according to condition
     */
    public static boolean isValidFolderPath(String logFileFolderPath) {
        return new File(logFileFolderPath).isDirectory();
    }

    /**
     * This method will check if the fileToBeChecked is a log fileToBeChecked with .log extension
     *
     * @param fileToBeChecked File present in the folder
     * @return boolean value according to condition
     */
    public static boolean isValidLogFile(File fileToBeChecked) {
        return fileToBeChecked.getName().endsWith(LogAggregatorToolConstants.LOG_FILE_EXTENSION);
    }
}
