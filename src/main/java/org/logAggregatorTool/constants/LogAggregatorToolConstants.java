package org.logAggregatorTool.constants;

/**
 * this class has all the constants values used in the project
 */
public class LogAggregatorToolConstants {
    public static final String COMMA_VALUE = ",";
    public static final String DATABASE_EXCEPTION_MESSAGE = "Error Occurred in database : ";
    public static final String YEAR_MONTH_DATE_REGEX_PATTERN = "^[0-9]{4}/(0[1-9]|1[0-2])/(0[1-9]|[1-2][0-9]|3[01]).*";
    public static final String MONTH_DATE_YEAR_REGEX_PATTERN = "(0[1-9]|1[0-2])/(0[1-9]|[1-2][0-9]|3[01])/[0-9]{4}.*";
    public static final String DATA_NOT_FOUND_MESSAGE = "No Data found on the Log File";
    public static final int DEFAULT_INT_VALUE = 0;
    public static final String DEFAULT_STRING_VALUE = "";
    public static final String ENTER_LOG_FILE_FOLDER_PATH_MESSAGE = "Please enter the folder path where log files are located :";
    public static final String EXIT_MESSAGE = "As you entered N the program will be closed ! Thank You";
    public static final String FAILURE = "Failure";
    public static final String FAILED_MESSAGE = "Failed ! ";
    public static final String FILE_NOT_FOUND_MESSAGE = "No File Found on the specified folder";
    public static final String INVALID_FILE_MESSAGE = "These files are not valid log files : ";
    public static final String INVALID_PATH_MESSAGE = "\nYou entered an invalid path, If you wish to continue type Y or N";
    public static final String LOG_FILE_EXTENSION = ".log";
    public static final String LOG_FILE_NOT_FOUND_MESSAGE = "No Log File Found on the folder";
    public static final String OUTPUT_FILE_PATH = "C:\\Users\\sambit.sahu\\Resources\\DemoFolder\\OutputFile.txt";
    public static final String PARSING_TIMESTAMP_ERROR = "Error";
    public static final String POSSIBLE_DATE_FORMAT_1 = "yyyy/MM/dd HH:mm:ss:SSS";
    public static final String POSSIBLE_DATE_FORMAT_2 ="MM/dd/yyyy HH:mm:ss.SSS";
    public static final String PROCESSING_MESSAGE = "Processing.....";
    public static final String SINGLE_SPACE = " ";
    public static final String SUCCESS = "Success";
    public static final String SUCCESS_AND_OUTPUT_FILE_PATH_MESSAGE = "Success ! Here is the Output File FolderPath : " + OUTPUT_FILE_PATH;
    public static final int SUBSTRING_END_INDEX = 24;
    public static final String USER_PERMISSION_N = "n";
    public static final String USER_PERMISSION_Y = "y";
    public static final String USER_EXIT_MESSAGE = "user entered invalid folder path and exited";
    public static final String WELCOME_MESSAGE = "**** Log Aggregator Tool ****";
}
