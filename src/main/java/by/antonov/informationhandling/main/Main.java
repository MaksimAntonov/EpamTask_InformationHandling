package by.antonov.informationhandling.main;

import by.antonov.informationhandling.interpreter.Interpreter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
  public static void main(String[] args) {
    Interpreter interpreter = new Interpreter();
    String str = "(7^5|1&2<<(2|5>>2&71))|1200";   // 1202
    Integer num = (7^5|1&2<<(2|5>>2&71))|1200;    // 1202
    System.out.println(interpreter.calculateExpression(str) + " / " + num);
    str = "(~71&(2&3|(3|(2&1>>2|2)&2)|10&2))|78"; // 78
    num = (~71&(2&3|(3|(2&1>>2|2)&2)|10&2))|78;   // 78
    System.out.println(interpreter.calculateExpression(str) + " / " + num);
    str = "3>>5";   // 0
    num = 3>>5;     // 0
    System.out.println(interpreter.calculateExpression(str) + " / " + num);

    str = "~6&9|(3&4)";   // 1
    num = ~6&9|(3&4);     // 9
    System.out.println(str + " = " + interpreter.calculateExpression(str) + " / " + num);

    str = "5|(1&2&(3|(4&(1^5|6&47)|3)|(~89&4|(42&7)))|1)";    // 7
    num = 5|(1&2&(3|(4&(1^5|6&47)|3)|(~89&4|(42&7)))|1);      // 5
    System.out.println(str + " = " + interpreter.calculateExpression(str) + " / " + num);

    str = "5<<2";   // 64
    num = 5<<2;     // 20
    System.out.println(str + " = " + interpreter.calculateExpression(str) + " / " + num);

    /*interpreter.parse("3>>5");
    Integer num = 3>>5;
    System.out.println(interpreter.calculate() + " / " + num);
    interpreter.parse("2&3|(3|(2&1>>2|2)&2)|10&2");
    System.out.println(interpreter.calculate() + " / " + num);*/

    /*Pattern pattern = Pattern.compile("(?<expr>\\d+|\\||<<|>>|&|~|\\^)");
    Matcher matcher = pattern.matcher(str);
    while (matcher.find()) {
      System.out.println(matcher.group("expr"));
    }*/

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
