package org.logAggregatorTool.reader;

import org.logAggregatorTool.constants.LogAggregatorToolConstants;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class LogFileReader {
    /**
     * Reads the log data and extracts timestamp in a list and data into a map
     *
     * @param logFilesList     List Of Log Files
     * @param timeStampList    List Of Timestamp as String
     * @param logTimeToDataMap Map of log data , key:String timestamp , value:String data
     * @throws IOException passes exception object to calling method
     */
    public void readLogFileData(List<File> logFilesList, List<String> timeStampList, Map<String, List<String>> logTimeToDataMap) throws IOException {
        boolean checkLogData = false;
        for (File logFile : logFilesList) {
            String filePath = String.valueOf(logFile);
            try (BufferedReader logReader = new BufferedReader(new FileReader(filePath))) {
                String logTimeStampKey = LogAggregatorToolConstants.EMPTY_STRING;
                String logMessageValue = LogAggregatorToolConstants.EMPTY_STRING;
                boolean check = false;
                String logDataLine = null;
                while ((logDataLine = logReader.readLine()) != null) {
                    checkLogData = true;
                    if (logDataLine.matches(LogAggregatorToolConstants.DATE_REGEX_PATTERN) && check) {
                        if (!logTimeToDataMap.containsKey(logTimeStampKey))
                            logTimeToDataMap.put(logTimeStampKey, new ArrayList<>(Arrays.asList(logMessageValue)));
                        else logTimeToDataMap.get(logTimeStampKey).add(logMessageValue);
                        timeStampList.add(logTimeStampKey);
                    }
                    if (!logDataLine.matches(LogAggregatorToolConstants.DATE_REGEX_PATTERN)) {
                        logMessageValue += logDataLine;
                        continue;
                    }
                        logTimeStampKey = logDataLine.substring(LogAggregatorToolConstants.DEFAULT_INT_VALUE, LogAggregatorToolConstants.SUBSTRING_END_INDEX);
                        logMessageValue = logDataLine.substring(LogAggregatorToolConstants.SUBSTRING_END_INDEX);
                    check = true;
                }
            }
        }
        if (!checkLogData) throw new RuntimeException((LogAggregatorToolConstants.DATA_NOT_FOUND_MESSAGE));
    }
}
