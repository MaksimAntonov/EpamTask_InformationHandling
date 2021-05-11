package by.antonov.informationhandling.interpreter;

import java.util.ArrayDeque;

public class Context {
  private final ArrayDeque<Integer> integers = new ArrayDeque<>();

  public Integer popValue() {
    return integers.pop();
  }

  public void pushValue(Integer value) {
    integers.push(value);
  }

  public void addValue(Integer value) {
    integers.add(value);
  }
}
