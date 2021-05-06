package by.antonov.informationhandling.customparser;

import by.antonov.informationhandling.entity.TextComponent;
import by.antonov.informationhandling.entity.ComponentType;
import by.antonov.informationhandling.entity.TextComposite;
import by.antonov.informationhandling.entity.TextElement;

public class LexemeParser extends CustomParser{
  @Override
  public TextComponent handle(String stringForParsing) {
    TextComponent lexemeComponent = new TextComposite(ComponentType.LEXEME);

    String[] stringList = stringForParsing.trim().split("");
    for (String string : stringList) {
      TextComponent leaf = new TextElement(ComponentType.CHARACTER, string);

      lexemeComponent.add(leaf);
    }

    return lexemeComponent;
  }
}
