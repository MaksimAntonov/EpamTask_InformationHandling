package by.antonov.informationhandling.main;

import by.antonov.informationhandling.customparser.CharacterParser;
import by.antonov.informationhandling.customparser.CustomParser;
import by.antonov.informationhandling.customparser.ExpressionParser;
import by.antonov.informationhandling.customparser.LexemeParser;
import by.antonov.informationhandling.customparser.ParagraphParser;
import by.antonov.informationhandling.customparser.SentenceParser;
import by.antonov.informationhandling.customparser.TextElementParser;
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

      System.out.println("Parsing: \n" + string);

      TextComponent rootComponent = new TextComposite(ComponentType.ROOT);
      TextComponent baseText = new BaseTextLeaf(string);
      rootComponent.add(baseText);

      CustomParser textToParagraphParser = new ParagraphParser();
      CustomParser sentenceParser = new SentenceParser();
      CustomParser expressionParser = new ExpressionParser();
      CustomParser lexemeParser = new LexemeParser();
      CustomParser textElementParser = new TextElementParser();
      CustomParser characterParser = new CharacterParser();

      textElementParser.nextParser(characterParser);
      lexemeParser.nextParser(textElementParser);
      expressionParser.nextParser(lexemeParser);
      sentenceParser.nextParser(expressionParser);
      textToParagraphParser.nextParser(sentenceParser);

      textToParagraphParser.parse(rootComponent);

      System.out.println("\nDeparsing: \n" + rootComponent);
    } catch (CustomException e) {
      System.out.println(e.getMessage());
    }
  }
}
