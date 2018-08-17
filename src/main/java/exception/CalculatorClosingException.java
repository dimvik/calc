package exception;

/**
 * Ожидалось закрытие скобок
 */
public class CalculatorClosingException extends RuntimeException {

  public CalculatorClosingException() {
    super("Closing brackets expected");
  }

}
