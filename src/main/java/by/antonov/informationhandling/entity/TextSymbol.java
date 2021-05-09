package by.antonov.informationhandling.entity;

public class TextSymbol extends TextComponent {
  private final char charValue;

  public TextSymbol(ComponentType componentType, char symbol) {
    super(componentType);
    this.charValue = symbol;
  }

  public char getCharValue() {
    return this.charValue;
  }

  public String convertToString() {
    return Character.toString(this.charValue);
  }

  public String toString() {
    StringBuilder sb = new StringBuilder("{");
    sb.append(getComponentType().name());
    sb.append(" ").append(this.charValue);
    sb.append("}");

    return sb.toString();
  }
}
