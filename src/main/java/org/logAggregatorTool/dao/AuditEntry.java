package org.logAggregatorTool.dao;

import org.logAggregatorTool.dto.LogRecord;
import org.logAggregatorTool.utility.JdbcConstants;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class AuditEntry {
    /**
     * This method has the database logic to insert run logs to database AUDIT table
     *
     * @param logRecord LogRecord object which have all the data to be inserted in the database
     */
    public void auditEntryOperation(LogRecord logRecord) {
        String logFileNames = "";
        if (logRecord.getNameOfFiles() != null) logFileNames = String.join(",", logRecord.getNameOfFiles());
        try (Connection logConnection = DriverManager.getConnection(JdbcConstants.URL, JdbcConstants.USER_NAME, JdbcConstants.PASSWORD)) {
            PreparedStatement logPreparedStatement = logConnection.prepareStatement(JdbcConstants.SQL_INSERT_AUDIT_DATA_QUERY);
            logPreparedStatement.setString(1, logRecord.getLogFileFolderPath());
            logPreparedStatement.setInt(2, logRecord.getTotalFiles());
            logPreparedStatement.setString(3, logFileNames);
            logPreparedStatement.setString(4, logRecord.getDateOfOperation());
            logPreparedStatement.setString(5, logRecord.getResult());
            logPreparedStatement.setString(6, logRecord.getOutputFileName());
            logPreparedStatement.setString(7, logRecord.getErrorMessage());
            logPreparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

