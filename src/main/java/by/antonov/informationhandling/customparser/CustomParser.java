package by.antonov.informationhandling.customparser;

import by.antonov.informationhandling.entity.TextComponent;

public abstract class CustomParser {

  protected CustomParser nextParser;

  public void nextParser(CustomParser parser) {
    this.nextParser = parser;
  }

  public boolean hasNextParser() {
    return (this.nextParser != null);
  }

  public abstract void parse(TextComponent rootComponent);
}
