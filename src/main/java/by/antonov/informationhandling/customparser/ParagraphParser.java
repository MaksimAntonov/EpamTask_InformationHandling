package by.antonov.informationhandling.customparser;

import by.antonov.informationhandling.entity.Component;
import by.antonov.informationhandling.entity.ComponentType;
import by.antonov.informationhandling.entity.Composite;

public class ParagraphParser extends CustomParser{
  @Override
  public Component handle(String stringForParsing) {
    Component paragraphComponent = new Composite(ComponentType.PARAGRAPH);

    String[] stringList = stringForParsing.split("(\\.|\\?|!|\\...)\s");

    if (this.nextParser != null) {
      for (String string : stringList) {
        Component sentenceComponent = this.nextParser.handle(string);

        paragraphComponent.add(sentenceComponent);
      }
    }

    return paragraphComponent;
  }
}
