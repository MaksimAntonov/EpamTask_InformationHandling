package by.antonov.informationhandling.interpreter.impl;

import by.antonov.informationhandling.interpreter.Context;
import by.antonov.informationhandling.interpreter.Expression;

public class TerminalExpressionBitwise implements Expression {

  @Override
  public void interpret(Context context) {
    Integer bitwise = ~context.popValue();
    context.addValue(bitwise);
  }
}
