package main.com.span.league.service;

import main.com.span.league.domain.LeagueLog;

import java.util.Comparator;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Consumer;

public class LeaguePrinterService {

  private static LeaguePrinterService bean;

  private LeaguePrinterService() {}

  public static LeaguePrinterService getInstance() {

    if(Objects.isNull(bean)) {
      bean =  new LeaguePrinterService();
    }

    return bean;
  }

  public void print(final LeagueLog log, Consumer<String> printer) {

    AtomicReference<Integer> index = new AtomicReference<>(1);
    log.getLog().entrySet()
            .stream()
            .sorted(Comparator.comparing(Map.Entry<String,Integer>::getValue)
                    .reversed().thenComparing(Map.Entry::getKey))
            .map(x ->String.format("%s. %s, %s", index.getAndSet(index.get() + 1), x.getKey(), x.getValue()))
            .forEach(printer);
  }
}
