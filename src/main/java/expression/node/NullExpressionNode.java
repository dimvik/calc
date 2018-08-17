package expression.node;

import static expression.node.NodeType.NULL_NODE;

import lombok.Getter;

public class NullExpressionNode implements ExpressionNode<Object> {

  @Getter
  private Object value;

  public NullExpressionNode() {
    this.value = null;
  }

  public NodeType getType() {
    return NULL_NODE;
  }

}
