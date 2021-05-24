package by.antonov.informationhandling.comparator;

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
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class ParagraphComparatorTest {

  private TextComponent rootComponent;
  private TextComponent expectedComponent;

  @BeforeClass
  public void beforeClass() {
    String string =
        """
              It has survived - not only (five) centuries, but also the leap into 13<<2 electronic typesetting, remaining 3>>5 essentially ~6&9|(3&4) unchanged. It was popularised in the 5|(1&2&(3|(4&(1^5|6&47)|3)|(~89&4|(42&7)))|1) with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.
              It is a long established fact that a reader will be distracted by the readable content of a page when looking at its layout. The point of using (~71&(2&3|(3|(2&1>>2|2)&2)|10&2))|78 Ipsum is that it has a more-or-less normal distribution of letters, as opposed to using (Content here), content here', making it look like readable English.
              It is a (7^5|1&2<<(2|5>>2&71))|1200 established fact that a reader will be of a page when looking at its layout.
              Bye.
            """;

    this.rootComponent = getComponentForText(string);

    String expectedString =
        """
              It is a (7^5|1&2<<(2|5>>2&71))|1200 established fact that a reader will be of a page when looking at its layout.
              Bye.
              It has survived - not only (five) centuries, but also the leap into 13<<2 electronic typesetting, remaining 3>>5 essentially ~6&9|(3&4) unchanged. It was popularised in the 5|(1&2&(3|(4&(1^5|6&47)|3)|(~89&4|(42&7)))|1) with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.
              It is a long established fact that a reader will be distracted by the readable content of a page when looking at its layout. The point of using (~71&(2&3|(3|(2&1>>2|2)&2)|10&2))|78 Ipsum is that it has a more-or-less normal distribution of letters, as opposed to using (Content here), content here', making it look like readable English.
            """;
    this.expectedComponent = getComponentForText(expectedString);
  }

  @Test
  public void compareTest() {
    Comparator<TextComponent> componentComparator = new ParagraphComparator();
    List<String> actual = rootComponent.getComponentsByType(ComponentType.PARAGRAPH).orElse(new ArrayList<>())
                                       .stream().sorted(componentComparator)
                                       .map(Object::toString)
                                       .collect(Collectors.toList());
    List<String> expected = expectedComponent.getComponentsByType(ComponentType.PARAGRAPH).orElse(new ArrayList<>())
                                             .stream().map(Objects::toString)
                                             .collect(Collectors.toList());

    Assert.assertEquals(actual, expected);
  }

  private TextComponent getComponentForText(String text) {
    TextComponent component = new TextComposite(ComponentType.ROOT);
    TextComponent baseText = new BaseTextLeaf(text);
    component.add(baseText);

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

    textToParagraphParser.parse(component);

    return component;
  }
}
