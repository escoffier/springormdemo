package com.example.xmljpademo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;

import java.util.concurrent.*;

@RestController
public class AppController {
    private static final Logger logger = LoggerFactory.getLogger(AppController.class);

   // private static final BlockingDeque queue = new ArrayBlockingQueue(200);
    private static final BlockingQueue queue = new ArrayBlockingQueue<>(20000);

    //private static ExecutorService executorService = Executors.newSingleThreadExecutor();
    private static Thread thread = new Thread( () -> {
        //ThreadLocalRandom threadLocalRandom = new ThreadLocalRandom();
        while (!Thread.currentThread().isInterrupted()) {
            try {
                DeferredResult<String> deferredResult = (DeferredResult<String>) queue.take();
                logger.info("process defered task {}",ThreadLocalRandom.current().nextInt());
                deferredResult.setResult("robbie");
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break; //optional, since the while loop conditional should detect the interrupted state
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    });

    static {
        thread.start();
    }

    @GetMapping(value = "/ruleTheWorld")
    public Callable<String>  rule() {
        logger.info("current thread {}", Thread.currentThread().getName() );
        return new Callable<String>() {
            @Override
            public String call() throws Exception {
                return "running in thread: " + Thread.currentThread().getName();
            }
        };
    }

    @GetMapping(value = "defertask")
    public DeferredResult<String> defer() {
        DeferredResult<String> deferredResult = new DeferredResult<>();
        queue.add(deferredResult);
        return deferredResult;
    }
}
