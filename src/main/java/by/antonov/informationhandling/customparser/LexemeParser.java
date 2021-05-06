package by.antonov.informationhandling.customparser;

import by.antonov.informationhandling.entity.Component;
import by.antonov.informationhandling.entity.ComponentType;
import by.antonov.informationhandling.entity.Composite;
import by.antonov.informationhandling.entity.Leaf;

public class LexemeParser extends CustomParser{
  @Override
  public Component handle(String stringForParsing) {
    Component lexemeComponent = new Composite(ComponentType.LEXEME);

    String[] stringList = stringForParsing.trim().split("");
    for (String string : stringList) {
      Component leaf = new Leaf(ComponentType.CHARACTER, string);

      lexemeComponent.add(leaf);
    }

    return lexemeComponent;
  }
}
