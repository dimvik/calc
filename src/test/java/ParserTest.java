import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import exception.CalculatorClosingException;
import exception.CalculatorUnexpectedEndException;
import exception.CalculatorUnexpectedSymbolException;
import expression.node.ExpressionNode;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

class ParserTest {

  private static final String EXPRESSION_NESTED = "(10 + 5) / 5 - (-1 * 2 * (+1 + 1)) + \" объект\" - \"кт\" - 4 * -1";

  private static final String EXPRESSION_PARSED = "(1.5 + 4.5) / 50 + (\"test name\" - \"me\")";

  private static final String EXPRESSION_UNEXPECTED_END = "2 + ";

  private static final String EXPRESSION_BRACKETS_END = "(2 + 4";

  private static final String EXPRESSION_UNEXPECTED_SYMBOL = "2 * *";

  private static final String EXPRESSION_ADD_NULL = "2 + null";

  private static final String EXPRESSION_MINUS_NULL = "2 - null";

  private static final String EXPRESSION_MULTIPLY_NULL = "2 * null + 4 * 2";

  private static final String EXPRESSION_DIVIDE_NULL = "2 / null - 20 / 5";

  private static final String EXPRESSION_NULL = "null";

  private static final String EXPRESSION_ZERO_INF = "12 / 0";

  private static final String EXPRESSION_ZERO = "0 / 2";

  private Parser parser;

  @BeforeEach
  public void init() {
    parser = new Parser();
  }

  @Test
  public void parse() {
    ExpressionNode expression = parser.parse(EXPRESSION_PARSED);
    Object result = expression.getValue();
    assertEquals("0.12test na", result);
  }

  @Test
  public void parseNested() {
    ExpressionNode expression = parser.parse(EXPRESSION_NESTED);
    Object result = expression.getValue();
    assertEquals("7 объе", result);
  }

  @Test
  public void parseAddNull() {
    ExpressionNode expression = parser.parse(EXPRESSION_ADD_NULL);
    Object result = expression.getValue();
    assertEquals(2, result);
  }

  @Test
  public void parseMinNull() {
    ExpressionNode expression = parser.parse(EXPRESSION_MINUS_NULL);
    Object result = expression.getValue();
    assertEquals(2, result);
  }

  @Test
  public void parseMultiplyNull() {
    ExpressionNode expression = parser.parse(EXPRESSION_MULTIPLY_NULL);
    Object result = expression.getValue();
    assertEquals(8, result);
  }

  @Test
  public void parseDivideNull() {
    ExpressionNode expression = parser.parse(EXPRESSION_DIVIDE_NULL);
    Object result = expression.getValue();
    assertEquals(-4, result);
  }

  @Test
  public void parseNull() {
    ExpressionNode expression = parser.parse(EXPRESSION_NULL);
    Object result = expression.getValue();
    assertNull(result);
  }

  @Test
  public void parseZeroInf() {
    ExpressionNode expression = parser.parse(EXPRESSION_ZERO_INF);
    Object result = expression.getValue();
    assertTrue(((Double) result).isInfinite());
  }

  @Test
  public void parseZero() {
    ExpressionNode expression = parser.parse(EXPRESSION_ZERO);
    Object result = expression.getValue();
    assertEquals(0, result);
  }

  @Test
  public void parseUnexpectedEnd() {
    Executable executable = () -> parser.parse(EXPRESSION_UNEXPECTED_END);
    assertThrows(CalculatorUnexpectedEndException.class, executable);
  }

  @Test
  public void parseUnexpectedSymbol() {
    Executable executable = () -> parser.parse(EXPRESSION_UNEXPECTED_SYMBOL);
    assertThrows(CalculatorUnexpectedSymbolException.class, executable);
  }

  @Test
  public void parseUnexpectedBracketsEndSymbol() {
    Executable executable = () -> parser.parse(EXPRESSION_BRACKETS_END);
    assertThrows(CalculatorClosingException.class, executable);
  }
}