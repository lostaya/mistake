package util;

import org.apache.activemq.*;
import org.apache.activemq.broker.jmx.ManagedQueueRegion;
import org.springframework.stereotype.*;

import javax.jms.*;


/**
 * Created by yp on 18-9-26.
 */


public class TestMQProducerQueue{

    public void testMQproducerQueue() throws Exception{
        //创建工厂 定制IP
        ConnectionFactory connetionfactory = new ActiveMQConnectionFactory("tcp://localhost:61616");
        //使用工厂  创建连接对象
        Connection connection = connetionfactory.createConnection();
        //开启连接
        connection.start();
        //使用连接对象创建会话(session)对象
        Session session = connection.createSession(false,Session.AUTO_ACKNOWLEDGE);
        //使用会话对象创建目标对象，包含queue和topic（一对一和一对多）
        Queue queue = session.createQueue("test-test");
        //使用会话对象创建生产者对象
        MessageProducer producer = session.createProducer(queue);
        //使用会话创建一个消息对象
        /** JMS定义了五种消息正文格式，以及消息的调用类型，允许发送和接收一些不同类型的数据，提供现有消息格式的一些级别的兼容性。

         StreamMessage：--JAVA原始的数据流

         TextMessage：一个字符串对象

         ObjectMessage：一个系列化的java对象

         BytesMessage：一个字节对象

         MapMessage：key/value方式的键值对
         */

        //测试  宽 高 视频码率 视频编码 视频格式 音频编码 音频码率
        //MapMessage mapmessage = session.createMapMessage();

        //mapmessage.setString("width","1080");
        //mapmessage.setString("height","760");
        //mapmessage.setInt("codeBitRate",3000000); //300KPBS
        //mapmessage.setString("codeFormat","avcodec.AV_CODEC_ID_H264"); //h264
        //mapmessage.setString("vedioFormat","MP4");
        //mapmessage.setString("audioCode","avcodec.AV_CODEC_ID_AAC"); //aac
        //mapmessage.setInt("audioBitRate",192000);

        TextMessage textMessage = session.createTextMessage("nononono");
        //再次生成
        //textMessage.setText("okokok");

        ////发送消息
        producer.send(textMessage);

        //producer.send(mapmessage);
        //关闭源
        producer.close();
        session.close();
        connection.close();


    }


}

