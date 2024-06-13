package org.logAggregatorTool.constants;

/**
 * this class has all the database constants used in the dao
 */
public class DatabaseConstants {
    public static final String COMMA_VALUE = ",";
    public static final String DATABASE_EXCEPTION_MESSAGE = "Error Occurred in database : ";
    public static final String PASSWORD = "root";
    public static final String SQL_INSERT_AUDIT_DATA_QUERY = "insert into audit (logfile_folder_path,total_files,name_of_files,date_time_of_operation,result,output_file_name,error_message) values (?,?,?,?,?,?,?)";
    public static final String URL = "jdbc:mysql://localhost:3306/poc_1";
    public static final String USER_NAME = "root";
}
