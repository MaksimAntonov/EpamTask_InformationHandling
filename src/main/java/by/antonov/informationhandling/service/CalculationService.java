package by.antonov.informationhandling.service;

import by.antonov.informationhandling.entity.ComponentType;
import by.antonov.informationhandling.entity.TextComponent;
import java.util.Map;

public interface CalculationService {

  Integer countOfComponentsByType(TextComponent rootComponent, ComponentType componentType);

  Integer countOfVowelsInSentence(TextComponent sentence);

  Integer countOfConsonantsInSentence(TextComponent sentence);

  Map<String, Integer> wordsCountInText(TextComponent component, Integer minimumCount);
}
