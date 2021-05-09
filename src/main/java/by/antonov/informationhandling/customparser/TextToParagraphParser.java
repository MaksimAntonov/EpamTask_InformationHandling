package by.antonov.informationhandling.customparser;

import by.antonov.informationhandling.entity.BaseTextLeaf;
import by.antonov.informationhandling.entity.TextComponent;
import by.antonov.informationhandling.entity.ComponentType;
import by.antonov.informationhandling.entity.TextComposite;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TextToParagraphParser extends CustomParser{
  private final static String PARAGRAPH_PATTERN = "(?: *|\\t?)(?<paragraph>.+?)(?:\\n {2,4}|\\n\\t|$)";

  @Override
  public void handle(TextComponent component) {
    Pattern pattern = Pattern.compile(PARAGRAPH_PATTERN);
    String baseText = component.getBaseText(component);

    Matcher matcher = pattern.matcher(baseText);
    while (matcher.find()) {
      TextComponent paragraphComponent = new TextComposite(ComponentType.PARAGRAPH);

      TextComponent textElement = new BaseTextLeaf(matcher.group("paragraph").trim());
      paragraphComponent.add(textElement);
      component.add(paragraphComponent);
    }

    if (this.hasNextParser()) {
      this.nextParser.handle(component);
    }
  }
}
