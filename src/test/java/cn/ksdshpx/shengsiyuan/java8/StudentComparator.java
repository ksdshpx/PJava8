package cn.ksdshpx.shengsiyuan.java8;

/**
 * Create with IntelliJ IDEA
 * Create by peng.xing
 * Date: 2019/5/28
 * Time: 16:45
 * Description:学生类比较方法
 */
public class StudentComparator {
    public int compareStuByScore(Student student1, Student student2) {
        return student1.getScore() - student2.getScore();
    }

    public int compareStuByName(Student student1, Student student2) {
        return student1.getName().compareToIgnoreCase(student2.getName());
    }
}
