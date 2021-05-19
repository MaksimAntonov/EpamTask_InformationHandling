package by.antonov.informationhandling.customparser;

import by.antonov.informationhandling.entity.ComponentType;
import by.antonov.informationhandling.entity.TextComponent;
import by.antonov.informationhandling.entity.TextSymbol;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TextElementToCharacterParser extends CustomParser{
  private final static String SYMBOL_PATTERN = "(?<symbol>[A-Za-zА-Яа-яЁё0-9'-])";

  @Override
  public void parse(TextComponent rootComponent) {
    componentParser(rootComponent, ComponentType.NUMBER);
    componentParser(rootComponent, ComponentType.WORD);
  }

  private void componentParser(TextComponent rootComponent, ComponentType componentType) {
    Optional<List<TextComponent>> components = rootComponent.getComponentsByType(componentType);

    if (components.isPresent()) {
      for (TextComponent component : components.get()) {
        Optional<String> baseTextOptional = component.getBaseText();
        if (baseTextOptional.isPresent()) {
          String baseText = baseTextOptional.get();
          Pattern pattern = Pattern.compile(SYMBOL_PATTERN);
          Matcher matcher = pattern.matcher(baseText);
          while (matcher.find()) {
            TextComponent textSymbol = new TextSymbol(ComponentType.CHARACTER, matcher.group("symbol").charAt(0));
            component.add(textSymbol);
          }
        }
      }
    }
  }
}
