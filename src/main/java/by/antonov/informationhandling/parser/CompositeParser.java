package by.antonov.informationhandling.parser;

import by.antonov.informationhandling.entity.TextComponent;

public abstract class CompositeParser {

  protected CompositeParser nextParser;

  public void nextParser(CompositeParser parser) {
    this.nextParser = parser;
  }

  public boolean hasNextParser() {
    return (this.nextParser != null);
  }

  public abstract void parse(TextComponent rootComponent);
}
