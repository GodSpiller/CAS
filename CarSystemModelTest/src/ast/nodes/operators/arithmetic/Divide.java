
package ast.nodes.operators.arithmetic;

import ast.*;
import token.Token;

public class Divide extends ASTNode {

    public Divide(Token token) {
        super(token);
    }

    @Override
    public Object accept(ASTVisitor visitor) {
        return visitor.visit(this);
    }
}