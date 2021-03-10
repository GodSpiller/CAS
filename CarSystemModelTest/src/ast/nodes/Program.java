package ast.nodes;

import ast.ASTNode;
import ast.ASTVisitor;
import token.Token;

public class Program extends ASTNode {

    public Program(Token token) {
        super(token);
    }

    @Override
    public  Object accept(ASTVisitor visitor) {
        return visitor.visit(this);
    }
}