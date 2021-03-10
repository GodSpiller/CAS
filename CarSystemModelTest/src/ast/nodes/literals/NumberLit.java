package ast.nodes.literals;

import ast.ASTNode;
import ast.ASTVisitor;
import token.Token;
import token.TokenType;

public class NumberLit extends ASTNode {

    public NumberLit(Token token) {
        super(token);
    }

    @Override
    public Object accept(ASTVisitor visitor) {
        return visitor.visit(this);
    }
}