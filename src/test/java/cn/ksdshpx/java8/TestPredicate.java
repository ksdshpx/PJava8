package cn.ksdshpx.java8;

import cn.ksdshpx.shengsiyuan.java8.Person;

import java.util.Arrays;
import java.util.List;
import java.util.function.BiFunction;
import java.util.stream.Collectors;

public class TestPredicate {
    public static void main(String[] args) {
        Person person1 = new Person("zhangSan", 20);
        Person person2 = new Person("liSi", 30);
        Person person3 = new Person("wangWu", 40);
        Person person4 = new Person("zhaoLiu", 50);
        List<Person> persons = Arrays.asList(person1, person2, person3, person4);
        TestPredicate testPredicate = new TestPredicate();
        //testPredicate.getPersonByName("zhangSan", persons).forEach(System.out::println);
        System.out.println("---------------------");
        testPredicate.getPersonByAge(35, persons).forEach(System.out::println);
        System.out.println("---------------------");
        System.out.println(testPredicate.getPersonByCondition(35,persons,(ageOfPerson,personList) -> personList.stream().filter((person -> person.getAge() < ageOfPerson)).collect(Collectors.toList())));
    }

    public List<Person> getPersonByName(String name, List<Person> personList) {
        return personList.stream().filter((person) -> person.getName().equals(name)).collect(Collectors.toList());
    }

    public List<Person> getPersonByAge(Integer age, List<Person> personList) {
        BiFunction<Integer, List<Person>, List<Person>> biFunction = (ageOfPerson, persons) -> persons.stream().filter((person) -> person.getAge() > ageOfPerson).collect(Collectors.toList());
        return biFunction.apply(age, personList);
    }

    public List<Person> getPersonByCondition(Integer age, List<Person> personList,BiFunction<Integer,List<Person>,List<Person>> biFunction){
        return biFunction.apply(age,personList);
    }
}
