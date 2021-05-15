package by.antonov.informationhandling.main;

import by.antonov.informationhandling.customparser.CustomParser;
import by.antonov.informationhandling.customparser.ExpressionForSentence;
import by.antonov.informationhandling.customparser.LexemeToTextElement;
import by.antonov.informationhandling.customparser.ParagraphToSentenceParser;
import by.antonov.informationhandling.customparser.TextElementToCharacterParser;
import by.antonov.informationhandling.customparser.SentenceToLexemeParser;
import by.antonov.informationhandling.customparser.TextToParagraphParser;
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

      CustomParser textToParagraphParser = new TextToParagraphParser();
      CustomParser sentenceParser = new ParagraphToSentenceParser();
      CustomParser expressionParser = new ExpressionForSentence();
      CustomParser lexemeParser = new SentenceToLexemeParser();
      CustomParser textElementParser = new LexemeToTextElement();
      CustomParser characterParser = new TextElementToCharacterParser();

      textElementParser.nextParser(characterParser);
      lexemeParser.nextParser(textElementParser);
      expressionParser.nextParser(lexemeParser);
      sentenceParser.nextParser(expressionParser);
      textToParagraphParser.nextParser(sentenceParser);

      textToParagraphParser.handle(rootComponent);

      System.out.println("\nDeparsing: \n" + rootComponent);
    } catch (CustomException e) {
      System.out.println(e.getMessage());
    }
  }
}
