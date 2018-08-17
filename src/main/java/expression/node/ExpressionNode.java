package expression.node;

public interface ExpressionNode<T> {

  NodeType getType();

  T getValue();

}
