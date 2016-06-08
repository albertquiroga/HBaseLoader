package com.convector.hbaseloader.utils;

import java.util.concurrent.TimeUnit;

/**
 * Created by pyro_ on 14/05/2016.
 */
public abstract class Utils {

    public static String formatTimeInMs (long timeMilis) {
        return String.format("%02d:%02d:%02d",
                TimeUnit.MILLISECONDS.toHours(timeMilis),
                TimeUnit.MILLISECONDS.toMinutes(timeMilis) - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(timeMilis)),
                TimeUnit.MILLISECONDS.toSeconds(timeMilis) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(timeMilis)));
    }
}
