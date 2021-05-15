package by.antonov.informationhandling.interpreter.impl;

import by.antonov.informationhandling.interpreter.Context;
import by.antonov.informationhandling.interpreter.Expression;

public class TerminalExpressionXOR implements Expression {
  @Override
  public void interpret(Context context) {
    context.addValue(context.popValue() ^ context.popValue());
  }
}
