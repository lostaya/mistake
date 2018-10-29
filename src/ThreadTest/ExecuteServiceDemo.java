package ThreadTest;


import java.util.List;
import java.util.concurrent.*;

public class ExecuteServiceDemo {

    public static void main(String [] args){

        List list = new CopyOnWriteArrayList<>();
        BlockingQueue queue = new LinkedBlockingQueue<>();

        //无界
        //ExecutorService executorService = Executors.newCachedThreadPool();
        //换线程池
        ThreadPoolExecutor productPool = new ThreadPoolExecutor(10,20,60, TimeUnit.MILLISECONDS,queue);

        CompletionService<String> completionService = new ExecutorCompletionService(productPool);
        ExecuteServiceDemo executeServiceDemo = new ExecuteServiceDemo();

        // 十个
        long startTime = System.currentTimeMillis();
        int count = 0;
        for (int i = 0;i < 10;i ++) {
            count ++;
            GetContentTask getContentTask = new ExecuteServiceDemo.GetContentTask("micro" + i, 10);
            completionService.submit(getContentTask);
        }

        System.out.println("提交完任务，主线程空闲了, 可以去做一些事情。");
        // 假装做了8秒种其他事情
        try {
            Thread.sleep(8000);
            System.out.println("主线程做完了，等待结果");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        try {
            // 做完事情要结果
            for (int i = 0;i < count;i ++) {
                Future<String> result = completionService.take();
                System.out.println(result.get());
            }
            long endTime = System.currentTimeMillis();
            System.out.println("耗时 : " + (endTime - startTime) / 1000);
        }  catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    static class GetContentTask implements Callable<String> {

        private String name;

        private Integer sleepTimes;

        public GetContentTask(String name, Integer sleepTimes) {
            this.name = name;
            this.sleepTimes = sleepTimes;
        }
        public String call() throws Exception {
            // 假设这是一个比较耗时的操作
            Thread.sleep(sleepTimes * 1000);
            return "当前线程:"+Thread.currentThread().getName()+"this is content : hello " + this.name;
        }

    }


}