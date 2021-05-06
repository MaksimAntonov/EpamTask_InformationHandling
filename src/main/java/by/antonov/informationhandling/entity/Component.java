package by.antonov.informationhandling.entity;

public abstract class Component {
  private final ComponentType componentType;

  protected Component(ComponentType componentType) {
    this.componentType = componentType;
  }

  public ComponentType getComponentType() {
    return this.componentType;
  }

  public void add(Component component) {};

  public abstract String convertToString();
}
