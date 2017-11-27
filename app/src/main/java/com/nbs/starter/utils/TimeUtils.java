package com.nbs.starter.utils;

import android.text.format.DateUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by ghiyatshanif on 6/5/17.
 */

public class TimeUtils {

    public static String getRelativeTime(String unformatted) {
        String formatted = "";

        String[] date = unformatted.split("T");
        String[] hours = date[1].split(".000Z");

        formatted = date[0] + " " + hours[0];

        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date d;
        try {
            d = df.parse(formatted);
        } catch (ParseException e) {
            e.printStackTrace();
            d = null;
        }

        CharSequence timePassed = "";
        if (d != null) {
            long epoch = d.getTime();
            timePassed = DateUtils.getRelativeTimeSpanString(epoch, System.currentTimeMillis(), DateUtils.SECOND_IN_MILLIS);
        }

        return timePassed.toString();

    }

}
