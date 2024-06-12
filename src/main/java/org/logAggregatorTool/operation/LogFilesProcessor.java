package org.logAggregatorTool.operation;

import org.logAggregatorTool.reader.LogDataReader;
import org.logAggregatorTool.writer.LogDataWriter;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


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
        List<String> timeStampList = new ArrayList<>();
        LogDataReader logDataReader = new LogDataReader();
        logDataReader.readLogFileData(logFileList, timeStampList, logTimeToDataMap);
        Collections.sort(timeStampList);
        LogDataWriter logDataWriter = new LogDataWriter();
        logDataWriter.writeProcessedLogData(timeStampList, logTimeToDataMap);
    }
}


