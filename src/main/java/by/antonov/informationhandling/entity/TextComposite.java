package by.antonov.informationhandling.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TextComposite extends TextComponent {
  private final List<TextComponent> components;

  public TextComposite(ComponentType componentType) {
    super(componentType);
    this.components = new ArrayList<>();
  }

  public void add(TextComponent component) {
    if (component != null) {
      this.components.add(component);
    }
  }

  public void remove(TextComponent component) {
    this.components.remove(component);
  }

  public Optional<List<TextComponent>> getComponents() {
    return Optional.of(new ArrayList<>(this.components));
  }

  public Optional<String> getBaseText(TextComponent textComponent) {
    Optional<List<TextComponent>> components = textComponent.getComponents();
    if (components.isPresent()) {
      for (TextComponent component : components.get()) {
        if (component.getComponentType().equals(ComponentType.BASE_TEXT)) {
          textComponent.remove(component);
          return Optional.of(component.toString());
        }
      }
    }

    return Optional.empty();
  }

  public Optional<List<TextComponent>> getComponentsByType(
      TextComponent textComponent, ComponentType componentType, List<TextComponent> components
  ) {
    Optional<List<TextComponent>> componentChild = textComponent.getComponents();
    if (componentChild.isPresent()) {
      for (TextComponent component : componentChild.get()) {
        if (component.getComponentType().equals(componentType)) {
          components.add(component);
        } else {
          this.getComponentsByType(component, componentType, components);
        }
      }
    }

    return Optional.of(components);
  }

  public String toString() {
    StringBuilder sb = new StringBuilder();
    components.forEach(component -> {
      ComponentType componentType = component.getComponentType();
      switch (componentType) {
        case PARAGRAPH -> sb.append(component).append("\n");
        case SENTENCE, LEXEME -> sb.append(component).append(" ");
        default -> sb.append(component);
      }
    });
    return sb.toString().trim();
  }
}
