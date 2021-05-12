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
    integers.push(value);
  }

  public void addValue(Integer value) {
    integers.add(value);
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
