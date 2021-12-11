package com.company;

import java.util.*;
import java.util.concurrent.*;

public class Main {

    public static void main(String[] args) throws Exception {
	// write your code here
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        Set<Callable<String>> callables = new HashSet<Callable<String>>();

        callables.add(new Callable<String>() {
            public String call() throws Exception {
                return "first";
            }
        });
        callables.add(new Callable<String>() {
            public String call() throws Exception {
                return "Second";
            }
        });
        callables.add(new Callable<String>() {
            public String call() throws Exception {
                return "third";
            }
        });

        java.util.List<Future<String>> futures = executorService.invokeAll(callables);

        for(Future<String> future : futures){
            System.out.println("future.get = " + future.get());
        }

        executorService.shutdown();
    }
}
