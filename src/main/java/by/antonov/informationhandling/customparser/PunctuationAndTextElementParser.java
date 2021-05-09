package by.antonov.informationhandling.customparser;

import by.antonov.informationhandling.entity.ComponentType;
import by.antonov.informationhandling.entity.TextComponent;
import by.antonov.informationhandling.entity.TextSymbol;
import java.util.ArrayList;
import java.util.List;

public class PunctuationAndTextElementParser extends CustomParser{
  private final static String PUNCTUATION_SYMBOL_PATTERN = "\\W";
  private final static String NUMBER_SYMBOL_PATTERN = "\\d";
  private final static String TEXT_SYMBOL_PATTERN = "[A-Za-zА-Яа-яЁё]";

  @Override
  public void handle(TextComponent rootComponent) {
    List<TextComponent> components = rootComponent.getComponentsByType(
        rootComponent,
        ComponentType.LEXEME,
        new ArrayList<>());

    for (TextComponent component : components) {
      String baseText = component.getBaseText(component);
      String[] strSymbols = baseText.split("");
      for (String strSymbol : strSymbols) {
        TextComponent textSymbol = null;
        if (strSymbol.matches(PUNCTUATION_SYMBOL_PATTERN)) {
          textSymbol = new TextSymbol(ComponentType.PUNCTUATION, strSymbol.charAt(0));
        }
        if (strSymbol.matches(NUMBER_SYMBOL_PATTERN)) {
          textSymbol = new TextSymbol(ComponentType.NUMBER, strSymbol.charAt(0));
        }
        if (strSymbol.matches(TEXT_SYMBOL_PATTERN)) {
          textSymbol = new TextSymbol(ComponentType.CHARACTER, strSymbol.charAt(0));
        }
        component.add(textSymbol);
        //System.out.println("String: " + strSymbol);
      }
    }
  }
}
