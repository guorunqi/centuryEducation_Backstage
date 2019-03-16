package com.example.demo.util;


import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class DateUtil {
    /**
     * 英制时间转Date类型
     * @param data
     * @return
     * @throws ParseException
     */
    public Date getDateFormat(String data) throws ParseException {
        String TimeStart = data.replace("Z", " UTC");
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date FormDate = format.parse(TimeStart);
        return FormDate;
    }
}