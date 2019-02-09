package com.htp.domain.date;

import com.htp.controller.command.util.CommandInterface;
import com.sun.org.apache.regexp.internal.RE;

import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class CurrentDate implements DateInterface {

private static String DATE_REGEX="yyyy-MM-dd";

    public CurrentDate() {
    }

    public static DateInterface getInstance() {
        return SingletonHolder.instance;
    }

    private static class SingletonHolder {
        private static final DateInterface instance = new CurrentDate();
    }

    @Override
    public Date date() {
        DateFormat dateFormat = new SimpleDateFormat(DATE_REGEX);
        Date date = new Date(System.currentTimeMillis());
        return Date.valueOf(dateFormat.format(date));
    }
}
