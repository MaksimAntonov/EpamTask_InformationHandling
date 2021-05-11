package by.antonov.informationhandling.interpreter;

public class TerminalExpressionXOR extends AbstractExpression {
  @Override
  void interpret(Context context) {
    context.pushValue(context.popValue() ^ context.popValue());
  }
}
