package main.com.span.league;

import main.com.span.league.domain.LeagueLog;
import main.com.span.league.service.LeaguePrinterService;
import main.com.span.league.service.MatchPointsCalculatorService;
import main.com.span.league.util.Constants;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.regex.Pattern;

public class Main {

  public static void main(String[] args) throws IOException {

    List<String> lines = Files.readAllLines(Paths.get(args[0]), Charset.defaultCharset());

    Pattern pattern = Pattern.compile(String.format("%s,%s", Constants.TEAM_SCORE, Constants.TEAM_SCORE));

    var matchPointsCalculator = MatchPointsCalculatorService.getInstance();
    var leagueLog = new LeagueLog();

    lines.stream()
            .filter(line -> pattern.matcher(line).find())
            .forEach(match -> matchPointsCalculator.updateLogByMatch(match, leagueLog));

    LeaguePrinterService.getInstance().print(leagueLog, System.out::println);
  }
}
