
package ast.nodes;

import ast.ASTNode;
import ast.ASTVisitor;
import token.Token;

public class Identifier extends ASTNode {

    public Identifier(Token token) {
        super(token);
    }

    @Override
    public Object accept(ASTVisitor visitor) {
        return visitor.visit(this);
    }
}