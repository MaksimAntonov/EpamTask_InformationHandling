package by.antonov.informationhandling.interpreter;

public class TerminalExpressionZeroFillRightShift  extends AbstractExpression {
  @Override
  void interpret(Context context) {
    context.pushValue(context.popValue() >>> context.popValue());
  }
}
