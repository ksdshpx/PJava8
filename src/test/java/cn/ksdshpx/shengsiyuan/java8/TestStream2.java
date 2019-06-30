package cn.ksdshpx.shengsiyuan.java8;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

/**
 * Create with IntelliJ IDEA
 * Create by peng.xing
 * Date: 2019/6/4
 * Time: 10:45
 * Description:stream深度解析与源码实践
 */
public class TestStream2 {
    public static void main(String[] args) {
        Stream<String> stream = Stream.of("hello", "world", "hello world");
        //String[] strArray = stream.toArray(len -> new String[len]);
        String[] strArray = stream.toArray(String[]::new);
        Arrays.asList(strArray).forEach(System.out::println);
        System.out.println("--------------");
        stream = Stream.of("hello", "world", "hello world");
        //List<String> list = stream.collect(Collectors.toList());
        //List<String> list = stream.collect(() -> new ArrayList<>(), (theList, item) -> theList.add(item), (theList1, theList2) -> theList1.addAll(theList2));
        List<String> list = stream.collect(ArrayList::new, ArrayList::add, ArrayList::addAll);
        list.forEach(System.out::println);
    }
}
