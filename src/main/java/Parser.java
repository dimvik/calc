import static expression.node.NodeType.ADDITION_NODE;
import static expression.node.NodeType.MULTIPLICATION_NODE;
import static token.TokenType.CLOSE_BRACKET;
import static token.TokenType.EPSILON;
import static token.TokenType.MULTDIV;
import static token.TokenType.OPEN_BRACKET;
import static token.TokenType.PLUSMINUS;

import exception.CalculatorClosingException;
import exception.CalculatorUnexpectedEndException;
import exception.CalculatorUnexpectedSymbolException;
import expression.node.AdditionExpressionNode;
import expression.node.ConstantExpressionNode;
import expression.node.ExpressionNode;
import expression.node.MultiplicationExpressionNode;
import expression.node.NullExpressionNode;
import expression.node.StringExpressionNode;
import java.util.LinkedList;
import token.Token;
import token.Tokenizer;

class Parser {

  private LinkedList<Token> tokens;

  private Token lookahead;

  ExpressionNode parse(String expression) {
    Tokenizer tokenizer = Tokenizer.createExpressionTokenizer();
    tokenizer.tokenize(expression);
    LinkedList<Token> tokens = tokenizer.getTokens();
    return parse(tokens);
  }

  private ExpressionNode parse(LinkedList<Token> tokens) {
    this.tokens = new LinkedList<>(tokens);
    lookahead = this.tokens.getFirst();

    return expression();
  }

  private ExpressionNode expression() {
    ExpressionNode expr = signedTerm();
    expr = sumOp(expr);
    return expr;
  }

  private ExpressionNode sumOp(ExpressionNode expr) {
    if (lookahead.type == PLUSMINUS) {
      AdditionExpressionNode sum;
      if (expr.getType() == ADDITION_NODE) {
        sum = (AdditionExpressionNode) expr;
      } else {
        sum = new AdditionExpressionNode(expr, true);
      }

      boolean positive = lookahead.sequence.equals("+");
      nextToken();
      ExpressionNode term = term();
      sum.add(term, positive);

      return sumOp(sum);
    }

    return expr;
  }

  private ExpressionNode signedTerm() {
    if (lookahead.type == PLUSMINUS) {
      boolean positive = lookahead.sequence.equals("+");
      nextToken();
      ExpressionNode term = term();
      return positive ? term : new AdditionExpressionNode(term, false);
    }

    return term();
  }

  private ExpressionNode term() {
    ExpressionNode arg = argument();
    return termOp(arg);
  }

  private ExpressionNode termOp(ExpressionNode expression) {
    if (lookahead.type == MULTDIV) {
      MultiplicationExpressionNode prod;

      if (expression.getType() == MULTIPLICATION_NODE) {
        prod = (MultiplicationExpressionNode) expression;
      } else {
        prod = new MultiplicationExpressionNode(expression, true);
      }

      boolean positive = lookahead.sequence.equals("*");
      nextToken();
      ExpressionNode f = signedFactor();
      prod.add(f, positive);

      return termOp(prod);
    }

    return expression;
  }

  private ExpressionNode signedFactor() {
    if (lookahead.type == PLUSMINUS) {
      boolean positive = lookahead.sequence.equals("+");
      nextToken();
      ExpressionNode argument = argument();
      return positive ? argument : new AdditionExpressionNode(argument, false);
    }

    return argument();
  }

  private ExpressionNode argument() {
    if (lookahead.type == OPEN_BRACKET) {
      nextToken();
      ExpressionNode expr = expression();
      if (lookahead.type != CLOSE_BRACKET) {
        throw new CalculatorClosingException();
      }
      nextToken();
      return expr;
    }

    return value();
  }

  private ExpressionNode value() {
    switch (lookahead.type) {
      case NUMBER: {
        ExpressionNode expr = new ConstantExpressionNode(lookahead.sequence);
        nextToken();
        return expr;
      }
      case STRING: {
        ExpressionNode expr = new StringExpressionNode(lookahead.sequence);
        nextToken();
        return expr;
      }
      case NULL: {
        ExpressionNode expr = new NullExpressionNode();
        nextToken();
        return expr;
      }
      case EPSILON:
        throw new CalculatorUnexpectedEndException();
      default:
        throw new CalculatorUnexpectedSymbolException(lookahead.sequence);
    }
  }

  private void nextToken() {
    tokens.pop();
    lookahead = tokens.isEmpty() ? new Token(EPSILON, "") : tokens.getFirst();
  }
}
