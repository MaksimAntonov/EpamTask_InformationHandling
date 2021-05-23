package by.antonov.informationhandling.service;

import by.antonov.informationhandling.entity.TextComponent;
import java.util.List;

public interface SearchService {

  List<String> findSentenceWithLongestWord(TextComponent textComponent);
}
