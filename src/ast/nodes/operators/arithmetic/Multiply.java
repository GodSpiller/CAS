package ast.nodes.operators.arithmetic;

import ast.ASTNode;
import ast.ASTVisitor;
import token.Token;

public class Multiply extends ASTNode {

    public Multiply(Token token) {
        super(token);
    }

    @Override
    public Object accept(ASTVisitor visitor) {
        return visitor.visit(this);
    }
}