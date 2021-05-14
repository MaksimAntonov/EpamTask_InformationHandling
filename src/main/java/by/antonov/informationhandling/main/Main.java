package by.antonov.informationhandling.main;

import by.antonov.informationhandling.interpreter.Interpreter;

public class Main {
  public static void main(String[] args) {
    Interpreter interpreter = new Interpreter();
    String str = "13<<2 ";   // 52
    Integer num = 13<<2 ;    // 52
    System.out.println("res: " + str + " = " + interpreter.calculateExpression(str) + " / " + num + "\r\n");

    str = "3>>5"; // 0
    num = 3>>5;   // 0
    System.out.println("res: " + str + " = " + interpreter.calculateExpression(str) + " / " + num + "\r\n");

    str = "~6&9|(3&4)";   // 9
    num = ~6&9|(3&4);     // 9
    System.out.println("res: " + str + " = " + interpreter.calculateExpression(str) + " / " + num + "\r\n");

    str = "5|(1&2&(3|(4&(1^5|6&47)|3)|(~89&4|(42&7)))|1)";   // 7
    num = 5|(1&2&(3|(4&(1^5|6&47)|3)|(~89&4|(42&7)))|1);     // 5
    System.out.println("res: " + str + " = " + interpreter.calculateExpression(str) + " / " + num + "\r\n");

    str = "(~71&(2&3|(3|(2&1>>2|2)&2)|10&2))|78";    // 78
    num = (~71&(2&3|(3|(2&1>>2|2)&2)|10&2))|78;      // 78
    System.out.println("res: " + str + " = " + interpreter.calculateExpression(str) + " / " + num + "\r\n");

    str = "(7^5|1&2<<(2|5>>2&71))|1200";   // 1202
    num = (7^5|1&2<<(2|5>>2&71))|1200;     // 1202
    System.out.println("res: " + str + " = " + interpreter.calculateExpression(str) + " / " + num + "\r\n");

    /*try {
      CustomReader reader = new CustomReader();
      String string = reader.readDataFromFile("data/data.txt");

      TextComponent rootComponent = new TextComposite(ComponentType.ROOT);
      TextComponent baseText = new BaseTextLeaf(string);
      rootComponent.add(baseText);

      CustomParser interpreter = new TextToParagraphParser();
      CustomParser sentenceParser = new ParagraphToSentenceParser();
      CustomParser lexemeParser = new SentenceToLexemeParser();
      CustomParser punctuationAndTextParser = new PunctuationAndTextElementParser();

      lexemeParser.nextParser(punctuationAndTextParser);
      sentenceParser.nextParser(lexemeParser);
      interpreter.nextParser(sentenceParser);

      interpreter.handle(rootComponent);

      System.out.println("Deparsing: \n" + rootComponent.convertToString());
    } catch (CustomException e) {
      System.out.println(e.getMessage());
    }*/
  }
}
