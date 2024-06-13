package org.logAggregatorTool.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

/**
 * This class consists the data to be inserted in the columns of AUDIT table
 */
@NoArgsConstructor
@Getter
@Setter
public class LogAggregatorAuditData {
    String logFileFolderPath;
    int totalFiles;
    List<String> nameOfFiles;
    String dateOfOperation;
    String result;
    String outputFileName;
    String errorMessage;
}
