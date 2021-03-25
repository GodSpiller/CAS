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
import token.TokenType;

import java.util.ArrayList;
import java.util.HashMap;

import static java.lang.Integer.parseInt;

public class BoundaryVisitor implements ASTVisitor {

    public HashMap<Integer, ArrayList<Integer>> boundaryValues = new HashMap<>();

    @Override
    public Object visit(Program program) {
        for (ASTNode node : program.getChildren()) {
            node.accept(this);
        }
        return boundaryValues;
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
        and.getLeft().accept(this);
        and.getRight().accept(this);

        return null;
    }

    @Override
    public Object visit(Or or) {
        or.getLeft().accept(this);
        or.getRight().accept(this);

        return null;
    }

    @Override
    public Object visit(EQL eql) {

        if(eql.getRight().getType().equals(TokenType.NUMBER.toString())) {
            boundaryValues.put(parseInt(eql.getRight().getValue()), makeValues(parseInt(eql.getRight().getValue())));
        }
        if(eql.getLeft().getType().equals(TokenType.NUMBER.toString())) {
            boundaryValues.put(parseInt(eql.getLeft().getValue()), makeValues(parseInt(eql.getLeft().getValue())));
        }

        return null;
    }

    @Override
    public Object visit(NEQ neq) {

        if (neq.getRight().getType().equals(TokenType.NUMBER.toString())) {
            boundaryValues.put(parseInt(neq.getRight().getValue()), makeValues(parseInt(neq.getRight().getValue())));
        }
        if (neq.getLeft().getType().equals(TokenType.NUMBER.toString())) {
            boundaryValues.put(parseInt(neq.getLeft().getValue()), makeValues(parseInt(neq.getLeft().getValue())));
        }

        return null;
    }

    @Override
    public Object visit(GTR gtr) {

        if (gtr.getRight().getType().equals(TokenType.NUMBER.toString())){ //Clock > Boundary
            boundaryValues.put(parseInt(gtr.getRight().getValue()), makeValues(parseInt(gtr.getRight().getValue())));
        }
        if (gtr.getLeft().getType().equals(TokenType.NUMBER.toString())){  //Boundary > Clock
            boundaryValues.put(parseInt(gtr.getLeft().getValue()), makeValues(parseInt(gtr.getLeft().getValue())));
        }

        return null;
    }

    @Override
    public Object visit(GEQ geq) {

        if (geq.getRight().getType().equals(TokenType.NUMBER.toString())){  //Clock >= Boundary
            boundaryValues.put(parseInt(geq.getRight().getValue()), makeValues(parseInt(geq.getRight().getValue())));
        }
        if (geq.getLeft().getType().equals(TokenType.NUMBER.toString())){  //Boundary >= Clock
            boundaryValues.put(parseInt(geq.getLeft().getValue()), makeValues(parseInt(geq.getLeft().getValue())));
        }

        return null;
    }

    @Override
    public Object visit(LSS lss) {

        if (lss.getLeft().getType().equals(TokenType.NUMBER.toString())){  //Clock < Boundary
            boundaryValues.put(parseInt(lss.getLeft().getValue()), makeValues(parseInt(lss.getLeft().getValue())));
        }
        if (lss.getRight().getType().equals(TokenType.NUMBER.toString())){  //Boundary < Clock
            boundaryValues.put(parseInt(lss.getRight().getValue()), makeValues(parseInt(lss.getRight().getValue())));
        }

        return null;
    }

    @Override
    public Object visit(LEQ leq) {

        if (leq.getRight().getType().equals(TokenType.NUMBER.toString())){  //Clock <= Boundary
            boundaryValues.put(parseInt(leq.getRight().getValue()), makeValues(parseInt(leq.getRight().getValue())));
        }
        if (leq.getLeft().getType().equals(TokenType.NUMBER.toString())){  //Boundary <= Clock
            boundaryValues.put(parseInt(leq.getLeft().getValue()), makeValues(parseInt(leq.getLeft().getValue())));
        }

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

    public ArrayList<Integer> makeValues(int x) {
        ArrayList<Integer> temp = new ArrayList<>();
        temp.add(x);
        temp.add(x + 1);
        temp.add(x - 1);

        return temp;
    }
}


