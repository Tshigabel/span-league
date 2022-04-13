package main.com.span.league.service;

import main.com.span.league.domain.LeagueLog;
import main.com.span.league.domain.TeamScore;
import main.com.span.league.util.Constants;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.regex.Pattern;

public class MatchPointsCalculatorService {

  private static MatchPointsCalculatorService bean;
  private final Map<Integer, List<Integer>> scoreToPoints = Map.of(
          -1, Arrays.asList(0, 3),
          0, Arrays.asList(1, 1),
          1, Arrays.asList(3, 0));
  private final Pattern pattern = Pattern.compile(Constants.TEAM_SCORE);

  private MatchPointsCalculatorService() {
  }

  public static MatchPointsCalculatorService getInstance() {
    if (Objects.isNull(bean)) {
      bean = new MatchPointsCalculatorService();
    }
    return bean;
  }

  public LeagueLog updateLogByMatch(final String match, final LeagueLog leagueLog) {

    var teamScores = match.split(",");

    var teamA = getTeamScore(pattern, teamScores[0]);
    var teamB = getTeamScore(pattern, teamScores[1]);

    var teamsPoints = scoreToPoints.get(Integer.compare(teamA.getScore(), teamB.getScore()));

    leagueLog.addTeamPoints(teamA.getId(), teamsPoints.get(0));
    leagueLog.addTeamPoints(teamB.getId(), teamsPoints.get(1));

    return leagueLog;
  }

  private TeamScore getTeamScore(Pattern pattern, final String teamScore) {
    var m = pattern.matcher(teamScore);
    m.find();
    return new TeamScore(m.group(1).trim(), Integer.parseInt(m.group(2)));
  }

}
