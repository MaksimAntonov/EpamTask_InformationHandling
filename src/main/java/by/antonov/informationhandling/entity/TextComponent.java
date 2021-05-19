package by.antonov.informationhandling.entity;

import java.util.List;
import java.util.Optional;

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

  public Optional<List<TextComponent>> getComponents() {
    return Optional.empty();
  }

  public Optional<String> getBaseText() {
    return Optional.empty();
  }

  public Optional<List<TextComponent>> getComponentsByType(ComponentType componentType) {
    return Optional.empty();
  }
}
