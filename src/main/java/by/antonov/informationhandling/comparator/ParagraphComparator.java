package by.antonov.informationhandling.comparator;

import by.antonov.informationhandling.entity.ComponentType;
import by.antonov.informationhandling.entity.TextComponent;
import by.antonov.informationhandling.service.CalculationService;
import by.antonov.informationhandling.service.impl.CalculationServiceImpl;
import java.util.Comparator;

public class ParagraphComparator implements Comparator<TextComponent> {

  @Override
  public int compare(TextComponent paragraph1, TextComponent paragraph2) {
    CalculationService calculationService = new CalculationServiceImpl();

    int sentences1 = calculationService.countOfComponentsByType(paragraph1, ComponentType.SENTENCE);
    int sentences2 = calculationService.countOfComponentsByType(paragraph2, ComponentType.SENTENCE);
    return sentences1 - sentences2;
  }
}
