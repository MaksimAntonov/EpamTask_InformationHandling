package by.antonov.informationhandling.interpreter;

import java.util.ArrayDeque;
import java.util.Iterator;

public class Context {
  private ArrayDeque<Integer> integers = new ArrayDeque<>();
  private boolean reversed = true;

  public Integer popValue() {
    if (!reversed) { reverse(); }
    return integers.pop();
  }

  public void pushValue(Integer value) {
    integers.add(value);
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

  private void reverse() {
    Iterator<Integer> iterator = integers.descendingIterator();
    ArrayDeque<Integer> reversed = new ArrayDeque<>();
    while (iterator.hasNext()) {
      reversed.add(iterator.next());
    }

    this.reversed = true;
    this.integers = reversed;
  }
}
