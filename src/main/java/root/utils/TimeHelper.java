package root.utils;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by Alexey on 10.06.2017.
 */
public final class TimeHelper {

    private TimeHelper() {
    }

    public static long getTimeInMs() {
        Date date = new Date();
        return date.getTime();
    }

    public static int getPOSIX() {
        Date date = new Date();
        int millisInSecond = 1000;
        return (int)(date.getTime() / millisInSecond);
    }

    public static String getUserDateFull(Locale locale) {
        Date date = new Date();
        DateFormat dateFormatter = DateFormat.getDateInstance(DateFormat.FULL, locale);
        return dateFormatter.format(date);
    }

    public static String getUserDateShort(Locale locale) {
        Date date = new Date();
        DateFormat dateFormatter = DateFormat.getDateInstance(DateFormat.SHORT, locale);
        return dateFormatter.format(date);
    }

}
