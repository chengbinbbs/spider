package com.dayspass.datacenter.service.listener.topic;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

public class TopicMessageListener implements MessageListener {

	@Override
	public void onMessage(Message message) {
		TextMessage tm = (TextMessage) message;
		try {
			System.out.println("TopicMessageListener 监听到消息：\t" + tm.getText());
		} catch (JMSException e) {
			e.printStackTrace();
		}
	}

}
