package by.antonov.informationhandling.service.impl;

import by.antonov.informationhandling.entity.ComponentType;
import by.antonov.informationhandling.entity.TextComponent;
import by.antonov.informationhandling.service.CalculationService;
import by.antonov.informationhandling.service.SearchService;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class SearchServiceImpl implements SearchService {

  public List<String> findSentenceWithLongestWord(TextComponent textComponent) {
    CalculationService calculationService = new CalculationServiceImpl();
    List<TextComponent> sentences = textComponent.getComponentsByType(ComponentType.SENTENCE).orElse(new ArrayList<>());
    Set<String> resultSentences = new HashSet<>();
    int wordLength = 1;

    for (TextComponent sentence : sentences) {
      List<TextComponent> words = sentence.getComponentsByType(ComponentType.WORD).orElse(new ArrayList<>());

      for (TextComponent word : words) {
        int length = calculationService.countOfComponentsByType(word, ComponentType.CHARACTER);
        if (length > wordLength) {
          resultSentences.clear();
          resultSentences.add(sentence.toString());
          wordLength = length;
        } else {
          if (length == wordLength) {
            resultSentences.add(sentence.toString());
          }
        }
      }
    }

    return resultSentences.stream().toList();
  }
}
