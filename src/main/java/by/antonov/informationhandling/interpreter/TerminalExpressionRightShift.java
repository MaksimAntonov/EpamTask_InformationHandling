package by.antonov.informationhandling.interpreter;

public class TerminalExpressionRightShift extends AbstractExpression {
  @Override
  public void interpret(Context context) {
    context.pushValue(context.popValue() >> context.popValue());
  }
}
