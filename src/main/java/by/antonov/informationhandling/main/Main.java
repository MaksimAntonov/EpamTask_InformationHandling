package by.antonov.informationhandling.main;

import by.antonov.informationhandling.parser.CharacterParser;
import by.antonov.informationhandling.parser.CompositeParser;
import by.antonov.informationhandling.parser.ExpressionParser;
import by.antonov.informationhandling.parser.LexemeParser;
import by.antonov.informationhandling.parser.ParagraphParser;
import by.antonov.informationhandling.parser.SentenceParser;
import by.antonov.informationhandling.parser.TextElementParser;
import by.antonov.informationhandling.reader.Reader;
import by.antonov.informationhandling.entity.BaseTextLeaf;
import by.antonov.informationhandling.entity.ComponentType;
import by.antonov.informationhandling.entity.TextComponent;
import by.antonov.informationhandling.entity.TextComposite;
import by.antonov.informationhandling.exception.CustomException;

public class Main {

  public static void main(String[] args) {
    try {
      Reader reader = new Reader();
      String string = reader.readDataFromFile("data/data.txt");

      System.out.println("Parsing: \n" + string);

      TextComponent rootComponent = new TextComposite(ComponentType.ROOT);
      TextComponent baseText = new BaseTextLeaf(string);
      rootComponent.add(baseText);

      CompositeParser textToParagraphParser = new ParagraphParser();
      CompositeParser sentenceParser = new SentenceParser();
      CompositeParser expressionParser = new ExpressionParser();
      CompositeParser lexemeParser = new LexemeParser();
      CompositeParser textElementParser = new TextElementParser();
      CompositeParser characterParser = new CharacterParser();

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
