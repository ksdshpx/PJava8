package cn.ksdshpx.shengsiyuan.java8;

/**
 * Create with IntelliJ IDEA
 * Create by peng.xing
 * Date: 2019/7/2
 * Time: 9:36
 * Description:Human类
 */
public class Human {
    private String name;
    private Integer score;

    public Human() {
    }

    public Human(String name, Integer score) {
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

    @Override
    public String toString() {
        return "Human{" +
                "name='" + name + '\'' +
                ", score=" + score +
                '}';
    }
}
