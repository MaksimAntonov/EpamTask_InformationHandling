package by.antonov.informationhandling.interpreter;

public class TerminalExpressionLeftShift extends AbstractExpression {
  @Override
  public void interpret(Context context) {
    context.pushValue(context.popValue() << context.popValue());
  }
}
