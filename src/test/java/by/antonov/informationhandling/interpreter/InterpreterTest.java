package by.antonov.informationhandling.interpreter;

import static org.testng.Assert.assertEquals;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class InterpreterTest {

  @DataProvider(name = "interpreterDataTest")
  public Object[][] interpreterDataTest() {
    return new Object[][]{
        {"13<<2", 52},
        {"3>>5", 0},
        {"~6&9|(3&4)", 9},
        {"5|(1&2&(3|(4&(1^5|6&47)|3)|(~89&4|(42&7)))|1)", 5},
        {"(~71&(2&3|(3|(2&1>>2|2)&2)|10&2))|78", 78},
        {"(7^5|1&2<<(2|5>>2&71))|1200", 1202}
    };
  }

  @Test(dataProvider = "interpreterDataTest")
  public void interpreterTest(String expression, Number expected) {
    Interpreter interpreter = new Interpreter();
    Number actual = interpreter.calculateExpression(expression);

    assertEquals(actual, expected);
  }
}
