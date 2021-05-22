package by.antonov.informationhandling.interpreter;

@FunctionalInterface
public interface Expression {

  void interpret(Context context);
}
