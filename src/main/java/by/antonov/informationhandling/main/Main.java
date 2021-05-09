package by.antonov.informationhandling.main;

import by.antonov.informationhandling.customparser.CustomParser;
import by.antonov.informationhandling.customparser.SentenceToLexemeParser;
import by.antonov.informationhandling.customparser.TextToParagraphParser;
import by.antonov.informationhandling.customparser.PunctuationAndTextElementParser;
import by.antonov.informationhandling.customparser.ParagraphToSentenceParser;
import by.antonov.informationhandling.customreader.CustomReader;
import by.antonov.informationhandling.entity.BaseTextLeaf;
import by.antonov.informationhandling.entity.ComponentType;
import by.antonov.informationhandling.entity.TextComponent;
import by.antonov.informationhandling.entity.TextComposite;
import by.antonov.informationhandling.exception.CustomException;

public class Main {
  public static void main(String[] args) {
    try {
      CustomReader reader = new CustomReader();
      String string = reader.readDataFromFile("data/data.txt");

      TextComponent rootComponent = new TextComposite(ComponentType.ROOT);
      TextComponent baseText = new BaseTextLeaf(string);
      rootComponent.add(baseText);

      CustomParser parser = new TextToParagraphParser();
      CustomParser sentenceParser = new ParagraphToSentenceParser();
      CustomParser lexemeParser = new SentenceToLexemeParser();
      CustomParser punctuationAndTextParser = new PunctuationAndTextElementParser();

      lexemeParser.nextParser(punctuationAndTextParser);
      sentenceParser.nextParser(lexemeParser);
      parser.nextParser(sentenceParser);

      parser.handle(rootComponent);

      System.out.println("Deparsing: \n" + rootComponent.convertToString());
    } catch (CustomException e) {
      System.out.println(e.getMessage());
    }
  }
}
