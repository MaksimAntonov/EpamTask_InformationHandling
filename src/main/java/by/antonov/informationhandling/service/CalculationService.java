package by.antonov.informationhandling.service;

import by.antonov.informationhandling.entity.ComponentType;
import by.antonov.informationhandling.entity.TextComponent;
import java.util.ArrayList;
import java.util.List;

public class CalculationService {
  public static Integer countOfComponentsByType(TextComponent rootComponent, ComponentType componentType) {
    List<TextComponent> components = rootComponent.getComponentsByType(componentType)
        .orElse(new ArrayList<>());
    return components.size();
  }
}
