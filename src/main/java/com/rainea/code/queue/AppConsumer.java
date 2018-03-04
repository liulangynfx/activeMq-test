package com.rainea.code.queue;

import com.rainea.code.Constant;
import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;
import javax.xml.soap.Text;

public class AppConsumer {

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
        Destination destination = session.createQueue(Constant.QUEUE_NAME);
        //创建消费者
        MessageConsumer consumer = session.createConsumer(destination);

        //创建一个监听器
        consumer.setMessageListener(new MessageListener() {
            public void onMessage(Message message) {
                TextMessage textMessage = (TextMessage) message;
                try {
                    System.out.println("接受队列消息：" + textMessage.getText());
                } catch (JMSException e) {
                    e.printStackTrace();
                }
            }
        });


        //关闭连接
//        connection.close();
    }
}
