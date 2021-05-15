package by.antonov.informationhandling.customparser;

import by.antonov.informationhandling.entity.BaseTextLeaf;
import by.antonov.informationhandling.entity.ComponentType;
import by.antonov.informationhandling.entity.TextComponent;
import by.antonov.informationhandling.interpreter.Interpreter;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ExpressionForSentence extends CustomParser {
  private final static String EXPRESSION_PATTERN = "(?:\\s)(?<expression>\\(?[\\d~|<>&^()]{2,}\\)?)";

  @Override
  public void handle(TextComponent rootComponent) {
    Pattern expressionPattern = Pattern.compile(EXPRESSION_PATTERN);
    Interpreter interpreter = new Interpreter();
    List<TextComponent> components = rootComponent.getComponentsByType(
        rootComponent,
        ComponentType.SENTENCE,
        new ArrayList<>());

    for (TextComponent component : components) {
      String baseText = component.getBaseText(component);
      Matcher matcher = expressionPattern.matcher(baseText);
      while (matcher.find()) {
        String originalText = matcher.group("expression");
        baseText = baseText.replace(originalText, "" + interpreter.calculateExpression(originalText));
      }
      component.add(new BaseTextLeaf(baseText));
    }

    if (this.hasNextParser()) {
      this.nextParser.handle(rootComponent);
    }
  }
}
