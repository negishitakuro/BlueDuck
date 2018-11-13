package com.t_negi.www.blueduck.utils;

import android.util.Log;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;


public class DateUtils {
    private static final String DATE_FORMAT_TWITTER = "EEE MMM dd HH:mm:ss ZZZZZ yyyy";
    private static final String DATE_FORMAT_YMD = "yyyy/MM/dd";
    private static SimpleDateFormat SF_TWITTER_FORMAT = new SimpleDateFormat(DATE_FORMAT_TWITTER, Locale.ENGLISH);
    private static SimpleDateFormat SF_YMD = new SimpleDateFormat(DATE_FORMAT_YMD, Locale.ENGLISH);

    private static Date convertToDate(String twitterDate) {
        try {
            return SF_TWITTER_FORMAT.parse(twitterDate);
        } catch (ParseException e) {
            Log.e("", e.getMessage());
            return null;
        }
    }

    public static String getDateStringFromTwitterDate(String twitterDate) {
        Date d = convertToDate(twitterDate);
        if (d == null) return "";

        return SF_YMD.format(d);
    }
}
