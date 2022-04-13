package test.com.span.league.service;

import main.com.span.league.domain.LeagueLog;
import main.com.span.league.service.LeaguePrinterService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

public class LeaguePrinterServiceTest {

  private LeaguePrinterService service;

  @Before
  public void setup() {
    service =  LeaguePrinterService.getInstance();
  }

  @Test
  public void should_sort_and_print_league_log_in_correct_order_when_LeaguePrinterService_print_is_called() {
    var log =  new LeagueLog();

    log.addTeamPoints("a", 4);
    log.addTeamPoints("bb", 3);
    log.addTeamPoints("ba", 3);
    log.addTeamPoints("C", 0);

    var output =  new ArrayList<String>();

    service.print(log, output::add);

    Assert.assertEquals("1. a, 4", output.get(0));
    Assert.assertEquals("2. ba, 3", output.get(1));
    Assert.assertEquals("3. bb, 3", output.get(2));
  }
}
