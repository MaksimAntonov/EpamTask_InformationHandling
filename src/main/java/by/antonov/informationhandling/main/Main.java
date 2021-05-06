package by.antonov.informationhandling.main;

import by.antonov.informationhandling.customparser.CustomParser;
import by.antonov.informationhandling.customparser.LexemeParser;
import by.antonov.informationhandling.customparser.ParagraphParser;
import by.antonov.informationhandling.customparser.RootParser;
import by.antonov.informationhandling.customparser.SentenceParser;
import by.antonov.informationhandling.entity.Component;
import by.antonov.informationhandling.entity.ComponentType;
import by.antonov.informationhandling.entity.Composite;
import by.antonov.informationhandling.entity.Leaf;

public class Main {
  public static void main(String[] args) {
    /*
     *  корневой элемент (Composite)
     *  |-> абзац (Composite)
     *      |-> предложение (Composite)
     *          |-> выделяется символ (Leaf)
     */

    String string = "It has survived - not only (five) centuries, but also the leap into electronic type setting, remaining essentially unchanged. It was popularised in the “Динамо” (Рига) with the release of Letraset sheets.toString() containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker Faclon9 including versions of Lorem Ipsum!\n"
        + "It is a long a!=b established fact that a reader will be distracted by the readable content of a page when looking at its layout. The point of using Ipsum is that it has a more-or-less normal distribution ob.toString(a?b:c), as opposed to using (Content here), content here's, making it look like readable English?\n"
        + "It is a established fact that a reader will be of a page when looking at its layout...\n"
        + "Bye бандерлоги.";

    CustomParser lexemeParser = new LexemeParser();
    CustomParser sentenceParser = new SentenceParser();
    sentenceParser.next(lexemeParser);
    CustomParser paragraphParser = new ParagraphParser();
    paragraphParser.next(sentenceParser);
    CustomParser rootParser = new RootParser();
    rootParser.next(paragraphParser);


    Component root = rootParser.handle(string);
    //System.out.println(root.convertToString());

  }
}
