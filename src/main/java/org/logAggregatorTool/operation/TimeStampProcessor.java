package org.logAggregatorTool.operation;

import org.logAggregatorTool.constants.LogAggregatorToolConstants;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

public class TimeStampProcessor {

    public final List<SimpleDateFormat> POSSIBLE_DATE_FORMATS = Arrays.asList(new SimpleDateFormat(LogAggregatorToolConstants.POSSIBLE_DATE_FORMAT_1), new SimpleDateFormat(LogAggregatorToolConstants.POSSIBLE_DATE_FORMAT_2));

    public Date parsingTimeStamp(String timeStamp) {
        for (SimpleDateFormat possibleDateFormat : POSSIBLE_DATE_FORMATS) {
            try {
                return possibleDateFormat.parse(timeStamp);
            } catch (ParseException parseException) {
//                System.out.println(LogAggregatorToolConstants.PARSING_TIMESTAMP_ERROR);
            }
        }
        return new Date(LogAggregatorToolConstants.DEFAULT_INT_VALUE);
    }

    public void sortTimeStamp(List<String> timeStampList) {
        Comparator<String> timeStampComparator = (timeStampValue1, timeStampValue2) -> {
            Date timeStamp1 = parsingTimeStamp(timeStampValue1);
            Date timeStamp2 = parsingTimeStamp(timeStampValue2);
            return timeStamp1.compareTo(timeStamp2);
        };
        timeStampList.sort(timeStampComparator);
    }

}
