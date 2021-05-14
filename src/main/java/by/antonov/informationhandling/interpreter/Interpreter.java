package by.antonov.informationhandling.interpreter;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Interpreter {
  private final static String SUB_EXPRESSION_PATTERN = ".*(?<subexpression>\\([\\d|<>&~^]+\\)).*";
  private final static String PRIORITY_LEVEL_1_PATTERN = "(?<step>~[-\\d]+)";
  private final static String PRIORITY_LEVEL_2_PATTERN = "(?<step>[-\\d]+[<>]{2}[-\\d]+)";
  private final static String PRIORITY_LEVEL_3_PATTERN = "(?<step>[-\\d]+&[-\\d]+)";
  private final static String PRIORITY_LEVEL_4_PATTERN = "(?<step>[-\\d]+\\^[-\\d]+)";
  private final static String PRIORITY_LEVEL_5_PATTERN = "(?<step>[-\\d]+\\|[-\\d]+)";
  private final static String NUMBER_PATTERN = "(?<numbers>[-\\d]+)";
  private final static String EXPRESSION_PATTERN = "(?<expression>\\||<<|>>|&|~|\\^)";
  private final List<AbstractExpression> expressionList = new ArrayList<>();

  public Number calculateExpression(String expression) {
    Pattern pattern = Pattern.compile(SUB_EXPRESSION_PATTERN);
    while (Pattern.matches(SUB_EXPRESSION_PATTERN, expression)) {
      Matcher matcher = pattern.matcher(expression);
      while (matcher.find()) {
        String originalText = matcher.group("subexpression");
        this.parse(originalText);
        expression = expression.replace(originalText, "" + this.calculate());
      }
    }

    this.parse(expression);

    return this.calculate();
  }

  public void parse(String expression) {
    //System.out.println("0: " + expression);

    expression = PriorityStep(PRIORITY_LEVEL_1_PATTERN, expression);

    //System.out.println("1: " + expression);

    expression = PriorityStep(PRIORITY_LEVEL_2_PATTERN, expression);

    //System.out.println("2: " + expression);

    expression = PriorityStep(PRIORITY_LEVEL_3_PATTERN, expression);

    //System.out.println("3: " + expression);

    expression = PriorityStep(PRIORITY_LEVEL_4_PATTERN, expression);

    //System.out.println("4: " + expression);

    expression = PriorityStep(PRIORITY_LEVEL_5_PATTERN, expression);
  }

  private String PriorityStep(String regex, String expression) {
    Pattern pattern = Pattern.compile(regex);
    Matcher matcher = pattern.matcher(expression);

    while (matcher.find()) {
      String originalText = matcher.group("step");
      parseSimpleExpression(originalText);
      expression = expression.replace(originalText, "" + calculate());
    }

    if (pattern.matcher(expression).find()) {
      expression = PriorityStep(regex, expression);
    }

    return expression;
  }

  public void parseSimpleExpression(String expression) {
    expressionList.clear();

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
        case ">>>" -> expressionList.add(new TerminalExpressionZeroFillRightShift());
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
