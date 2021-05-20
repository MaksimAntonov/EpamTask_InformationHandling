package by.antonov.informationhandling.service;

import by.antonov.informationhandling.entity.ComponentType;
import by.antonov.informationhandling.entity.TextComponent;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class SearchService {
  public static List<TextComponent> findSentenceWithLongestWord(TextComponent textComponent) {
    List<TextComponent> sentences = textComponent.getComponentsByType(ComponentType.SENTENCE).orElse(new ArrayList<>());
    Set<TextComponent> resultSentences = new HashSet<>();
    int wordLength = 1;

    for (TextComponent sentence : sentences) {
      List<TextComponent> words = sentence.getComponentsByType(ComponentType.WORD).orElse(new ArrayList<>());

      for (TextComponent word : words) {
        int length = CalculationService.countOfComponentsByType(word, ComponentType.CHARACTER);
        if (length > wordLength) {
          resultSentences.clear();
          resultSentences.add(sentence);
          wordLength = length;
        } else {
          if (length == wordLength) {
            resultSentences.add(sentence);
          }
        }
      }
    }

    return resultSentences.stream().toList();
  }
}
