package ThreadTest.Threads;

import ThreadTest.ReciveMessage;

import java.util.LinkedList;
import java.util.Map;
import java.util.concurrent.LinkedBlockingQueue;


/**
 * Created by yp on 18-9-29.
 */
public class ProductThread implements Runnable{
    private LinkedList llist;
    private LinkedBlockingQueue queue;
    private LinkedList<Map> taskNum;


    public ProductThread(LinkedList<Map> taskNum,LinkedBlockingQueue queue) {
        this.taskNum = taskNum;
        this.queue = queue;
    }

    public void run() {
        try {
            //模拟生产
            Thread.currentThread().sleep(1000);
            //ReciveMessage reciveMessage = new ReciveMessage();
            //reciveMessage.reciveMessage();

            System.out.println("开始生产");
            queue.add(taskNum);
            System.out.println("队列内容:"+taskNum);
            System.out.println("当前队列长度:"+queue.size());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



}
