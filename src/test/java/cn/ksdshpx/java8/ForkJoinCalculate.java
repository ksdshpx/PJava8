package cn.ksdshpx.java8;

import java.util.concurrent.RecursiveTask;

/**
 * Create with IntelliJ IDEA
 * Create by peng.x
 * Date: 2019/6/30
 * Time: 21:13
 * Description:Fork/Join框架
 */
public class ForkJoinCalculate extends RecursiveTask<Long> {
    private Long start;
    private Long end;
    private static final long THRESHOLD = 10000L;

    public ForkJoinCalculate(Long start, Long end) {
        this.start = start;
        this.end = end;
    }

    @Override
    protected Long compute() {
        long sum = 0;
        if ((end - start) <= THRESHOLD) {
            for (long i = start; i <= end; i++) {
                sum += i;
            }
        } else {
            long middle = (start + end) / 2;
            ForkJoinCalculate left = new ForkJoinCalculate(start, middle);
            left.fork();//拆分，将子任务压入线程队列
            ForkJoinCalculate right = new ForkJoinCalculate(middle + 1, end);
            right.fork();
            sum = left.join() + right.join();
        }
        return sum;
    }
}
