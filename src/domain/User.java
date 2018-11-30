package domain;

import java.util.Date;

public class User {

    private String name;
    private Integer score;
    private Date created_at;

    public User(String name, Integer score) {
        this.name = name;
        this.score = score;
        this.created_at = new java.util.Date();
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

    public Date getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Date created_at) {
        this.created_at = created_at;
    }
}
