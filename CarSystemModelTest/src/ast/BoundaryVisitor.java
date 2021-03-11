package ast;

import ast.nodes.Identifier;
import ast.nodes.Program;
import ast.nodes.literals.NumberLit;
import ast.nodes.operators.arithmetic.Divide;
import ast.nodes.operators.arithmetic.Minus;
import ast.nodes.operators.arithmetic.Multiply;
import ast.nodes.operators.arithmetic.Plus;
import ast.nodes.operators.compare.*;
import ast.nodes.operators.logical.And;
import ast.nodes.operators.logical.Or;
import com.uppaal.model.core2.Visitor;
import token.TokenType;

import java.lang.reflect.Array;

import static java.lang.Integer.parseInt;

public class BoundaryVisitor implements ASTVisitor {

    @Override
    public Object visit(Program program) {
        return null;
    }

    @Override
    public Object visit(Identifier identifier) {
        return null;
    }

    @Override
    public Object visit(NumberLit integer) {

        return parseInt(integer.getValue());
    }

    @Override
    public Object visit(And and) {
        return null;
    }

    @Override
    public Object visit(Or or) {
        return null;
    }

    @Override
    public Object visit(EQL eql) {
        int[] boundaries = new int[2];
        for (ASTNode node : eql.getChildren()){
            if(node.getRight().getType().equals(TokenType.NUMBER)) {
                boundaries[0] = parseInt(node.getRight().getValue()) - 1;
                boundaries[1] = parseInt(node.getRight().getValue()) + 1;
            }
        }
        return boundaries;
    }

    @Override
    public Object visit(NEQ neq) {
        int[] boundaries = new int[2];
        for (ASTNode node : neq.getChildren()){
            if (node.getRight().getType().equals(TokenType.NUMBER)) {
                boundaries[0] = parseInt(node.getRight().getValue()) - 1;
                boundaries[1] = parseInt(node.getRight().getValue()) + 1;
            }
        }
        return boundaries;
    }

    @Override
    public Object visit(GTR gtr) {
        int[] boundaries = new int[0];
        for (ASTNode node : gtr.getChildren()){
            if (node.getRight().getType().equals(TokenType.NUMBER)){
                boundaries[0] = parseInt(node.getRight().getValue())-1;
                boundaries[1] = parseInt(node.getRight().getValue());
                boundaries[2] = parseInt(node.getRight().getValue())+1;
            }
            if (node.getLeft().getType().equals(TokenType.NUMBER)){
                boundaries[0] = parseInt(node.getRight().getValue())+1;
                boundaries[1] = parseInt(node.getRight().getValue());
                boundaries[2] = parseInt(node.getRight().getValue())-1;
            }
        }
        return boundaries;
    }

    @Override
    public Object visit(GEQ geq) {
        return null;
    }

    @Override
    public Object visit(LSS lss) {
        return null;
    }

    @Override
    public Object visit(LEQ leq) {
        return null;
    }

    @Override
    public Object visit(Plus plus) {
        return null;
    }

    @Override
    public Object visit(Minus minus) {
        return null;
    }

    @Override
    public Object visit(Divide divide) {
        return null;
    }

    @Override
    public Object visit(Multiply multiply) {
        return null;
    }
}
