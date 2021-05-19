package by.antonov.informationhandling.customparser;

import by.antonov.informationhandling.entity.BaseTextLeaf;
import by.antonov.informationhandling.entity.ComponentType;
import by.antonov.informationhandling.entity.TextComponent;
import by.antonov.informationhandling.interpreter.Interpreter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ExpressionForSentence extends CustomParser {
  private final static String EXPRESSION_PATTERN = "(?:\\s)(?<expression>\\(?[\\d~|<>&^()]{2,}\\)?)";

  @Override
  public void parse(TextComponent rootComponent) {
    Pattern expressionPattern = Pattern.compile(EXPRESSION_PATTERN);
    Interpreter interpreter = new Interpreter();
    Optional<List<TextComponent>> components = rootComponent.getComponentsByType(ComponentType.SENTENCE);

    if (components.isPresent()) {
      for (TextComponent component : components.get()) {
        Optional<String> baseTextOptional = component.getBaseText();
        if (baseTextOptional.isPresent()) {
          String baseText = baseTextOptional.get();
          Matcher matcher = expressionPattern.matcher(baseText);
          while (matcher.find()) {
            String originalText = matcher.group("expression");
            baseText = baseText.replace(originalText, "" + interpreter.calculateExpression(originalText));
          }
          component.add(new BaseTextLeaf(baseText));
        }
      }
    }

    if (this.hasNextParser()) {
      this.nextParser.parse(rootComponent);
    }
  }
}
