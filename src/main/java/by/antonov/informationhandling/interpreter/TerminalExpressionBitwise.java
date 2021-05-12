package by.antonov.informationhandling.interpreter;

public class TerminalExpressionBitwise extends AbstractExpression {

  @Override
  public void interpret(Context context) {
    Integer bitwise = ~context.popValue();
    context.pushValue(bitwise);
  }
}
