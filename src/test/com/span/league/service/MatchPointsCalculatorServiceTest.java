package test.com.span.league.service;

import main.com.span.league.domain.LeagueLog;
import main.com.span.league.service.MatchPointsCalculatorService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class MatchPointsCalculatorServiceTest {

  private MatchPointsCalculatorService service;

  @Before
  public void setup() {
    service =  MatchPointsCalculatorService.getInstance();
  }

  @Test
  public void should_update_team_points_for_a_game_when_updateLogByMatch_is_called() {
    var log =  new LeagueLog();

    log.addTeamPoints("a", 4);
    log.addTeamPoints("bb", 3);
    log.addTeamPoints("ba", 3);
    log.addTeamPoints("c", 0);

    service.updateLogByMatch("a 4, bb 3", log);
    service.updateLogByMatch("ba 1, FC a 1", log);
    service.updateLogByMatch("c 3, a 3", log);

    var leagueTable =  log.getLog();
    Assert.assertEquals(8, leagueTable.get("a").intValue());
    Assert.assertEquals(3, leagueTable.get("bb").intValue());
    Assert.assertEquals(4, leagueTable.get("ba").intValue());
    Assert.assertEquals(1, leagueTable.get("c").intValue());
  }

  @Test
  public void should_add_and_update_team_points_for_a_game_when_updateLogByMatch_is_called() {
    var log =  new LeagueLog();

    log.addTeamPoints("a", 4);

    service.updateLogByMatch("a 4, bb 3", log);
    service.updateLogByMatch("ba 1, FC a 1", log);
    service.updateLogByMatch("c 3, a 3", log);

    var leagueTable =  log.getLog();
    Assert.assertEquals(8, leagueTable.get("a").intValue());
    Assert.assertEquals(0, leagueTable.get("bb").intValue());
    Assert.assertEquals(1, leagueTable.get("ba").intValue());
    Assert.assertEquals(1, leagueTable.get("c").intValue());
  }
}
