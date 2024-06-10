package org.logAggregatorDemo;

import java.io.*;
import java.util.*;
import java.util.concurrent.Callable;

public class LogThread implements Callable<String> {

    private static String logFilesFolderPath;
    private static String outputFilePath = "C:\\Users\\sambit.sahu\\Resources\\DemoFolder\\OutputFile.txt";

    public LogThread(String path) {
        logFilesFolderPath = path;
    }

    static void logOperation() throws IOException {

        File[] fileList = (new File(logFilesFolderPath)).listFiles();
        if (fileList.length == 0) throw new RuntimeException("No files found");

        List<File> logFileList = Arrays.stream(fileList)
                .filter(file -> file.getName().endsWith(".log"))
                .toList();
        if (logFileList.isEmpty()) throw new RuntimeException("No LogFiles Detected");

        Map<String, List<String>> logMap = new HashMap<>();
        List<String> timeStampList = new ArrayList<>();

        String datePattern = "^[0-9]{4}/(0[1-9]|1[0-2])/(0[1-9]|[1-2][0-9]|3[01]).*";

        boolean checkLogData = false;

        for (File logFile : logFileList) {
            String filePath = String.valueOf(logFile);
            BufferedReader logReader = new BufferedReader(new FileReader(filePath));
            String logKey = "";
            String logValue = "";
            boolean check = false;
            String logDataLine = null;
            while ((logDataLine = logReader.readLine()) != null) {
                checkLogData = true;
                if (logDataLine.matches(datePattern)) {
                    if (check) {
                        if (!logMap.containsKey(logKey))
                            logMap.put(logKey, new ArrayList<>(Arrays.asList(logValue)));
                        else logMap.get(logKey).add(logValue);
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
        if (!checkLogData) throw new RuntimeException(("No Data in LogFiles Detected"));

        Collections.sort(timeStampList);

        BufferedWriter logWriter = new BufferedWriter(new FileWriter(outputFilePath));
        String timeStamp = "";

        for (String timeStampData : timeStampList) {
            if (!timeStamp.equals(timeStampData)) {
                String logData = "";
                timeStamp = timeStampData;
                List<String> logValues = logMap.get(timeStampData);
                for (String logValue : logValues) {
                    logData = timeStamp + logValue;
                    logWriter.write(logData);
                    logWriter.newLine();
                }
            }
        }
        logWriter.close();
    }

    @Override
    public String call() throws Exception {
        try {
            logOperation();
            return "Success ! Here is the Output FilePath : " + outputFilePath;
        } catch (Exception e) {
            return "Failed ! : " + e.getMessage();
        }
    }
}
