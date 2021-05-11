package by.antonov.informationhandling.interpreter;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Interpreter {
  private final static String NUMBER_PATTERN = "(?<numbers>\\d++)";
  private final static String EXPRESSION_PATTERN = "(?<expression>\\||<<|>>|&|~|\\^)";
  private final List<AbstractExpression> expressionList = new ArrayList<>();

  public void parse(String expression) {
    Pattern numberPattern = Pattern.compile(NUMBER_PATTERN);
    Matcher matcher = numberPattern.matcher(expression);
    while (matcher.find()) {
      String element = matcher.group("numbers");
      expressionList.add(new NonTerminalExpression(Integer.parseInt(element)));
    }

    Pattern expressionPattern = Pattern.compile(EXPRESSION_PATTERN);
    matcher = expressionPattern.matcher(expression);
    while (matcher.find()) {
      String element = matcher.group("expression");
      switch (element) {
        case "&" -> expressionList.add(new TerminalExpressionAnd());
        case "|" -> expressionList.add(new TerminalExpressionOr());
        case "^" -> expressionList.add(new TerminalExpressionXOR());
        case "<<" -> expressionList.add(new TerminalExpressionLeftShift());
        case ">>" -> expressionList.add(new TerminalExpressionRightShift());
        case "~" -> expressionList.add(new TerminalExpressionBitwise());
      }
    }
  }

  public Number calculate() {
    Context context = new Context();
    for (AbstractExpression expression : expressionList) {
      expression.interpret(context);
    }
    return context.popValue();
  }
}
