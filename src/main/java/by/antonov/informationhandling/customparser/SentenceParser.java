package by.antonov.informationhandling.customparser;

import by.antonov.informationhandling.entity.Component;
import by.antonov.informationhandling.entity.ComponentType;
import by.antonov.informationhandling.entity.Composite;

public class SentenceParser extends CustomParser {
  @Override
  public Component handle(String stringForParsing) {
    Component sentenceComponent = new Composite(ComponentType.SENTENCE);

    String[] stringList = stringForParsing.split("\\s");

    if (this.nextParser != null) {
      for (String string : stringList) {
        System.out.println(string);
        Component lexemeComponent = this.nextParser.handle(string);

        sentenceComponent.add(lexemeComponent);
      }
    }

    return sentenceComponent;
  }
}
