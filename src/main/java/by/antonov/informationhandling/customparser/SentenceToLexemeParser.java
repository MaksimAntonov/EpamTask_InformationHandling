package by.antonov.informationhandling.customparser;

import by.antonov.informationhandling.entity.BaseTextLeaf;
import by.antonov.informationhandling.entity.ComponentType;
import by.antonov.informationhandling.entity.TextComponent;
import by.antonov.informationhandling.entity.TextComposite;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SentenceToLexemeParser extends CustomParser{
  private final static String LEXEME_PATTERN = "(?<lexeme>\\S+)";

  @Override
  public void parse(TextComponent rootComponent) {
    Pattern lexemePattern = Pattern.compile(LEXEME_PATTERN);
    Optional<List<TextComponent>> components = rootComponent.getComponentsByType(ComponentType.SENTENCE);

    if (components.isPresent()) {
      for (TextComponent component : components.get()) {
        Optional<String> baseTextOptional = component.getBaseText();
        if (baseTextOptional.isPresent()) {
          String baseText = baseTextOptional.get();
          Matcher matcher = lexemePattern.matcher(baseText);
          while (matcher.find()) {
            TextComponent lexemeComponent = new TextComposite(ComponentType.LEXEME);
            TextComponent textElement = new BaseTextLeaf(matcher.group("lexeme").trim());

            lexemeComponent.add(textElement);
            component.add(lexemeComponent);
          }
        }
      }
    }

    if (this.hasNextParser()) {
      this.nextParser.parse(rootComponent);
    }
  }
}
