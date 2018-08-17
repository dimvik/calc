public class Calculator {

  public static Object calc(String expr) {
    Parser parser = new Parser();
    return parser.parse(expr).getValue();
  }

}