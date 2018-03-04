package com.rainea.code.topic;

import com.rainea.code.Constant;
import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

public class AppProducer {

    public static void main(String[] args) throws JMSException {
        //创建连接工厂
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(Constant.URL);
        //创建连接
        Connection connection = connectionFactory.createConnection();
        //连接启动
        connection.start();

        //创建session
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        //创建目标
        Destination destination = session.createTopic(Constant.TOPIC_NAME);
        //创建生产者
        MessageProducer producer = session.createProducer(destination);

        for (int i=0; i < 100; i++) {
            //创建消息
            TextMessage textMessage = session.createTextMessage("msg..." + i);
            //发布消息
            producer.send(textMessage);
            System.out.println("发布主题消息：" + textMessage.getText());
        }

        //关闭连接
        connection.close();
    }
}
