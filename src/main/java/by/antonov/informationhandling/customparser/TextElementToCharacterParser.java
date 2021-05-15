package by.antonov.informationhandling.customparser;

import by.antonov.informationhandling.entity.ComponentType;
import by.antonov.informationhandling.entity.TextComponent;
import by.antonov.informationhandling.entity.TextSymbol;
import java.util.ArrayList;
import java.util.List;

public class TextElementToCharacterParser extends CustomParser{
  private final static String PUNCTUATION_SYMBOL_PATTERN = "\\W";
  private final static String NUMBER_SYMBOL_PATTERN = "\\d";
  private final static String TEXT_SYMBOL_PATTERN = "[A-Za-zА-Яа-яЁё]";

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
      String[] strSymbols = baseText.split("");
      for (String strSymbol : strSymbols) {
        TextComponent textSymbol = new TextSymbol(ComponentType.CHARACTER, strSymbol.charAt(0));
        component.add(textSymbol);
      }
    }
  }
}
