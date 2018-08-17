import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class CalculatorTest {

  private static final String EXPRESSION = "(2 + 4) * 3";

  @Test
  void calc() {
    Object result = Calculator.calc(EXPRESSION);
    assertEquals(18, result);
  }
}