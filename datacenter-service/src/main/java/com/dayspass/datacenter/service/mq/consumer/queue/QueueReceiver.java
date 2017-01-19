package com.dayspass.datacenter.service.mq.consumer.queue;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

/**
 * 队列消息监听器
 * @author zhangcb
 *
 */
public class QueueReceiver implements MessageListener {

	@Override
	public void onMessage(Message message) {
		try {
			System.out.println("QueueReceiver1接收到消息:"+((TextMessage) message).getText());
			//message.acknowledge();
		} catch (JMSException e) {
			e.printStackTrace();
		}
	}

}
