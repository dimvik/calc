package exception;

import java.text.MessageFormat;

/**
 * Неизвестный символ
 */
public class CalculatorUnexpectedSymbolException extends RuntimeException {

  public CalculatorUnexpectedSymbolException(String symbol) {
    super(MessageFormat.format("Unexpected symbol - {0}", symbol));
  }

}
