package by.antonov.informationhandling.entity;

import java.util.ArrayList;
import java.util.List;

public class Composite extends Component{
  private final List<Component> components;

  public Composite(ComponentType componentType) {
    super(componentType);
    this.components = new ArrayList<>();
  }

  public void add(Component component) {
    this.components.add(component);
  }

  public void remove(Component component) {
    this.components.remove(component);
  }

  public List<Component> getComponents() {
    return new ArrayList<>(this.components);
  }

  public String convertToString() {
    StringBuilder sb = new StringBuilder();
    components.forEach(component -> {
      ComponentType componentType = component.getComponentType();
      switch (componentType) {
        //case ROOT -> sb.append(component.convertToString());
        case PARAGRAPH -> sb.append(component.convertToString()).append("\n");
        case SENTENCE -> sb.append(component.convertToString()).append(". ");
        case LEXEME -> sb.append(component.convertToString()).append(" ");
        default -> sb.append(component.convertToString());
        //case CHARACTER -> sb.append(component.convertToString());
      }
    });
    return sb.toString();
  }

  public String toString() {
    StringBuilder sb = new StringBuilder("{");
    sb.append(getComponentType().name());
    components.forEach(sb::append);
    sb.append("}");

    return sb.toString();
  }
}
