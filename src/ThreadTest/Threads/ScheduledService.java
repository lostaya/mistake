package ThreadTest.Threads;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Created by yp on 18-9-30.
 */
public class ScheduledService {

    static class MyScheduledExecutor implements Runnable {

        private String jobName;

        MyScheduledExecutor() {

        }

        MyScheduledExecutor(String jobName) {
            this.jobName = jobName;
        }

        @Override
        public void run() {

            System.out.println(jobName + " is running");
        }
    }



    public static void main(String[] args) {
        ScheduledExecutorService service = Executors.newScheduledThreadPool(10);

        long initialDelay = 1;
        long period = 1;
        // 从现在开始1秒钟之后，每隔1秒钟执行一次job1  基于固定时间间隔进行任务调度
        service.scheduleAtFixedRate(new MyScheduledExecutor("job1"), initialDelay, period, TimeUnit.SECONDS);

        // 从现在开始2秒钟之后，每隔2秒钟执行一次job2  取决于每次任务执行的时间长短
        //service.scheduleWithFixedDelay(new MyScheduledExecutor("job2"), initialDelay, period, TimeUnit.SECONDS);
    }

}
