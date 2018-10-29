package ThreadTest.TreadPool;

import org.apache.activemq.*;
import org.apache.activemq.broker.jmx.ManagedQueueRegion;
import org.springframework.stereotype.*;

import javax.jms.*;

public class SimulationMQSend {

    private static final String USERNAME = ActiveMQConnection.DEFAULT_USER;
    private static final String PASSWORD = ActiveMQConnection.DEFAULT_PASSWORD;
    private static final String URL = ActiveMQConnection.DEFAULT_BROKER_URL;
    private static final int SENDNUM = 20;//发送20条模拟消息

    ConnectionFactory connectionFactory = null;
    private Connection connection = null;
    private Session session = null;
    private Destination destination = null;//队列名

    public void init(){
        connectionFactory = new ActiveMQConnectionFactory(SimulationMQSend.USERNAME,SimulationMQSend.PASSWORD,SimulationMQSend.URL);
        try {
            connection = connectionFactory.createConnection();
            connection.start();
        } catch (JMSException e) {
            e.printStackTrace();
        }

    }

    public void produce() throws Exception{

        try {
            MessageProducer messageProducer; // 消息生产者
            session=connection.createSession(false, Session.AUTO_ACKNOWLEDGE); // 创建Session
            destination=session.createQueue("test-test");
            messageProducer=session.createProducer(destination); // 创建消息生产者

            for(int i=0;i<=SimulationMQSend.SENDNUM;i++){
                TextMessage message=session.createTextMessage("ActiveMQ中"+Thread.currentThread().getName()+"线程发送的数据"+":"+i);
                System.out.println(Thread.currentThread().getName()+"线程"+"发送消息："+"ActiveMQ 发布的消息"+":"+i);
                messageProducer.send(message);
                session.commit();
                
            }
        } catch (JMSException e) {
            e.printStackTrace();
        }

        //MapMessage mapmessage = session.createMapMessage();

        //mapmessage.setString("width","1080");
        //mapmessage.setString("height","760");
        //mapmessage.setInt("codeBitRate",3000000); //300KPBS
        //mapmessage.setString("codeFormat","avcodec.AV_CODEC_ID_H264"); //h264
        //mapmessage.setString("vedioFormat","MP4");
        //mapmessage.setString("audioCode","avcodec.AV_CODEC_ID_AAC"); //aac
        //mapmessage.setInt("audioBitRate",192000);
        //producer.send(mapmessage);



        //关闭源
        //producer.close();
        session.close();
        connection.close();

    }

}
