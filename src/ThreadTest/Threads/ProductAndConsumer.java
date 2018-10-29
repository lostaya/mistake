package ThreadTest.Threads;

import ThreadTest.ReciveMessage;

import java.util.LinkedList;
import java.util.Map;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created by yp on 18-9-29.
 */
public class ProductAndConsumer {
    public static void main(String[] args) throws Exception {

        LinkedBlockingQueue<Map> queue = new LinkedBlockingQueue<>();
        //为多生产者和多消费者分别开创的线程池
        ThreadPoolExecutor productPool =
                new ThreadPoolExecutor(10,20,60, TimeUnit.MILLISECONDS,new LinkedBlockingQueue<>(),new ThreadPoolExecutor.CallerRunsPolicy());
        ThreadPoolExecutor consumerPool =
                new ThreadPoolExecutor(10,20,60,TimeUnit.MILLISECONDS,new LinkedBlockingQueue<>(),new ThreadPoolExecutor.CallerRunsPolicy());

        System.out.println("start");

        //模拟并发实验
        for(int i = 1; i<=20; i++){



            ReciveMessage reciveMessage = new ReciveMessage(i);
            productPool.execute(new ProductThread(reciveMessage.reciveMessage(),queue));

        }
        productPool.shutdown();


        //consumerPool.shutdown();

    }

}
