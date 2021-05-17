package by.antonov.informationhandling.entity;

public class TextSymbol extends TextComponent {
  private final char charValue;

  public TextSymbol(ComponentType componentType, char symbol) {
    super(componentType);
    this.charValue = symbol;
  }

  public String toString() {
    return Character.toString(this.charValue);
  }
}
