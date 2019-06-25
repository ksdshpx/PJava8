package cn.ksdshpx.shengsiyuan.java8;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * Create with IntelliJ IDEA
 * Create by peng.xing
 * Date: 2019/6/25
 * Time: 9:59
 * Description:串行流和并行流比较
 */
public class TestStream6 {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>(5000000);
        for (int i = 0; i < 5000000; i++) {
            list.add(UUID.randomUUID().toString());
        }
        System.out.println("开始排序");
        long startTime = System.nanoTime();
        //list.stream().sorted().count();
        list.parallelStream().sorted().count();
        long endTime = System.nanoTime();
        System.out.println("排序耗时：" + (TimeUnit.NANOSECONDS.toMillis(endTime - startTime)));
    }
}
