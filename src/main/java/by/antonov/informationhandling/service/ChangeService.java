package by.antonov.informationhandling.service;

import by.antonov.informationhandling.entity.ComponentType;
import by.antonov.informationhandling.entity.TextComponent;
import java.util.ArrayList;
import java.util.List;

public class ChangeService {
  public static TextComponent deleteSentencesByWordCount(TextComponent textComponent, Integer minWordsCount) {
    List<TextComponent> paragraphs = textComponent.getComponentsByType(ComponentType.PARAGRAPH)
        .orElse(new ArrayList<>());

    for (TextComponent paragraph : paragraphs) {
      List<TextComponent> sentences = paragraph.getComponentsByType(ComponentType.SENTENCE).orElse(new ArrayList<>());
      for (TextComponent sentence : sentences) {
        if (CalculationService.countOfComponentsByType(sentence, ComponentType.WORD) <= minWordsCount) {
          paragraph.remove(sentence);
        }
      }
    }

    return textComponent;
  }
}
