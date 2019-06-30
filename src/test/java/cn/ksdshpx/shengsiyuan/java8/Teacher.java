package cn.ksdshpx.shengsiyuan.java8;

/**
 * Create with IntelliJ IDEA
 * Create by peng.xing
 * Date: 2019/6/26
 * Time: 9:53
 * Description:教师实体类
 */
public class Teacher {
    private String name;
    private Integer age;
    private Integer score;

    public Teacher() {
    }

    public Teacher(String name, Integer age, Integer score) {
        this.name = name;
        this.age = age;
        this.score = score;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    @Override
    public String toString() {
        return "Teacher{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", score=" + score +
                '}';
    }
}
