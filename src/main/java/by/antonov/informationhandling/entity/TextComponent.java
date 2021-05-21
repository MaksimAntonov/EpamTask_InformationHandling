package by.antonov.informationhandling.entity;

import java.util.List;
import java.util.Optional;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public abstract class TextComponent {
  private static final Logger logger = LogManager.getLogger();
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
    logger.warn("Using getComponents in " + this.componentType.toString());
    return Optional.empty();
  }

  public Optional<String> getBaseText() {
    logger.warn("Using getBaseText in " + this.componentType.toString());
    return Optional.empty();
  }

  public Optional<List<TextComponent>> getComponentsByType(ComponentType componentType) {
    logger.warn("Using getComponentsByType in " + this.componentType.toString() + " / " + componentType.toString());
    return Optional.empty();
  }
}
