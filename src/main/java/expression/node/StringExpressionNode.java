package expression.node;

import static expression.node.NodeType.STRING_NODE;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public class StringExpressionNode implements ExpressionNode<String> {

  @Getter
  private String value;

  public NodeType getType() {
    return STRING_NODE;
  }

}
