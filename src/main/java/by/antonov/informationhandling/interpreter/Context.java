package by.antonov.informationhandling.interpreter;

import java.util.ArrayDeque;

public class Context {

  private final ArrayDeque<Integer> integers = new ArrayDeque<>();

  public Integer popValue() {
    return integers.pop();
  }

  public void addValue(Integer value) {
    integers.add(value);
  }

  public String toString() {
    StringBuilder sb = new StringBuilder("Context[");
    for (Integer integer : integers) {
      sb.append(integer).append(",");
    }
    sb.append("]");

    return sb.toString();
  }
}
