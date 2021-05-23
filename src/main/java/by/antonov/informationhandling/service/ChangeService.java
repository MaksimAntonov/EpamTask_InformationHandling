package by.antonov.informationhandling.service;

import by.antonov.informationhandling.entity.TextComponent;

public interface ChangeService {

  void deleteSentencesByWordCount(TextComponent textComponent, Integer minWordsCount);
}
