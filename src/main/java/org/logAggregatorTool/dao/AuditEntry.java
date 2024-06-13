package org.logAggregatorTool.dao;

import org.logAggregatorTool.constants.LogAggregatorToolConstants;
import org.logAggregatorTool.dto.LogAggregatorAuditData;
import org.logAggregatorTool.constants.DatabaseConstants;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class AuditEntry {
    /**
     * This method has the database logic to insert run logs to database AUDIT table
     *
     * @param logAggregatorAuditData LogRecord object which have all the data to be inserted in the database
     */
    public void auditEntryOperation(LogAggregatorAuditData logAggregatorAuditData) {
        String logFileNames = LogAggregatorToolConstants.EMPTY_STRING;
        if (logAggregatorAuditData.getNameOfFiles() != null) logFileNames = String.join(DatabaseConstants.COMMA_VALUE, logAggregatorAuditData.getNameOfFiles());
        try (Connection logConnection = DriverManager.getConnection(DatabaseConstants.URL, DatabaseConstants.USER_NAME, DatabaseConstants.PASSWORD)) {
            PreparedStatement logPreparedStatement = logConnection.prepareStatement(DatabaseConstants.SQL_INSERT_AUDIT_DATA_QUERY);
            logPreparedStatement.setString(1, logAggregatorAuditData.getLogFileFolderPath());
            logPreparedStatement.setInt(2, logAggregatorAuditData.getTotalFiles());
            logPreparedStatement.setString(3, logFileNames);
            logPreparedStatement.setString(4, logAggregatorAuditData.getDateOfOperation());
            logPreparedStatement.setString(5, logAggregatorAuditData.getResult());
            logPreparedStatement.setString(6, logAggregatorAuditData.getOutputFileName());
            logPreparedStatement.setString(7, logAggregatorAuditData.getErrorMessage());
            logPreparedStatement.executeUpdate();
            logPreparedStatement.close();
        } catch (Exception exception) {
            System.out.println(DatabaseConstants.DATABASE_EXCEPTION_MESSAGE+exception.getMessage());
            exception.printStackTrace();
        }
    }
}
