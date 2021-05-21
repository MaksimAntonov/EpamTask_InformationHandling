package by.antonov.informationhandling.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TextComposite extends TextComponent {
  private static final String PARAGRAPH_DELIMITER = "\n";
  private static final String LEXEME_DELIMITER = " ";
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

  public Optional<String> getBaseText() {
    Optional<List<TextComponent>> components = this.getComponents();
    Optional<String> optionalResult = Optional.empty();
    if (components.isPresent()) {
      for (TextComponent component : components.get()) {
        if (component.getComponentType() == ComponentType.BASE_TEXT) {
          this.remove(component);
          optionalResult = Optional.of(component.toString());
        }
      }
    }

    return optionalResult;
  }

  public Optional<List<TextComponent>> getComponentsByType(ComponentType componentType) {
    List<TextComponent> results = new ArrayList<>();

    Optional<List<TextComponent>> components = this.getComponents();
    if (components.isPresent()) {
      for (TextComponent component : components.get()) {
        if (component.getComponentType() == componentType) {
          results.add(component);
        } else {
          if (getClass().equals(component.getClass())) {
            results.addAll(component.getComponentsByType(componentType).orElse(new ArrayList<>()));
          }
        }
      }
    }

    return Optional.of(results);
  }

  public String toString() {
    StringBuilder sb = new StringBuilder();
    components.forEach(component -> {
      ComponentType componentType = component.getComponentType();
      switch (componentType) {
        case PARAGRAPH -> sb.append(component).append(PARAGRAPH_DELIMITER);
        case SENTENCE, LEXEME -> sb.append(component).append(LEXEME_DELIMITER);
        default -> sb.append(component);
      }
    });
    return sb.toString().trim();
  }
}
