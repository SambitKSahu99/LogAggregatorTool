package org.logAggregatorDemo;

import java.io.*;

public class Validations {

    public static boolean validateFolderPath(String path) {
        return new File(path).isDirectory();
    }
}
