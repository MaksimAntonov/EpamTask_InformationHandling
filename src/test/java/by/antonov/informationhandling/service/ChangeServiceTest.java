package by.antonov.informationhandling.service;

import by.antonov.informationhandling.customparser.*;
import by.antonov.informationhandling.entity.BaseTextLeaf;
import by.antonov.informationhandling.entity.ComponentType;
import by.antonov.informationhandling.entity.TextComponent;
import by.antonov.informationhandling.entity.TextComposite;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ChangeServiceTest {
  @Test
  public void deleteSentencesByWordCountTest() {
    String stringActual =
        """
              It has survived - not only (five) centuries, but also the leap into 13<<2 electronic typesetting, remaining 3>>5 essentially ~6&9|(3&4) unchanged. It was popularised in the 5|(1&2&(3|(4&(1^5|6&47)|3)|(~89&4|(42&7)))|1) with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.
              It is a long established fact that a reader will be distracted by the readable content of a page when looking at its layout. The point of using (~71&(2&3|(3|(2&1>>2|2)&2)|10&2))|78 Ipsum is that it has a more-or-less normal distribution of letters, as opposed to using (Content here), content here', making it look like readable English.
              It is a (7^5|1&2<<(2|5>>2&71))|1200 established fact that a reader will be of a page when looking at its layout.
              Bye.
            """;
    TextComponent actualComponent = this.getComponentForText(stringActual);

    String stringExpected =
        """
              It has survived - not only (five) centuries, but also the leap into 13<<2 electronic typesetting, remaining 3>>5 essentially ~6&9|(3&4) unchanged. It was popularised in the 5|(1&2&(3|(4&(1^5|6&47)|3)|(~89&4|(42&7)))|1) with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.
              It is a long established fact that a reader will be distracted by the readable content of a page when looking at its layout. The point of using (~71&(2&3|(3|(2&1>>2|2)&2)|10&2))|78 Ipsum is that it has a more-or-less normal distribution of letters, as opposed to using (Content here), content here', making it look like readable English.
              It is a (7^5|1&2<<(2|5>>2&71))|1200 established fact that a reader will be of a page when looking at its layout.
            """;
    TextComponent expectedComponent = this.getComponentForText(stringExpected);
    ChangeService.deleteSentencesByWordCount(actualComponent, 5);

    Assert.assertEquals(actualComponent.toString(), expectedComponent.toString());
  }

  private TextComponent getComponentForText(String text) {
    TextComponent component = new TextComposite(ComponentType.ROOT);
    TextComponent baseText = new BaseTextLeaf(text);
    component.add(baseText);

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

    textToParagraphParser.parse(component);

    return component;
  }
}
