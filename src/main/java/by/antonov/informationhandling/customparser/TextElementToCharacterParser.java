package by.antonov.informationhandling.customparser;

import by.antonov.informationhandling.entity.ComponentType;
import by.antonov.informationhandling.entity.TextComponent;
import by.antonov.informationhandling.entity.TextSymbol;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TextElementToCharacterParser extends CustomParser{
  private final static String SYMBOL_PATTERN = "(?<symbol>[A-Za-zА-Яа-яЁё0-9'-])";

  @Override
  public void handle(TextComponent rootComponent) {
    componentParser(rootComponent, ComponentType.NUMBER);
    componentParser(rootComponent, ComponentType.WORD);
  }

  private void componentParser(TextComponent rootComponent, ComponentType componentType) {
    List<TextComponent> components = rootComponent.getComponentsByType(
        rootComponent,
        componentType,
        new ArrayList<>());

    for (TextComponent component : components) {
      String baseText = component.getBaseText(component);

      Pattern pattern = Pattern.compile(SYMBOL_PATTERN);
      Matcher matcher = pattern.matcher(baseText);
      while (matcher.find()) {
        TextComponent textSymbol = new TextSymbol(ComponentType.CHARACTER, matcher.group("symbol").charAt(0));
        component.add(textSymbol);
      }
    }
  }
}
