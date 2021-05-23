package by.antonov.informationhandling.service;

import by.antonov.informationhandling.customparser.CharacterParser;
import by.antonov.informationhandling.customparser.CustomParser;
import by.antonov.informationhandling.customparser.ExpressionParser;
import by.antonov.informationhandling.customparser.LexemeParser;
import by.antonov.informationhandling.customparser.ParagraphParser;
import by.antonov.informationhandling.customparser.SentenceParser;
import by.antonov.informationhandling.customparser.TextElementParser;
import by.antonov.informationhandling.entity.BaseTextLeaf;
import by.antonov.informationhandling.entity.ComponentType;
import by.antonov.informationhandling.entity.TextComponent;
import by.antonov.informationhandling.entity.TextComposite;
import by.antonov.informationhandling.service.impl.SearchServiceImpl;
import java.util.ArrayList;
import java.util.List;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class SearchServiceTest {

  private SearchService searchService;
  private TextComponent rootComponent;

  @BeforeClass
  public void beforeClass() {
    this.searchService = new SearchServiceImpl();
    String string = " It has survived - not only (five) centuries, but also the leap into 13<<2 electronic typesetting, remaining 3>>5 essentially ~6&9|(3&4) unchanged. It was popularised in the 5|(1&2&(3|(4&(1^5|6&47)|3)|(~89&4|(42&7)))|1) with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.\n"
        + "  It is a long established fact that a reader will be distracted by the readable content of a page when looking at its layout. The point of using (~71&(2&3|(3|(2&1>>2|2)&2)|10&2))|78 Ipsum is that it has a more-or-less normal distribution of letters, as opposed to using (Content here), content here', making it look like readable English.\n"
        + "  It is a (7^5|1&2<<(2|5>>2&71))|1200 established fact that a reader will be of a page when looking at its layout.\n"
        + "  Bye.";

    this.rootComponent = new TextComposite(ComponentType.ROOT);
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

    textToParagraphParser.parse(this.rootComponent);
  }

  @Test
  public void findSentenceWithLongestWordTest() {
    List<String> expected = new ArrayList<>();
    expected.add("The point of using 78 Ipsum is that it has a more-or-less normal distribution of letters, as " +
                     "opposed to using (Content here), content here', making it look like readable English.");

    List<String> actual = searchService.findSentenceWithLongestWord(this.rootComponent);

    Assert.assertEquals(actual, expected);
  }
}
