package by.antonov.informationhandling.interpreter.impl;

import by.antonov.informationhandling.interpreter.Context;
import by.antonov.informationhandling.interpreter.Expression;

public class NonTerminalExpression implements Expression {
  private final Integer numberValue;

  public NonTerminalExpression(Integer numberValue) {
    this.numberValue = numberValue;
  }

  @Override
  public void interpret(Context context) {
    context.addValue(this.numberValue);
  }
}
