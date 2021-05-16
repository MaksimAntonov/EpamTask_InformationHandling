package by.antonov.informationhandling.customreader;

import by.antonov.informationhandling.exception.CustomException;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CustomReaderTest {
  @Test
  public void readDataFromFileTest() throws CustomException {
    CustomReader reader = new CustomReader();
    String actual = reader.readDataFromFile("data/testdata.txt");
    String expected = "\tIt has survived - not only (five) centuries, but also the leap into 13<<2 electronic typesetting, remaining 3>>5 essentially ~6&9|(3&4) unchanged. It was popularised in the 5|(1&2&(3|(4&(1^5|6&47)|3)|(~89&4|(42&7)))|1) with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.\n"
        + "\tIt is a long established fact that a reader will be distracted by the readable content of a page when looking at its layout. The point of using (~71&(2&3|(3|(2&1>>2|2)&2)|10&2))|78 Ipsum is that it has a more-or-less normal distribution of letters, as opposed to using (Content here), content here', making it look like readable English.\n"
        + "\tIt is a (7^5|1&2<<(2|5>>2&71))|1200 established fact that a reader will be of a page when looking at its layout.\n"
        + "\tBye.";

    Assert.assertEquals(actual, expected);
  }
}
