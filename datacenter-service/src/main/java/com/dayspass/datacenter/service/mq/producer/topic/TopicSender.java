package com.dayspass.datacenter.service.mq.producer.topic;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Component;

/**
 * 发送消息到主题
 * @author zhangcb
 *
 */
// @Component
public class TopicSender {

	@Autowired
	@Qualifier("topicJmsTemplate")
	private JmsTemplate jmsTemplate;
	
	/**
	 * 发送一条消息到指定的队列（目标）
	 * 
	 * @param topicName
	 *            队列名称
	 * @param message
	 *            消息内容(注意发送端和接收端的对象的包名必须一致，且要实现Serialize接口)
	 */
	public void send(String topicName, final String message) {
		jmsTemplate.send(topicName, new MessageCreator() {
			@Override
			public Message createMessage(Session session) throws JMSException {
				return session.createTextMessage(message);
			}
		});
	}
}
