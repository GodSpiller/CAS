package ast.nodes.operators.logical;

import ast.ASTNode;
import ast.ASTVisitor;
import token.Token;

public class Or extends ASTNode {

    public Or(Token token) {
        super(token);
    }

    @Override
    public Object accept(ASTVisitor visitor) {
        return visitor.visit(this);
    }
}