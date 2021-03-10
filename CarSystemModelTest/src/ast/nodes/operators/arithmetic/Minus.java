package ast.nodes.operators.arithmetic;

import ast.ASTNode;
import ast.ASTVisitor;
import token.Token;

public class Minus extends ASTNode {

    public Minus(Token token) {
        super(token);
    }

    @Override
    public Object accept(ASTVisitor visitor) {
        return visitor.visit(this);
    }
}