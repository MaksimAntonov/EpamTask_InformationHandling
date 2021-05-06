package by.antonov.informationhandling.entity;

public abstract class TextComponent {
  private final ComponentType componentType;

  protected TextComponent(ComponentType componentType) {
    this.componentType = componentType;
  }

  public ComponentType getComponentType() {
    return this.componentType;
  }

  public void add(TextComponent component) {};

  public abstract String convertToString();
}
