package by.antonov.informationhandling.main;

import by.antonov.informationhandling.interpreter.Interpreter;

public class InterpreterMain {
  public static void main(String[] args) {
    Interpreter interpreter = new Interpreter();

    String str = "13<<2 ";
    Integer num = 13<<2;
    System.out.println("res: " + str + " = " + interpreter.calculateExpression(str) + " / " + num + "\r\n");

    str = "3>>5";
    num = 3>>5;
    System.out.println("res: " + str + " = " + interpreter.calculateExpression(str) + " / " + num + "\r\n");

    str = "~6&9|(3&4)";
    num = ~6&9|(3&4);
    System.out.println("res: " + str + " = " + interpreter.calculateExpression(str) + " / " + num + "\r\n");

    str = "5|(1&2&(3|(4&(1^5|6&47)|3)|(~89&4|(42&7)))|1)";
    num = 5|(1&2&(3|(4&(1^5|6&47)|3)|(~89&4|(42&7)))|1);
    System.out.println("res: " + str + " = " + interpreter.calculateExpression(str) + " / " + num + "\r\n");

    str = "(~71&(2&3|(3|(2&1>>2|2)&2)|10&2))|78";
    num = (~71&(2&3|(3|(2&1>>2|2)&2)|10&2))|78;
    System.out.println("res: " + str + " = " + interpreter.calculateExpression(str) + " / " + num + "\r\n");

    str = "(7^5|1&2<<(2|5>>2&71))|1200";
    num = (7^5|1&2<<(2|5>>2&71))|1200;
    System.out.println("res: " + str + " = " + interpreter.calculateExpression(str) + " / " + num + "\r\n");
  }
}
