package ThreadTest.TreadPool;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.*;

public class TreadRecive {

    public static void main(String[] args) {
        BlockingQueue queue = new LinkedBlockingQueue<>();
//        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(4,4,60, TimeUnit.DAYS,queue);
        Executor executor = Executors.newCachedThreadPool();
        ThreadPoolExecutor threadPoolExecutor2 = new ThreadPoolExecutor(8,8,60, TimeUnit.DAYS,queue);

        for (int i=1;i<=20;i++){
            Product product = new Product(String.valueOf(i));
//            threadPoolExecutor.execute(product);
            executor.execute(product);

//            System.out.println("生产完 当前队列里有个数："+queue.size());
            Consumor consumor = new Consumor(product);
            threadPoolExecutor2.execute(consumor);
        }

    }


}
