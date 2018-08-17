package expression.node;

import expression.Term;
import java.util.ArrayList;

public abstract class SequenceExpressionNode<T> implements ExpressionNode<T> {

  ArrayList<Term> terms;

  SequenceExpressionNode(ExpressionNode a, boolean positive) {
    this.terms = new ArrayList<>();
    this.terms.add(new Term(positive, a));
  }

  public void add(ExpressionNode node, boolean positive) {
    this.terms.add(new Term(positive, node));
  }

}
