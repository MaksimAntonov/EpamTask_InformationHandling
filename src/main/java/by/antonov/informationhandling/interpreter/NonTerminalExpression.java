package by.antonov.informationhandling.interpreter;

public class NonTerminalExpression extends AbstractExpression{
  private final Integer numberValue;

  public NonTerminalExpression(Integer numberValue) {
    this.numberValue = numberValue;
  }

  @Override
  public void interpret(Context context) {
    context.addValue(this.numberValue);
  }
}
