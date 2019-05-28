package cn.ksdshpx.shengsiyuan.java8;

/**
 * Create with IntelliJ IDEA
 * Create by peng.xing
 * Date: 2019/5/28
 * Time: 16:12
 * Description:学生类
 */
public class Student {
    private String name;
    private Integer score;

    public Student() {
    }

    public Student(String name, Integer score) {
        this.name = name;
        this.score = score;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public static int compareStuByScore(Student student1, Student student2) {
        return student1.getScore() - student2.getScore();
    }

    public static int compareStuByName(Student student1, Student student2) {
        return student1.getName().compareToIgnoreCase(student2.getName());
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", score=" + score +
                '}';
    }
}
