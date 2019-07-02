package cn.ksdshpx.java8;

import org.junit.Test;

import java.time.Duration;
import java.time.Instant;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.stream.LongStream;

/**
 * Create with IntelliJ IDEA
 * Create by peng.x
 * Date: 2019/6/30
 * Time: 21:19
 * Description:测试Fork/Join框架以及java8并行流
 */
public class ForkJoinTest {
    @Test
    public void test01() {
        Instant startTime = Instant.now();
        ForkJoinPool pool = new ForkJoinPool();
        ForkJoinTask<Long> task = new ForkJoinCalculate(0L, 50000000000L);
        Long sum = pool.invoke(task);
        System.out.println("sum:" + sum);
        Instant endTime = Instant.now();
        System.out.println("耗时：" + Duration.between(startTime, endTime).toMillis());
    }

    @Test
    public void test02() {
        Instant startTime = Instant.now();
        long sum = 0;
        for (long i = 0; i <= 50000000000L; i++) {
            sum += i;
        }
        System.out.println("sum:" + sum);
        Instant endTime = Instant.now();
        System.out.println("耗时：" + Duration.between(startTime, endTime).toMillis());
    }

    @Test
    public void test03(){
        Instant startTime = Instant.now();
        long sum = LongStream.rangeClosed(0, 50000000000L)
                .parallel()
                .reduce(0, Long::sum);
        System.out.println("sum:" + sum);
        Instant endTime = Instant.now();
        System.out.println("耗时：" + Duration.between(startTime, endTime).toMillis());
    }
}
