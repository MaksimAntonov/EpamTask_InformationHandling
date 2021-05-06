package by.antonov.informationhandling.entity;

public class Leaf extends Component{
  private final String charValue;

  public Leaf(ComponentType componentType, String charValue) {
    super(componentType);
    this.charValue = charValue;
  }

  public String getCharValue() {
    return charValue;
  }

  public String convertToString() {
    return charValue;
  }

  public String toString() {
    StringBuilder sb = new StringBuilder("{");
    sb.append(getComponentType().name());
    sb.append("char=").append(charValue);
    sb.append("}");

    return sb.toString();
  }
}
