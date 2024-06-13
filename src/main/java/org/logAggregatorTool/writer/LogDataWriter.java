package org.logAggregatorTool.writer;

import org.logAggregatorTool.constants.LogAggregatorToolConstants;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public class LogDataWriter {
    /**
     * Processes the log data according to the sorted timeStamp from logTimeToDataMap
     * and writes it into an output file
     *
     * @param sortedTimeStampList Sorted TimeStamp in ascending order
     * @param logTimeToDataMap    Key : TimeStamp as String , Value : LogData as List<String>
     */
    public void writeProcessedLogData(List<String> sortedTimeStampList, Map<String, List<String>> logTimeToDataMap) throws IOException {
        String timeStamp = LogAggregatorToolConstants.EMPTY_STRING;
        try (BufferedWriter logWriter = new BufferedWriter(new FileWriter(LogAggregatorToolConstants.OUTPUT_FILE_PATH))) {
            for (String timeStampData : sortedTimeStampList) {
                if (!timeStamp.equals(timeStampData)) {
                    String logData;
                    timeStamp = timeStampData;
                    List<String> logValues = logTimeToDataMap.get(timeStampData);
                    for (String logValue : logValues) {
                        logData = timeStamp + logValue;
                        logWriter.write(logData);
                        logWriter.newLine();
                    }
                }
            }
        }
    }
}
