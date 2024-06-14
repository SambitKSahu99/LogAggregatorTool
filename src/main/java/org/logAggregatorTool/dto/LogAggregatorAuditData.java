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
    private String logFileFolderPath;
    private int totalFiles;
    private List<String> nameOfFiles;
    private String dateOfOperation;
    private String result;
    private String outputFileName;
    private String errorMessage;
}
