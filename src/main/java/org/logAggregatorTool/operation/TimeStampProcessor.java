package org.logAggregatorTool.operation;

import org.logAggregatorTool.constants.LogAggregatorToolConstants;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

public class TimeStampProcessor {

    public final List<SimpleDateFormat> POSSIBLE_DATE_FORMATS = Arrays.asList(new SimpleDateFormat(LogAggregatorToolConstants.YYYY_MM_DD_DATE_FORMAT), new SimpleDateFormat(LogAggregatorToolConstants.MM_DD_YYYY_DATE_FORMAT));

    public Date parsingTimeStamp(String timeStamp) {
        try {
            if (timeStamp.indexOf(LogAggregatorToolConstants.FORWARD_SLASH) == LogAggregatorToolConstants.INT_VALUE_TWO) {
                return POSSIBLE_DATE_FORMATS.get(LogAggregatorToolConstants.INT_VALUE_ONE).parse(timeStamp);
            } else {
                return POSSIBLE_DATE_FORMATS.get(LogAggregatorToolConstants.DEFAULT_INT_VALUE).parse(timeStamp);
            }
        } catch (ParseException parseException) {
            System.out.println(LogAggregatorToolConstants.PARSING_TIMESTAMP_ERROR);
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
