package ThreadTest.TreadPool;

import java.util.concurrent.*;

/**
 * Created by yp on 18-10-11.
 */
public class ThreadPoolUtils {
    private ThreadPoolUtils(){

    }

    private static final BlockingQueue queue = new LinkedBlockingQueue<>();
    private static final ThreadPoolExecutor threadPoolExecutor2 = new ThreadPoolExecutor(8, 8, 60, TimeUnit.DAYS, queue);


    public static ThreadPoolExecutor getThreadPoolExecutor2() {
        return threadPoolExecutor2;
    }
}
