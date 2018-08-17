package exception;

/**
 * Неожиданный конец формулы
 */
public class CalculatorUnexpectedEndException extends RuntimeException {

  public CalculatorUnexpectedEndException() {
    super("Unexpected end of formula");
  }

}
