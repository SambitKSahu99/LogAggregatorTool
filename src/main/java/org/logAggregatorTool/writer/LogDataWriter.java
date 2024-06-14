package org.logAggregatorTool.writer;

import org.logAggregatorTool.constants.LogAggregatorToolConstants;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public class LogDataWriter {
    /**
     * Processes the log data according to the sorted timeStamp from logDateTimeToDataMap
     * and writes it into an output file
     *
     * @param sortedTimeStampList  Sorted TimeStamp in ascending order
     * @param logDateTimeToDataMap Key : TimeStamp as String , Value : LogData as List<String>
     */
    public void writeProcessedLogData(List<String> sortedTimeStampList, Map<String, List<String>> logDateTimeToDataMap) throws IOException {
        String checkTimeStampValue = LogAggregatorToolConstants.DEFAULT_STRING_VALUE;
        try (BufferedWriter logBufferedWriter = new BufferedWriter(new FileWriter(LogAggregatorToolConstants.OUTPUT_FILE_PATH))) {
            for (String timeStampData : sortedTimeStampList) {
                writeDataToOutputFile(logDateTimeToDataMap, timeStampData, checkTimeStampValue, logBufferedWriter);
            }
        }
    }

    /**
     * this method will accept the required log data and write the processed data in an output file
     *
     * @param logDateTimeToDataMap Map key:DateToTimeStamp , Value:LogData
     * @param timeStampData        SortedTimeStamp
     * @param checkTimeStampValue  Check TimeStamp Value
     * @param logBufferedWriter    Log Buffered Writer  Object
     * @throws IOException throws exception to calling method
     */
    private void writeDataToOutputFile(Map<String, List<String>> logDateTimeToDataMap, String timeStampData, String checkTimeStampValue, BufferedWriter logBufferedWriter) throws IOException {
        if (checkTimeStampValue.equals(timeStampData)) return;
        checkTimeStampValue = timeStampData;
        List<String> logValues = logDateTimeToDataMap.get(timeStampData);
        String logData;
        for (String logValue : logValues) {
            logData = checkTimeStampValue + logValue;
            logBufferedWriter.write(logData);
            logBufferedWriter.newLine();
        }
    }
}
