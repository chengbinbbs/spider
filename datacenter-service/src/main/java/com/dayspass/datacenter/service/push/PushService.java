package com.dayspass.datacenter.service.push;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public interface PushService {

	 public final ExecutorService pushExecutor = Executors.newFixedThreadPool(10);  
     
	 public void push(Object info);  
}
