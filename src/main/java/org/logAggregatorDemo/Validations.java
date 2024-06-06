package org.logAggregatorDemo;

import java.io.*;

public class Validations {

    public static boolean validateFolderPath(String path) {
        return new File(path).isDirectory();
    }

    public static boolean validateFile(String path) {
        return path.endsWith(".log");


    }
}
