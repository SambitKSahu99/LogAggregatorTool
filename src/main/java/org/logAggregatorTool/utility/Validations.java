package org.logAggregatorTool.utility;

import java.io.File;

public class Validations {
    /**
     * Accepts the log File Folder path and validates it
     * @param path log Files Folder path
     * @return true if validates or else false
     */
    public static boolean validateFolderPath(String path) {
        return new File(path).isDirectory();
    }
}
