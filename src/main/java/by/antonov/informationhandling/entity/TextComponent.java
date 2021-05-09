package by.antonov.informationhandling.entity;

import java.util.List;

public abstract class TextComponent {

  private final ComponentType componentType;

  protected TextComponent(ComponentType componentType) {
    this.componentType = componentType;
  }

  public ComponentType getComponentType() {
    return this.componentType;
  }

  public void add(TextComponent component) {
  }

  public void remove(TextComponent component) {
  }

  public List<TextComponent> getComponents() {
    return null;
  }

  public String getBaseText(TextComponent textComponent) {
    return null;
  }

  public List<TextComponent> getComponentsByType(TextComponent textComponent, ComponentType componentType,
      List<TextComponent> components) {
    return null;
  }

  public abstract String convertToString();
}
