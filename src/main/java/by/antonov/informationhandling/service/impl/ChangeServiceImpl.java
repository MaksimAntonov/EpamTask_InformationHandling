package by.antonov.informationhandling.service.impl;

import by.antonov.informationhandling.entity.ComponentType;
import by.antonov.informationhandling.entity.TextComponent;
import by.antonov.informationhandling.service.CalculationService;
import by.antonov.informationhandling.service.ChangeService;
import java.util.ArrayList;
import java.util.List;

public class ChangeServiceImpl implements ChangeService {

  public void deleteSentencesByWordCount(TextComponent textComponent, Integer minWordsCount) {
    CalculationService calculationService = new CalculationServiceImpl();
    List<TextComponent> paragraphs = textComponent.getComponentsByType(ComponentType.PARAGRAPH)
                                                  .orElse(new ArrayList<>());

    for (TextComponent paragraph : paragraphs) {
      List<TextComponent> sentences = paragraph.getComponentsByType(ComponentType.SENTENCE).orElse(new ArrayList<>());
      for (TextComponent sentence : sentences) {
        if (calculationService.countOfComponentsByType(sentence, ComponentType.WORD) <= minWordsCount) {
          paragraph.remove(sentence);
        }
      }
    }
  }
}
