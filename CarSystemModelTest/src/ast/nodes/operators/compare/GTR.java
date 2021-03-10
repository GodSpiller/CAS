package ast.nodes.operators.compare;

import ast.ASTNode;
import ast.ASTVisitor;
import token.Token;

public class GTR extends ASTNode {

    public GTR(Token token) {
        super(token);
    }

    @Override
    public Object accept(ASTVisitor visitor) {
        return visitor.visit(this);
    }
}