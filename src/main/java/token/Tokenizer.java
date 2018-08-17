package token;

import static token.TokenType.CLOSE_BRACKET;
import static token.TokenType.MULTDIV;
import static token.TokenType.NULL;
import static token.TokenType.NUMBER;
import static token.TokenType.OPEN_BRACKET;
import static token.TokenType.PLUSMINUS;
import static token.TokenType.STRING;

import exception.CalculatorUnexpectedSymbolException;
import java.util.LinkedList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import lombok.Getter;

public class Tokenizer {

  private LinkedList<TokenInfo> tokenInfos;

  @Getter
  private LinkedList<Token> tokens;

  private Tokenizer() {
    tokenInfos = new LinkedList<>();
    tokens = new LinkedList<>();
  }

  public static Tokenizer createExpressionTokenizer() {
    Tokenizer tokenizer = new Tokenizer();

    tokenizer.add("[+-]", PLUSMINUS);
    tokenizer.add("[*/]", MULTDIV);
    tokenizer.add("\\(", OPEN_BRACKET);
    tokenizer.add("\\)", CLOSE_BRACKET);
    tokenizer.add("\\b[0-9]*\\.?[0-9]+([eE][-+]?[0-9]+)?\\b", NUMBER);
    tokenizer.add("\\\"(.*?)\\\"", STRING);
    tokenizer.add("null", NULL);

    return tokenizer;
  }

  public void tokenize(String expr) {
    String exprTrim = expr.trim();
    tokens.clear();
    while (!exprTrim.equals("")) {
      boolean match = false;
      for (TokenInfo info : tokenInfos) {
        Matcher m = info.getRegex().matcher(exprTrim);
        if (m.find()) {
          match = true;
          String tok = m.group().trim();
          exprTrim = m.replaceFirst("").trim();
          if (info.getType() == STRING) {
            tok = tok.substring(1, tok.length() - 1);
          }
          tokens.add(new Token(info.getType(), tok));
          break;
        }
      }
      if (!match) {
        throw new CalculatorUnexpectedSymbolException(exprTrim);
      }
    }
  }

  private void add(String regex, TokenType type) {
    tokenInfos.add(new TokenInfo(Pattern.compile("^(" + regex + ")"), type));
  }

}
