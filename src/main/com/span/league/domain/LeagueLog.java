package main.com.span.league.domain;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class LeagueLog {

  private Map<String, Integer> log = new HashMap<>();

  public LeagueLog() {}

  public void addTeamPoints(String teamId, Integer points) {
    var currentPoints = Objects.requireNonNullElse(log.get(teamId), 0);
    log.put(teamId, currentPoints + points);
  }

  public Map<String, Integer> getLog() {
    return log;
  }
}
