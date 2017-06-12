package root.utils;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Alexey on 10.06.2017.
 */
public class TimerService {

    private static TimerService timerService;
    private Timer timer;
    private static Object locker1 = new Object();
    private static Object locker2 = new Object();

    private TimerService() {

    }

    public static TimerService getInstance() {
        if(timerService == null) {
            synchronized (locker1) {
                if(timerService == null) {
                    timerService = new TimerService();
                }
            }
        }
        return timerService;
    }

    public void scheduleTask(TimerTask timerTask, long delay) {
        scheduleTask(timerTask, delay, 0);
    }

    public void scheduleTask(TimerTask timerTask, long delay, long period) {
        if(this.timer == null) {
            synchronized (locker2) {
                if(this.timer == null) {
                    this.timer = new Timer();
                }
            }
        }
        this.timer.schedule(timerTask, delay, period);
    }

    public void stop() {
        if(this.timer != null) {
            this.timer.cancel();
            this.timer.purge();
        }
    }

}
