package com.epam.liavitskaya.main.controller.server;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.apache.log4j.Logger;

public class Server {

	final static Logger logger = Logger.getLogger(Server.class);

	private static Queue<String> requestsServer = new ConcurrentLinkedQueue<String>();
	static List<String> responseList = new LinkedList<>();

	public static List<String> startServer(String request) {

		requestsServer.add(request);
		ExecutorService executor = Executors.newCachedThreadPool();
		List<Future<String>> futureList = new LinkedList<Future<String>>();
		Callable<String> callable = new RequestDistributor(requestsServer);

		for (int i = 0; i < requestsServer.size(); i++) {
			Future<String> future = executor.submit(callable);
			futureList.add(future);
		}
		for (Future<String> futureResponse : futureList) {
			try {
				String response = futureResponse.get();
				responseList.add(response);
			} catch (InterruptedException | ExecutionException e) {
				logger.error("Error during executing multithreading", e);
			}
		}
		if (executor.isTerminated()) {
			executor.shutdown();
		}

		return responseList;
	}
}
