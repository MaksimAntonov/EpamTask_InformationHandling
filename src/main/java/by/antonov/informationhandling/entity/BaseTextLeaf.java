package by.antonov.informationhandling.entity;

public class BaseTextLeaf extends TextComponent{
  private final String baseText;

  public BaseTextLeaf(String baseText) {
    super(ComponentType.BASE_TEXT);
    this.baseText = baseText;
  }

  @Override
  public String convertToString() {
    return this.baseText;
  }
}
