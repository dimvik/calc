package token;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import exception.CalculatorUnexpectedSymbolException;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

class TokenizerTest {

  private static final String EXPRESSION = "(entity_type.id + 4) / 50 + (entity_type.name - \"me\") + ((3.14 - 0.64) * 2.2)";

  private static final String EXPRESSION_PARSED = "(1.5 + 4.5) / 50 + (\"test name\" - \"me\")";

  @Test
  public void tokenize() {
    Tokenizer tokenizer = Tokenizer.createExpressionTokenizer();
    tokenizer.tokenize(EXPRESSION_PARSED);
    List<Token> tokens = tokenizer.getTokens();
    assertEquals(tokens.size(), 13);
  }

  @Test
  public void tokenizeUnexpectedSymbol() {
    Tokenizer tokenizer = Tokenizer.createExpressionTokenizer();

    Executable executable = () -> tokenizer.tokenize(EXPRESSION);
    assertThrows(CalculatorUnexpectedSymbolException.class, executable);

  }

}