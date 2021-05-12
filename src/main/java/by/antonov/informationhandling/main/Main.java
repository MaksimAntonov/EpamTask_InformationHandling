package by.antonov.informationhandling.main;

import by.antonov.informationhandling.interpreter.Interpreter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
  public static void main(String[] args) {
    String PATTERN = ".*(?<expr>\\([\\d|<>&~^]+\\)).*";
    String str = "(~71&(2&3|(3|(2&1>>2|2)&2)|10&2))|78";
    Interpreter interpreter = new Interpreter();

    Pattern pattern = Pattern.compile(PATTERN);
    while (Pattern.matches(PATTERN, str)) {
      Matcher matcher = pattern.matcher(str);
      while (matcher.find()) {
        String originalText = matcher.group("expr");
        interpreter.parse(originalText);
        System.out.println(originalText + " / " + interpreter.calculate());
        str = str.replace(originalText, "" + interpreter.calculate());
      }
    }

    interpreter.parse(str);
    Integer num = (~71&(2&3|(3|(2&1>>2|2)&2)|10&2))|78;
    System.out.println(interpreter.calculate() + " / " + num);

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
