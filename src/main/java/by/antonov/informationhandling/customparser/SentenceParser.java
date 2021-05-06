package by.antonov.informationhandling.customparser;

import by.antonov.informationhandling.entity.TextComponent;
import by.antonov.informationhandling.entity.ComponentType;
import by.antonov.informationhandling.entity.TextComposite;

public class SentenceParser extends CustomParser {
  @Override
  public TextComponent handle(String stringForParsing) {
    TextComponent sentenceComponent = new TextComposite(ComponentType.SENTENCE);

    String[] stringList = stringForParsing.split("\\s");

    if (this.nextParser != null) {
      for (String string : stringList) {
        System.out.println(string);
        TextComponent lexemeComponent = this.nextParser.handle(string);

        sentenceComponent.add(lexemeComponent);
      }
    }

    return sentenceComponent;
  }
}
