package cn.ksdshpx.shengsiyuan.java8;

import java.util.IntSummaryStatistics;
import java.util.stream.Stream;

/**
 * Create with IntelliJ IDEA
 * Create by peng.xing
 * Date: 2019/6/24
 * Time: 13:55
 * Description:Stream案例分析
 */
public class TestStream5 {
    public static void main(String[] args) {
        Stream<Integer> stream = Stream.iterate(1, x -> x + 2);
        //stream.filter(x -> x > 2).map(x -> x * 2).skip(2).limit(2).forEach(System.out::println);
        System.out.println("---------------");
        //System.out.println(stream.filter(x -> x > 2).mapToInt(x -> x * 2).skip(2).limit(2).sum());
        //System.out.println(stream.filter(x -> x > 2).mapToInt(x -> x * 2).skip(2).limit(2).max());
        //stream.filter(x -> x > 2).mapToInt(x -> x * 2).skip(2).limit(2).max().ifPresent(System.out::println);
        IntSummaryStatistics intSummaryStatistics = stream.filter(x -> x > 2).mapToInt(x -> x * 2).skip(2).limit(2).summaryStatistics();
        System.out.println("count:"+intSummaryStatistics.getCount());
        System.out.println("max:"+intSummaryStatistics.getMax());
        System.out.println("average:"+intSummaryStatistics.getAverage());
        System.out.println("min:"+intSummaryStatistics.getMin());
        System.out.println("sum:"+intSummaryStatistics.getSum());
    }
}
