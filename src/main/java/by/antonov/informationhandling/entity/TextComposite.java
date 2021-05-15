package by.antonov.informationhandling.entity;

import java.util.ArrayList;
import java.util.List;

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

  public List<TextComponent> getComponents() {
    return new ArrayList<>(this.components);
  }

  public String getBaseText(TextComponent textComponent) {
    List<TextComponent> components = textComponent.getComponents();
    for (TextComponent component : components) {
      if (component.getComponentType().equals(ComponentType.BASE_TEXT)) {
        textComponent.remove(component);
        return component.toString();
      }
    }

    return null;
  }

  public List<TextComponent> getComponentsByType(
      TextComponent textComponent, ComponentType componentType, List<TextComponent> components
  ) {
    List<TextComponent> componentChild = textComponent.getComponents();
    if (componentChild != null) {
      for (TextComponent component : componentChild) {
        if (component.getComponentType().equals(componentType)) {
          components.add(component);
        } else {
          this.getComponentsByType(component, componentType, components);
        }
      }
    }

    return components;
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
