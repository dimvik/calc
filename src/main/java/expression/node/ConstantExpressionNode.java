package expression.node;

import static expression.node.NodeType.CONSTANT_NODE;

public class ConstantExpressionNode implements ExpressionNode<Number> {

  private double value;

  public ConstantExpressionNode(String value) {
    this.value = Double.valueOf(value);
  }

  public Number getValue() {
    if ((value % 1) == 0) {
      return (int) value;
    } else {
      return value;
    }
  }

  public NodeType getType() {
    return CONSTANT_NODE;
  }

}
