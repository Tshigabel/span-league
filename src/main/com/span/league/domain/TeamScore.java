package main.com.span.league.domain;

public class TeamScore {

  private String id;
  private Integer score;

  public TeamScore(String id, Integer score) {
    this.id = id;
    this.score = score;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public Integer getScore() {
    return score;
  }

  public void setScore(Integer score) {
    this.score = score;
  }
}
