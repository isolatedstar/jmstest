package com.sishuok.jmstest.queue;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.ActiveMQMessageProducer;
import org.apache.activemq.MessageTransformer;

public class QueueGroupSender {
	public static void main(String[] args) throws Exception {
		ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(
				"tcp://192.168.1.106:61676");
		
		Connection connection = connectionFactory.createConnection();
		connection.start();

		Session session = connection.createSession(Boolean.TRUE,
				Session.CLIENT_ACKNOWLEDGE);
		
		Destination destination = session.createQueue("my-queue4");
		
		
		ActiveMQMessageProducer producer = (ActiveMQMessageProducer)session.createProducer(destination);
		
		for (int i = 0; i < 3; i++) {
			TextMessage message = session.createTextMessage("messageAA--" + i);
			
			message.setIntProperty("age", 10);
			
			message.setStringProperty("JMSXGroupID","GroupA");
			
			producer.send(message);
			
//			TextMessage message2 = session.createTextMessage("messageBB--" + i);
//message2.setStringProperty("JMSXGroupID","GroupB");
//			
//			producer.send(message2);
		}
		
		session.commit();
		session.close();
		connection.close();
	}
}