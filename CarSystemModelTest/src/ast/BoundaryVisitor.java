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

import static java.lang.Integer.parseInt;

public class BoundaryVisitor implements ASTVisitor {

    public ArrayList<ArrayList<Integer>> superList = new ArrayList<>();

    @Override
    public Object visit(Program program) {
        for (ASTNode node : program.getChildren()) {
            node.accept(this);
        }
        return superList;
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
        ArrayList<Integer> boundaries = new ArrayList<>();

        if(eql.getRight().getType().equals(TokenType.NUMBER.toString())) {
            boundaries.add(parseInt(eql.getRight().getValue()));
            boundaries.add(parseInt(eql.getRight().getValue()));
            boundaries.add(parseInt(eql.getRight().getValue()) - 1);
            boundaries.add(parseInt(eql.getRight().getValue()) + 1);
        }
        if(eql.getLeft().getType().equals(TokenType.NUMBER.toString())) {
            boundaries.add(parseInt(eql.getLeft().getValue()));
            boundaries.add(parseInt(eql.getLeft().getValue()));
            boundaries.add(parseInt(eql.getLeft().getValue()) - 1);
            boundaries.add(parseInt(eql.getLeft().getValue()) + 1);
        }

        if (boundaries != null) {
            superList.add(boundaries);
        }

        return null;
    }

    @Override
    public Object visit(NEQ neq) {
        ArrayList<Integer> boundaries = new ArrayList<>();

        if (neq.getRight().getType().equals(TokenType.NUMBER.toString())) {
            boundaries.add(parseInt(neq.getRight().getValue()));
            boundaries.add(parseInt(neq.getRight().getValue()) + 1);
            boundaries.add(parseInt(neq.getRight().getValue()) - 1);
        }
        if (neq.getLeft().getType().equals(TokenType.NUMBER.toString())) {
            boundaries.add(parseInt(neq.getLeft().getValue()));
            boundaries.add(parseInt(neq.getLeft().getValue()) + 1);
            boundaries.add(parseInt(neq.getLeft().getValue()) - 1);

        }

        if (boundaries != null) {
            superList.add(boundaries);
        }
        return null;
    }

    @Override
    public Object visit(GTR gtr) {
        ArrayList<Integer> boundaries = new ArrayList<>();

        if (gtr.getRight().getType().equals(TokenType.NUMBER.toString())){ //Clock > Boundary
            boundaries.add(parseInt(gtr.getRight().getValue()));
            boundaries.add(parseInt(gtr.getRight().getValue()));
            boundaries.add(parseInt(gtr.getRight().getValue())+1);
            boundaries.add(parseInt(gtr.getRight().getValue())-1);
        }
        if (gtr.getLeft().getType().equals(TokenType.NUMBER.toString())){  //Boundary > Clock
            boundaries.add(parseInt(gtr.getLeft().getValue()));
            boundaries.add(parseInt(gtr.getLeft().getValue()));
            boundaries.add(parseInt(gtr.getLeft().getValue())-1);
            boundaries.add(parseInt(gtr.getLeft().getValue())+1);
        }

        if (boundaries != null) {
            superList.add(boundaries);
        }
        return null;
    }

    @Override
    public Object visit(GEQ geq) {
        ArrayList<Integer> boundaries = new ArrayList<>();

        if (geq.getRight().getType().equals(TokenType.NUMBER.toString())){  //Clock >= Boundary
            boundaries.add(parseInt(geq.getRight().getValue()));
            boundaries.add(parseInt(geq.getRight().getValue()));
            boundaries.add(parseInt(geq.getRight().getValue())-1);
            boundaries.add(parseInt(geq.getRight().getValue())+1);
        }
        if (geq.getLeft().getType().equals(TokenType.NUMBER.toString())){  //Boundary >= Clock
            boundaries.add(parseInt(geq.getLeft().getValue()));
            boundaries.add(parseInt(geq.getLeft().getValue()));
            boundaries.add(parseInt(geq.getLeft().getValue())-1);
            boundaries.add(parseInt(geq.getLeft().getValue())+1);

        }

        if (boundaries != null) {
            superList.add(boundaries);
        }
        return null;
    }

    @Override
    public Object visit(LSS lss) {
        ArrayList<Integer> boundaries = new ArrayList<>();

        if (lss.getLeft().getType().equals(TokenType.NUMBER.toString())){  //Clock < Boundary
            boundaries.add(parseInt(lss.getLeft().getValue()));
            boundaries.add(parseInt(lss.getLeft().getValue()));
            boundaries.add(parseInt(lss.getLeft().getValue())+1);
            boundaries.add(parseInt(lss.getLeft().getValue())-1);
        }
        if (lss.getRight().getType().equals(TokenType.NUMBER.toString())){  //Boundary < Clock
            boundaries.add(parseInt(lss.getRight().getValue()));
            boundaries.add(parseInt(lss.getRight().getValue()));
            boundaries.add(parseInt(lss.getRight().getValue())+1);
            boundaries.add(parseInt(lss.getRight().getValue())-1);
        }

        if (boundaries != null) {
            superList.add(boundaries);
        }
        return null;
    }

    @Override
    public Object visit(LEQ leq) {
        ArrayList<Integer> boundaries = new ArrayList<>();

        if (leq.getRight().getType().equals(TokenType.NUMBER.toString())){  //Clock <= Boundary
            boundaries.add(parseInt(leq.getRight().getValue()));
            boundaries.add(parseInt(leq.getRight().getValue()));
            boundaries.add(parseInt(leq.getRight().getValue())-1);
            boundaries.add(parseInt(leq.getRight().getValue())+1);
        }
        if (leq.getLeft().getType().equals(TokenType.NUMBER.toString())){  //Boundary <= Clock
            boundaries.add(parseInt(leq.getLeft().getValue()));
            boundaries.add(parseInt(leq.getLeft().getValue()));
            boundaries.add(parseInt(leq.getLeft().getValue())-1);
            boundaries.add(parseInt(leq.getLeft().getValue())+1);
        }

        if (boundaries != null) {
            superList.add(boundaries);
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
}


