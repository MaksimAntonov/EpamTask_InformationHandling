package by.antonov.informationhandling.customparser;

import by.antonov.informationhandling.entity.BaseTextLeaf;
import by.antonov.informationhandling.entity.ComponentType;
import by.antonov.informationhandling.entity.TextComponent;
import by.antonov.informationhandling.entity.TextComposite;
import by.antonov.informationhandling.entity.TextSymbol;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LexemeToTextElement extends CustomParser {
  private final static String TEXT_ELEMENT_PATTERN = "^(?<before>[()\"'-]?)(?<element>[A-zА-яЁё0-9-']+)(?<after>[),!.?\"']?)$";
  private final static String NUMBER_SYMBOL_PATTERN = "\\d+";
  private final static String TEXT_SYMBOL_PATTERN = "[A-Za-zА-Яа-яЁё]";

  @Override
  public void handle(TextComponent rootComponent) {
    List<TextComponent> components = rootComponent.getComponentsByType(
        rootComponent,
        ComponentType.LEXEME,
        new ArrayList<>());

    Pattern pattern = Pattern.compile(TEXT_ELEMENT_PATTERN);

    for (TextComponent component : components) {
      String baseText = component.getBaseText(component);

      Matcher matcher = pattern.matcher(baseText);
      while (matcher.find()) {
        if (!matcher.group("before").isEmpty()) {
          component.add(new TextSymbol(ComponentType.PUNCTUATION, matcher.group("before").charAt(0)));
        }

        String elementText = matcher.group("element");
        TextComponent textElement;
        if (elementText.matches(NUMBER_SYMBOL_PATTERN)) {
          textElement = new TextComposite(ComponentType.NUMBER);
        } else {
          textElement = new TextComposite(ComponentType.WORD);
        }

        textElement.add( new BaseTextLeaf(elementText));
        component.add(textElement);

        if (!matcher.group("after").isEmpty()) {
          component.add(new TextSymbol(ComponentType.PUNCTUATION, matcher.group("after").charAt(0)));
        }
      }
    }

    if (this.hasNextParser()) {
      this.nextParser.handle(rootComponent);
    }
  }
}