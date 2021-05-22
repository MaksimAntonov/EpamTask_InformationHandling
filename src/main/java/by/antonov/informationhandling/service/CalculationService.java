package by.antonov.informationhandling.service;

import by.antonov.informationhandling.entity.ComponentType;
import by.antonov.informationhandling.entity.TextComponent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class CalculationService {

  private final static String VOWELS_PATTERN = "[aeiou]";
  private final static String CONSONANTS_PATTERN = "[bcdfghjklmnpqrstvwxyz]";

  public static Integer countOfComponentsByType(TextComponent rootComponent, ComponentType componentType) {
    List<TextComponent> components = rootComponent.getComponentsByType(componentType)
                                                  .orElse(new ArrayList<>());
    return components.size();
  }

  public static Integer countOfVowelsInSentence(TextComponent sentence) {
    return countOfLettersInSentenceByPattern(sentence, VOWELS_PATTERN);
  }

  public static Integer countOfConsonantsInSentence(TextComponent sentence) {
    return countOfLettersInSentenceByPattern(sentence, CONSONANTS_PATTERN);
  }

  public static Map<String, Integer> wordsCountInText(TextComponent component, Integer minimumCount) {
    Map<String, Integer> wordsCount = new HashMap<>();

    List<TextComponent> words = component.getComponentsByType(ComponentType.WORD).orElse(new ArrayList<>());
    for (TextComponent word : words) {
      String wordString = word.toString().toLowerCase();
      wordsCount.merge(wordString, 1, Integer::sum);
    }

    return wordsCount.entrySet().stream()
                     .filter((entry) -> entry.getValue() >= minimumCount)
                     .sorted((entry1, entry2) -> entry2.getValue() - entry1.getValue())
                     .collect(Collectors.toMap(
                         Entry::getKey,
                         Entry::getValue,
                         (oldValue, newValue) -> oldValue,
                         LinkedHashMap::new)
                     );
  }

  private static Integer countOfLettersInSentenceByPattern(TextComponent sentence, String letterPattern) {
    int count = 0;
    List<TextComponent> symbols = sentence.getComponentsByType(ComponentType.CHARACTER).orElse(new ArrayList<>());
    Pattern pattern = Pattern.compile(letterPattern, Pattern.CASE_INSENSITIVE);

    for (TextComponent symbol : symbols) {
      if (pattern.matcher(symbol.toString()).matches()) {
        count++;
      }
    }
    return count;
  }
}
