package ThreadTest.TreadPool;

import util.TestMQConsumerQueue;
import util.TestMQProducerQueue;

/**
 * Created by yp on 18-10-8.
 */
public class DoServiceMain {



    public static void main(String[] args) {
        System.out.println("now we send message for test!");
        SimulationMQSend testmq;
        TestMQConsumerQueue testgetmq;

        try {
            //发送
            testmq = new SimulationMQSend();
            //testmq.testMQproducerQueue();

            ////接受
            testgetmq = new TestMQConsumerQueue();
            testgetmq.TestMQconsumerQueue();

            ////接受
            //mb = new ReciveMessage();
            //mb.reciveMessage();


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
