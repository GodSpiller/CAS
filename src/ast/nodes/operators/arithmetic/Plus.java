package ast.nodes.operators.arithmetic;

import ast.ASTNode;
import ast.ASTVisitor;
import token.Token;

public class Plus extends ASTNode {

    public Plus(Token token) {
        super(token);
    }

    @Override
    public Object accept(ASTVisitor visitor) {
        return visitor.visit(this);
    }
}