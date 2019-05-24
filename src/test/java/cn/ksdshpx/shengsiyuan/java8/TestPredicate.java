package cn.ksdshpx.shengsiyuan.java8;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
/**

 * Create with IntelliJ IDEA
 * Create by peng.xing
 * Date: 2019/5/24
 * Time: 8:25
 * Description:Predicate接口深入剖析
 */
public class TestPredicate {
    public static void main(String[] args) {
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        TestPredicate tp = new TestPredicate();
        //打印集合中的偶数
        tp.conditionFilter(list, item -> item % 2 == 0);
        System.out.println("--------------");
        //打印集合中的奇数
        tp.conditionFilter(list, item -> item % 2 != 0);
        System.out.println("------------");
        //打印集合中大于5的数字
        tp.conditionFilter(list, item -> item > 5);
        System.out.println("------------");
        tp.conditionFilter2(list, item -> item > 5, item -> item % 2 == 0);
        System.out.println("------------");
        tp.conditionFilter3(list,item -> item > 5);
    }

    public void conditionFilter(List<Integer> list, Predicate<Integer> predicate) {
        for (Integer i : list) {
            if (predicate.test(i)) {
                System.out.print(i + " ");
            }
        }
        System.out.println();
    }

    public void conditionFilter2(List<Integer> list, Predicate<Integer> predicate1, Predicate<Integer> predicate2) {
        for (Integer i : list) {
            if (predicate1.and(predicate2).test(i)) {
                System.out.print(i + " ");
            }
        }
        System.out.println();
    }

    public void conditionFilter3(List<Integer> list, Predicate<Integer> predicate) {
        for (Integer i : list) {
            if (predicate.negate().test(i)) {
                System.out.print(i + " ");
            }
        }
        System.out.println();
    }
}
