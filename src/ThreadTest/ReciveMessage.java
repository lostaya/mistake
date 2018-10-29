package ThreadTest;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class ReciveMessage{
    LinkedList<Map> linkedList = new LinkedList<Map>();
    Map map = new HashMap();

    //测试参数
    private Integer i;

    public ReciveMessage(){

    }

    //构造i
    public ReciveMessage(int i){
        this.i = i;
    }

    //接受消息队列
    public LinkedList<Map> reciveMessage() throws Exception {
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://localhost:61616");
        Connection connection = connectionFactory.createConnection();
        connection.start();
        //获得session 自动获取
        Session session = connection.createSession(false,Session.AUTO_ACKNOWLEDGE);
        Queue queue = session.createQueue("test-test");
        MessageConsumer consumer = session.createConsumer(queue);
        consumer.setMessageListener(new MessageListener() {
            @Override
            public void onMessage(Message message) {
                if(message instanceof MapMessage){
                    MapMessage mapmessage = (MapMessage)message;
                    try {
                        System.out.println(mapmessage.getString("width"));
                        System.out.println(mapmessage.getString("height"));
                        System.out.println(mapmessage.getString("codeBitRate"));
                        System.out.println(mapmessage.getString("codeFormat"));
                        System.out.println(mapmessage.getString("vedioFormat"));
                        System.out.println(mapmessage.getString("audioCode"));
                        System.out.println(mapmessage.getString("audioBitRate"));

                        //存储map
                        map.put("width",mapmessage.getString("width"));

                        //test
                        map.put("width",i);
                        map.put("height",mapmessage.getString("height"));
                        map.put("codeBitRate",mapmessage.getString("codeBitRate"));
                        map.put("codeFormat",mapmessage.getString("codeFormat"));
                        map.put("vedioFormat",mapmessage.getString("vedioFormat"));
                        map.put("audioCode",mapmessage.getString("audioCode"));
                        map.put("audioBitRate",mapmessage.getString("audioBitRate"));

                        linkedList.add(map);

                    } catch (JMSException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        System.out.println("存在的linklist数量"+linkedList.size());

        //9、关闭资源
        consumer.close();
        session.close();
        connection.close();

        return linkedList;

    }


}
