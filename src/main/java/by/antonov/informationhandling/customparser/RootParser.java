package by.antonov.informationhandling.customparser;

import by.antonov.informationhandling.entity.Component;
import by.antonov.informationhandling.entity.ComponentType;
import by.antonov.informationhandling.entity.Composite;

public class RootParser extends CustomParser{
  public Component handle(String stringForParsing) {
    Component rootComponent = new Composite(ComponentType.ROOT);

    if (this.nextParser != null) {
      String[] stringList = stringForParsing.split("\\n");

      for (String string : stringList) {
        Component paragraphComponent = this.nextParser.handle(string);

        rootComponent.add(paragraphComponent);
      }
    }

    return rootComponent;
  }
}
