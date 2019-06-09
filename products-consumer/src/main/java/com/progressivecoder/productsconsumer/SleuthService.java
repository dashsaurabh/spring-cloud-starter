package com.progressivecoder.productsconsumer;

import brave.Span;
import brave.Tracer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.logging.Logger;

@Service
public class SleuthService {

    Logger logger = Logger.getLogger("SleuthService");

    @Autowired
    private Tracer tracer;

    public void sameSpanWork(){
        logger.info("Doing some work");

    }

    public void newSpanWork(){
        logger.info("Original span going on");

        Span newSpan = tracer.nextSpan().name("new sleuth span").start();

        try(Tracer.SpanInScope span = tracer.withSpanInScope(newSpan.start())){
            logger.info("This work is being done in the new span");
        }finally {
            newSpan.finish();
        }

        logger.info("Back to original span");
    }
}
