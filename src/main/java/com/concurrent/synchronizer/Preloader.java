package com.concurrent.synchronizer;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * @author zhanglifeng
 * @since 2020-06-15 17:37
 */
public class Preloader {

    private final FutureTask<ProductInfo> future = new FutureTask<>(new Callable<ProductInfo>() {
        @Override
        public ProductInfo call() throws DataLoadException {
            return loadProductInfo();
        }
    });



    public ProductInfo get() throws InterruptedException, DataLoadException {
        try {
            return future.get();
        } catch (ExecutionException e) {
            Throwable cause = e.getCause();
            if (cause instanceof DataLoadException)
                throw (DataLoadException) cause;
//            else
//                throw LaunderThrowable.launderThrowable(cause);
        }
        return null;
    }


    interface ProductInfo{

    }

    ProductInfo loadProductInfo() throws DataLoadException {
        return null;
    }

    class DataLoadException extends Exception { }
}
