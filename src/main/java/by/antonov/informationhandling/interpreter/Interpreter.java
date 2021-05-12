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
    String originalText;
    Pattern patternStep;
    Matcher matcherStep;
    System.out.println(">" + expression);

    patternStep = Pattern.compile("(?<first>~\\d+)");
    matcherStep = patternStep.matcher(expression);
    while (matcherStep.find()) {
      originalText = matcherStep.group("first");
      parseSimpleExpression(originalText);
      expression = expression.replace(originalText, "" + calculate());
    }

    System.out.println(">>" + expression);

    patternStep = Pattern.compile("(?<second>\\d+[<>]{2}\\d+)");
    matcherStep = patternStep.matcher(expression);
    while (matcherStep.find()) {
      originalText = matcherStep.group("second");
      parseSimpleExpression(originalText);
      expression = expression.replace(originalText, "" + calculate());
    }

    System.out.println(">>" + expression);

    patternStep = Pattern.compile("(?<third>\\d+&\\d+)");
    matcherStep = patternStep.matcher(expression);
    while (matcherStep.find()) {
      originalText = matcherStep.group("third");
      parseSimpleExpression(originalText);
      expression = expression.replace(originalText, "" + calculate());
    }

    System.out.println(">>" + expression);

    patternStep = Pattern.compile("(?<fourth>\\d+\\|\\d+)");
    matcherStep = patternStep.matcher(expression);
    while (matcherStep.find()) {
      originalText = matcherStep.group("fourth");
      parseSimpleExpression(originalText);
      expression = expression.replace(originalText, "" + calculate());
    }

    System.out.println(">>" + expression);
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
