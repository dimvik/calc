
package expression.node;

import static expression.node.NodeType.MULTIPLICATION_NODE;
import static expression.node.NodeType.NULL_NODE;

import expression.Term;

public class MultiplicationExpressionNode extends SequenceExpressionNode<Number> {

  public MultiplicationExpressionNode(ExpressionNode a, boolean positive) {
    super(a, positive);
  }

  public NodeType getType() {
    return MULTIPLICATION_NODE;
  }

  public Number getValue() {
    double prod = 1.0;
    for (Term term : terms) {
      if (term.getExpression().getType() == NULL_NODE) {
        return null;
      }
      Object termValue = term.getExpression().getValue();
      if (termValue == null) {
        continue;
      }
      Double value;
      if (termValue instanceof Integer) {
        value = ((Integer) termValue).doubleValue();
      } else {
        value = (Double) termValue;
      }
      if (term.isPositive()) {
        prod *= value;
      } else {
        prod /= value;
      }
    }
    if ((prod % 1) == 0) {
      return (int) prod;
    } else {
      return Math.round(prod * 100.0) / 100.0;
    }
  }

}
