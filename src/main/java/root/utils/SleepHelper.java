package root.utils;

/**
 * Created by Alexey on 05.06.2017.
 */
public final class SleepHelper {

    private SleepHelper() {
    }

    public static void sleep(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
