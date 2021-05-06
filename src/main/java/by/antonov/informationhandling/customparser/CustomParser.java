package by.antonov.informationhandling.customparser;

import by.antonov.informationhandling.entity.TextComponent;

public abstract class CustomParser {
  protected CustomParser nextParser;

  public void next(CustomParser parser) {
    this.nextParser = parser;
  }

  public boolean hasNext() {
    return (this.nextParser == null);
  }

  public abstract TextComponent handle(String stringForParsing);
}
