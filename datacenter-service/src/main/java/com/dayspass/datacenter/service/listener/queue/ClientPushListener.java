package com.dayspass.datacenter.service.listener.queue;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.dayspass.datacenter.bean.Client;

//@Component("clientPushListener")
public class ClientPushListener implements MessageListener {

	private static Logger logger = LoggerFactory.getLogger(ClientPushListener.class);  
    @Override  
    public void onMessage(Message message) {  
        logger.info("[ClientPushListener.onMessage]:begin onMessage.");  
        TextMessage textMessage = (TextMessage) message;  
        try {  
            //获取数据  
            String jsonStr = textMessage.getText();  
            logger.info("[ClientPushListener.onMessage]:receive message is,"+ jsonStr);  
            if (jsonStr != null) {  
                Client info = JSON.parseObject(jsonStr, Client.class);  
                System.out.println("==============================接受到的客户端信息 开始====================================");  
                System.out.println(info.toString());  
                System.out.println("==============================接受到的客户端信息 结束====================================");  
             //   WebsocketController.broadcast("user", jsonStr);  
            }  
        } catch (JMSException e) {  
            logger.error("[ClientPushListener.onMessage]:receive message occured an exception",e);  
        }  
        logger.info("[ClientPushListener.onMessage]:end onMessage.");  
    }  

}
