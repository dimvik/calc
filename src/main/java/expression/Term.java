package expression;

import expression.node.ExpressionNode;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Term {

  private boolean positive;

  private ExpressionNode expression;

}
