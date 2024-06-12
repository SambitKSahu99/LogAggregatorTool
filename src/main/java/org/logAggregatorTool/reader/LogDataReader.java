package org.logAggregatorTool.reader;

import org.logAggregatorTool.utility.Constants;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Arrays;

public class LogDataReader {
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
                String logKey = "";
                String logValue = "";
                boolean check = false;
                String logDataLine = null;
                while ((logDataLine = logReader.readLine()) != null) {
                    checkLogData = true;
                    if (logDataLine.matches(Constants.DATE_PATTERN)) {
                        if (check) {
                            if (!logTimeToDataMap.containsKey(logKey))
                                logTimeToDataMap.put(logKey, new ArrayList<>(Arrays.asList(logValue)));
                            else logTimeToDataMap.get(logKey).add(logValue);
                            timeStampList.add(logKey);
                        }
                        check = true;
                        logKey = logDataLine.substring(0, 24);
                        logValue = logDataLine.substring(24);
                    } else {
                        logValue += logDataLine;
                    }
                }
            }

        }
        if (!checkLogData) throw new RuntimeException((Constants.DATA_NOT_FOUND_MESSAGE));
    }
}




