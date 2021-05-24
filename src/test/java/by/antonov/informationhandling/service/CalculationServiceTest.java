package by.antonov.informationhandling.service;

import by.antonov.informationhandling.parser.CharacterParser;
import by.antonov.informationhandling.parser.CompositeParser;
import by.antonov.informationhandling.parser.ExpressionParser;
import by.antonov.informationhandling.parser.LexemeParser;
import by.antonov.informationhandling.parser.ParagraphParser;
import by.antonov.informationhandling.parser.SentenceParser;
import by.antonov.informationhandling.parser.TextElementParser;
import by.antonov.informationhandling.entity.BaseTextLeaf;
import by.antonov.informationhandling.entity.ComponentType;
import by.antonov.informationhandling.entity.TextComponent;
import by.antonov.informationhandling.entity.TextComposite;
import by.antonov.informationhandling.service.impl.CalculationServiceImpl;
import java.util.HashMap;
import java.util.Map;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class CalculationServiceTest {

  private CalculationService calculationService;
  private TextComponent rootComponent;

  @BeforeClass
  public void beforeClass() {
    this.calculationService = new CalculationServiceImpl();
    String string = " It has survived - not only (five) centuries, but also the leap into 13<<2 electronic typesetting, remaining 3>>5 essentially ~6&9|(3&4) unchanged. It was popularised in the 5|(1&2&(3|(4&(1^5|6&47)|3)|(~89&4|(42&7)))|1) with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.\n"
        + "  It is a long established fact that a reader will be distracted by the readable content of a page when looking at its layout. The point of using (~71&(2&3|(3|(2&1>>2|2)&2)|10&2))|78 Ipsum is that it has a more-or-less normal distribution of letters, as opposed to using (Content here), content here', making it look like readable English.\n"
        + "  It is a (7^5|1&2<<(2|5>>2&71))|1200 established fact that a reader will be of a page when looking at its layout.\n"
        + "  Bye.";

    this.rootComponent = new TextComposite(ComponentType.ROOT);
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

    textToParagraphParser.parse(this.rootComponent);
  }

  @Test
  public void countOfComponentsByTypeTest() {
    Integer actual = calculationService.countOfComponentsByType(this.rootComponent, ComponentType.SENTENCE);
    Integer expected = 6;

    Assert.assertEquals(actual, expected);
  }

  @Test
  public void countOfVowelsInSentenceTest() {
    Integer actual = calculationService.countOfVowelsInSentence(this.rootComponent);
    Integer expected = 227;

    Assert.assertEquals(actual, expected);
  }

  @Test
  public void countOfConsonantsInSentenceTest() {
    Integer actual = calculationService.countOfConsonantsInSentence(this.rootComponent);
    Integer expected = 351;

    Assert.assertEquals(actual, expected);
  }

  @Test
  public void wordsCountInTextTest() {
    Map<String, Integer> expected = new HashMap<>();
    expected.put("a", 7);
    expected.put("it", 6);
    expected.put("of", 6);
    expected.put("the", 5);

    Map<String, Integer> actual = calculationService.wordsCountInText(this.rootComponent, 5);

    Assert.assertEquals(actual, expected);
  }
}
