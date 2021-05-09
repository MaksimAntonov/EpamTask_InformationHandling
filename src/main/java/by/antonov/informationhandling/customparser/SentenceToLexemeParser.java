package by.antonov.informationhandling.customparser;

import by.antonov.informationhandling.entity.BaseTextLeaf;
import by.antonov.informationhandling.entity.ComponentType;
import by.antonov.informationhandling.entity.TextComponent;
import by.antonov.informationhandling.entity.TextComposite;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SentenceToLexemeParser extends CustomParser{
  private final static String LEXEME_PATTERN = "(?<lexeme>\\S+)";

  @Override
  public void handle(TextComponent rootComponent) {
    Pattern lexemePattern = Pattern.compile(LEXEME_PATTERN);
    List<TextComponent> components = rootComponent.getComponentsByType(
        rootComponent,
        ComponentType.SENTENCE,
        new ArrayList<>());

    for (TextComponent component : components) {
      String baseText = component.getBaseText(component);
      Matcher matcher = lexemePattern.matcher(baseText);
      while (matcher.find()) {
        TextComponent lexemeComponent = new TextComposite(ComponentType.LEXEME);
        TextComponent textElement = new BaseTextLeaf(matcher.group("lexeme").trim());

        lexemeComponent.add(textElement);
        component.add(lexemeComponent);
      }
    }

    if (this.hasNextParser()) {
      this.nextParser.handle(rootComponent);
    }
  }
}
