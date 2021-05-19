package by.antonov.informationhandling.customparser;

import by.antonov.informationhandling.entity.BaseTextLeaf;
import by.antonov.informationhandling.entity.ComponentType;
import by.antonov.informationhandling.entity.TextComponent;
import by.antonov.informationhandling.entity.TextComposite;
import by.antonov.informationhandling.entity.TextSymbol;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LexemeToTextElement extends CustomParser {
  private final static String TEXT_ELEMENT_PATTERN = "^(?<before>[()\"'-]?)(?<element>[A-zА-яЁё0-9-']+)(?<after>[),!.?\"']*)$";
  private final static String NUMBER_SYMBOL_PATTERN = "\\d+";

  @Override
  public void parse(TextComponent rootComponent) {
    Optional<List<TextComponent>> components = rootComponent.getComponentsByType(
        ComponentType.LEXEME,
        new ArrayList<>());

    Pattern pattern = Pattern.compile(TEXT_ELEMENT_PATTERN);

    if (components.isPresent()) {
      for (TextComponent component : components.get()) {
        Optional<String> baseTextOptional = component.getBaseText();
        if (baseTextOptional.isPresent()) {
          String baseText = baseTextOptional.get();
          Matcher matcher = pattern.matcher(baseText);
          while (matcher.find()) {
            if (!matcher.group("before").isEmpty()) {
              String[] strSymbols = matcher.group("before").split("");
              for (String strSymbol : strSymbols) {
                component.add(new TextSymbol(ComponentType.PUNCTUATION, strSymbol.charAt(0)));
              }
            }

            String elementText = matcher.group("element");
            TextComponent textElement;
            if (elementText.matches(NUMBER_SYMBOL_PATTERN)) {
              textElement = new TextComposite(ComponentType.NUMBER);
            } else {
              textElement = new TextComposite(ComponentType.WORD);
            }

            textElement.add(new BaseTextLeaf(elementText));
            component.add(textElement);

            if (!matcher.group("after").isEmpty()) {
              String[] strSymbols = matcher.group("after").split("");
              for (String strSymbol : strSymbols) {
                component.add(new TextSymbol(ComponentType.PUNCTUATION, strSymbol.charAt(0)));
              }
            }
          }
        }
      }
    }

    if (this.hasNextParser()) {
      this.nextParser.parse(rootComponent);
    }
  }
}
