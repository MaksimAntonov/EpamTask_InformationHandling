package by.antonov.informationhandling.customparser;

import by.antonov.informationhandling.entity.TextComponent;
import by.antonov.informationhandling.entity.ComponentType;
import by.antonov.informationhandling.entity.TextComposite;

public class ParagraphParser extends CustomParser{
  @Override
  public TextComponent handle(String stringForParsing) {
    TextComponent paragraphComponent = new TextComposite(ComponentType.PARAGRAPH);

    String[] stringList = stringForParsing.split("(\\.|\\?|!|\\...)\s");

    if (this.nextParser != null) {
      for (String string : stringList) {
        TextComponent sentenceComponent = this.nextParser.handle(string);

        paragraphComponent.add(sentenceComponent);
      }
    }

    return paragraphComponent;
  }
}
