package domain;

import java.util.Date;

public class User {

    private String name;
    private Integer score;
    private String created_at;

    public User(String name, Integer score, String created_at) {
        this.name = name;
        this.score = score;
        this.created_at = created_at;
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

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at() {
        this.created_at = String.valueOf(new Date());
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", score=" + score +
                ", created_at=" + created_at +
                '}';
    }
}
