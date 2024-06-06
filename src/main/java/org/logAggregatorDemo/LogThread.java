package org.logAggregatorDemo;

import java.io.*;
import java.nio.*;

public class LogThread implements Runnable {

    String folderPath;
    String filesData;


    public LogThread(String path) {
        this.folderPath = path;
    }


    @Override
    public void run() {
        File[] files = (new File(folderPath)).listFiles();
//        System.out.println("No of files are :" + files[0]);
        for (int i = 0; i < files.length - 1; i++) {
            String filePath = files[i].toString();
            try {
                BufferedReader reader = new BufferedReader(new FileReader(filePath));

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
