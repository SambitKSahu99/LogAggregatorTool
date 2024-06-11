package org.logAggregatorTool.utility;

public class JdbcConstants {
    public static final String CLASS_NAME = "com.mysql.cj.jdbc.Driver";
    public static final String URL = "jdbc:mysql://localhost:3306/poc_1";
    public static final String USER_NAME = "root";
    public static final String PASS_WORD = "root";
    public static final String SQL_INSERT_AUDIT_DATA_QUERY = "insert into audit (logfile_folder_path,total_files,name_of_files,date_time_of_operation,result,output_file_name,error_message) values (?,?,?,?,?,?,?)";

}
