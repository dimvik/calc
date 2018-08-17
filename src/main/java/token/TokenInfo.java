package token;

import java.util.regex.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
class TokenInfo {

  private Pattern regex;

  private TokenType type;

}
