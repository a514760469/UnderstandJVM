package com.concurrent.log;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author zhanglifeng
 * @since 2020-06-22 18:35
 */
public class UEHLogger implements Thread.UncaughtExceptionHandler {

    @Override
    public void uncaughtException(Thread t, Throwable e) {
        Logger logger = Logger.getAnonymousLogger();
        logger.log(Level.SEVERE, "Thread terminated with exception: " + t.getName(), e);
    }
}
