package by.antonov.informationhandling.interpreter;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Interpreter {

  private static final Logger logger = LogManager.getLogger();
  private final static String SUB_EXPRESSION_PATTERN = ".*(?<subexpression>\\([\\d|<>&~^]+\\)).*";
  private final static String PRIORITY_LEVEL_1_PATTERN = "(?<step>~[-\\d]+)";
  private final static String PRIORITY_LEVEL_2_PATTERN = "(?<step>[-\\d]+[<>]{2}[-\\d]+)";
  private final static String PRIORITY_LEVEL_3_PATTERN = "(?<step>[-\\d]+&[-\\d]+)";
  private final static String PRIORITY_LEVEL_4_PATTERN = "(?<step>[-\\d]+\\^[-\\d]+)";
  private final static String PRIORITY_LEVEL_5_PATTERN = "(?<step>[-\\d]+\\|[-\\d]+)";
  private final static String NUMBER_PATTERN = "(?<numbers>[-\\d]+)";
  private final static String EXPRESSION_PATTERN = "(?<expression>\\||<<|>>|&|~|\\^)";
  private final List<Expression> expressionList = new ArrayList<>();

  public Number calculateExpression(String expression) {
    logger.info(String.format("Calculate expression: %s", expression));
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
    expression = PriorityStep(PRIORITY_LEVEL_1_PATTERN, expression);
    expression = PriorityStep(PRIORITY_LEVEL_2_PATTERN, expression);
    expression = PriorityStep(PRIORITY_LEVEL_3_PATTERN, expression);
    expression = PriorityStep(PRIORITY_LEVEL_4_PATTERN, expression);
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
      expressionList.add(context -> context.addValue(Integer.parseInt(element)));
    }

    Pattern expressionPattern = Pattern.compile(EXPRESSION_PATTERN);
    matcher = expressionPattern.matcher(expression);
    while (matcher.find()) {
      String element = matcher.group("expression");
      switch (element) {
        case "&" -> expressionList.add(context -> context.addValue(context.popValue() & context.popValue()));
        case "|" -> expressionList.add(context -> context.addValue(context.popValue() | context.popValue()));
        case "^" -> expressionList.add(context -> context.addValue(context.popValue() ^ context.popValue()));
        case "<<" -> expressionList.add(context -> context.addValue(context.popValue() << context.popValue()));
        case ">>" -> expressionList.add(context -> context.addValue(context.popValue() >> context.popValue()));
        case ">>>" -> expressionList.add(context -> context.addValue(context.popValue() >>> context.popValue()));
        case "~" -> expressionList.add(context -> context.addValue(~context.popValue()));
      }
    }
  }

  public Number calculate() {
    Context context = new Context();
    for (Expression expression : expressionList) {
      expression.interpret(context);
    }
    return context.popValue();
  }
}
