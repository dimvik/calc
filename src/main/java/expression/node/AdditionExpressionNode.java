package expression.node;

import static expression.node.NodeType.ADDITION_NODE;
import static expression.node.NodeType.NULL_NODE;

import expression.AdditionResult;
import expression.Term;

public class AdditionExpressionNode extends SequenceExpressionNode<Object> {

  public AdditionExpressionNode(ExpressionNode node, boolean positive) {
    super(node, positive);
  }

  public NodeType getType() {
    return ADDITION_NODE;
  }

  public Object getValue() {
    AdditionResult result = new AdditionResult();
    for (Term term : terms) {
      if (term.getExpression().getType() == NULL_NODE) {
        continue;
      }
      Object value = term.getExpression().getValue();
      if (value == null) {
        continue;
      }
      if (term.isPositive()) {
        result.addition(value);
      } else {
        result.subtraction(value);
      }
    }
    return result.getResult();
  }

}