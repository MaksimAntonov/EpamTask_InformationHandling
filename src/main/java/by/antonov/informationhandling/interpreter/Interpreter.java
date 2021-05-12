package by.antonov.informationhandling.interpreter;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Interpreter {
  private final static String SUB_EXPRESSION_PATTERN = ".*(?<subexpression>\\([\\d|<>&~^]+\\)).*";
  private final static String PRIORITY_LEVEL_1_PATTERN = "(?<first>~\\d+)";
  private final static String PRIORITY_LEVEL_2_PATTERN = "(?<second>\\d+[<>]{2}\\d+)";
  private final static String PRIORITY_LEVEL_3_PATTERN = "(?<third>\\d+&\\d+)";
  private final static String PRIORITY_LEVEL_4_PATTERN = "(?<fourth>\\d+\\^\\d+)";
  private final static String PRIORITY_LEVEL_5_PATTERN = "(?<fifth>\\d+\\|\\d+)";
  private final static String NUMBER_PATTERN = "(?<numbers>\\d++)";
  private final static String EXPRESSION_PATTERN = "(?<expression>\\||<<|>>|&|~|\\^)";
  private final List<AbstractExpression> expressionList = new ArrayList<>();

  public Number calculateExpression(String expression) {
    expressionList.clear();

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
    String originalText;
    Pattern patternStep;
    Matcher matcherStep;

    patternStep = Pattern.compile(PRIORITY_LEVEL_1_PATTERN);
    matcherStep = patternStep.matcher(expression);
    while (matcherStep.find()) {
      originalText = matcherStep.group("first");
      parseSimpleExpression(originalText);
      expression = expression.replace(originalText, "" + calculate());
    }

    patternStep = Pattern.compile(PRIORITY_LEVEL_2_PATTERN);
    matcherStep = patternStep.matcher(expression);
    while (matcherStep.find()) {
      originalText = matcherStep.group("second");
      parseSimpleExpression(originalText);
      expression = expression.replace(originalText, "" + calculate());
    }

    patternStep = Pattern.compile(PRIORITY_LEVEL_3_PATTERN);
    matcherStep = patternStep.matcher(expression);
    while (matcherStep.find()) {
      originalText = matcherStep.group("third");
      parseSimpleExpression(originalText);
      expression = expression.replace(originalText, "" + calculate());
    }

    patternStep = Pattern.compile(PRIORITY_LEVEL_4_PATTERN);
    matcherStep = patternStep.matcher(expression);
    while (matcherStep.find()) {
      originalText = matcherStep.group("fourth");
      parseSimpleExpression(originalText);
      expression = expression.replace(originalText, "" + calculate());
    }

    patternStep = Pattern.compile(PRIORITY_LEVEL_5_PATTERN);
    matcherStep = patternStep.matcher(expression);
    while (matcherStep.find()) {
      originalText = matcherStep.group("fifth");
      parseSimpleExpression(originalText);
      expression = expression.replace(originalText, "" + calculate());
    }
  }

  public void parseSimpleExpression(String expression) {
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
