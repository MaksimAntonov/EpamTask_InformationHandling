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

public class ParagraphToSentenceParser extends CustomParser{
  private final static String SENTENCE_PATTERN = "(?<sentence>\\S.*?[?!.]+)(?:\\s+|$)";

  @Override
  public void parse(TextComponent rootComponent) {
    Pattern pattern = Pattern.compile(SENTENCE_PATTERN);
    Optional<List<TextComponent>> components = rootComponent.getComponentsByType(
        rootComponent,
        ComponentType.PARAGRAPH,
        new ArrayList<>());

    if (components.isPresent()) {
      for (TextComponent component : components.get()) {
        Optional<String> baseTextOptional = component.getBaseText(component);
        if (baseTextOptional.isPresent()) {
          String baseText = baseTextOptional.get();
          Matcher matcher = pattern.matcher(baseText);
          while (matcher.find()) {
            TextComponent paragraphComponent = new TextComposite(ComponentType.SENTENCE);
            TextComponent textElement = new BaseTextLeaf(matcher.group("sentence").trim());

            paragraphComponent.add(textElement);
            component.add(paragraphComponent);
          }
        }
      }
    }

    if (this.hasNextParser()) {
      this.nextParser.parse(rootComponent);
    }
  }
}
