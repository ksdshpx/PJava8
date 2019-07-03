package cn.ksdshpx.java8;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.*;

/**
 * Create with IntelliJ IDEA
 * Create by peng.x
 * Date: 2019/7/2
 * Time: 22:15
 * Description:传统时间格式化的线程安全问题
 */
public class TestSimpleDateFormat {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        //SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        ThreadLocal<DateFormat> threadLocal = new ThreadLocal<DateFormat>() {
            @Override
            protected DateFormat initialValue() {
                return new SimpleDateFormat("yyyyMMdd");
            }
        };
        ExecutorService threadPool = Executors.newFixedThreadPool(100);
        Callable<Date> task = new Callable<Date>() {
            @Override
            public Date call() throws Exception {
                return threadLocal.get().parse("20190702");
            }
        };
        List<Future<Date>> results = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            Future<Date> result = threadPool.submit(task);
            results.add(result);
        }
        for (Future<Date> future : results) {
            System.out.println(future.get());
        }
        threadPool.shutdown();
    }
}
