package org.logAggregatorTool.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@Getter
@Setter
public class LogRecord {

    String logFileFolderPath;
    int totalFiles;
    List<String> nameOfFiles;
    String dateOfOperation;
    String result;
    String outputFileName;
    String errorMessage;
}

