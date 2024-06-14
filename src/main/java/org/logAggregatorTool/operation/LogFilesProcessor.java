package org.logAggregatorTool.operation;

import org.logAggregatorTool.reader.LogFileReader;
import org.logAggregatorTool.writer.LogDataWriter;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class LogFilesProcessor {
    /**
     * Retrieve all files from list , process them to read data and sorts them
     * according to timestamp and passes them for writing to output file
     *
     * @param logFileList List of Log Files
     * @throws IOException Passes exception object to calling method
     */
    public void logOperation(List<File> logFileList) throws IOException {
        Map<String, List<String>> logTimeToDataMap = new HashMap<>();
        List<String> timeStampsList = new ArrayList<>();
        LogFileReader logFileReader = new LogFileReader();
        logFileReader.readLogFileData(logFileList, timeStampsList, logTimeToDataMap);
        Collections.sort(timeStampsList);
        LogDataWriter logDataWriter = new LogDataWriter();
        logDataWriter.writeProcessedLogData(timeStampsList, logTimeToDataMap);
    }
}
