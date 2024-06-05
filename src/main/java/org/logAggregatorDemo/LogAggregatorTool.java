package org.logAggregatorDemo;

import java.io.File;

public class LogAggregatorTool {
    public static void main(String[] args) {

//      Taking user input through command line
        String folderPath = args[0];
        System.out.println(new File(folderPath).getName());

    }


}