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

    public HashMap<Integer, ArrayList<BoundaryValue>> boundaryValues = new HashMap<>();

    @Override
    public Object visit(Program program) {
        for (ASTNode node : program.getChildren()) {
            node.accept(this);
        }
        return boundaryValues;
    }

    @Override
    public Object visit(Identifier identifier) {
        return identifier.getValue();
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

        if (eql.getRight().getType().equals(TokenType.NUMBER.toString())) {
            boundaryValues.put(eql.getRight().getToken().getIndex(),
                    makeValues(parseInt(eql.getRight().getValue()), eql.getLeft().getValue(), "==R", eql.getRight().getToken().getIndex()));
        }
        if (eql.getLeft().getType().equals(TokenType.NUMBER.toString())) {
            boundaryValues.put(eql.getLeft().getToken().getIndex(),
                    makeValues(parseInt(eql.getLeft().getValue()), eql.getRight().getValue(),"==L", eql.getLeft().getToken().getIndex()));
        }
        return null;
    }

    @Override
    public Object visit(NEQ neq) {

        if (neq.getRight().getType().equals(TokenType.NUMBER.toString())) {
            boundaryValues.put(neq.getRight().getToken().getIndex(),
                    makeValues(parseInt(neq.getRight().getValue()), neq.getLeft().getValue(), "!=R", neq.getRight().getToken().getIndex()));
        }
        if (neq.getLeft().getType().equals(TokenType.NUMBER.toString())) {
            boundaryValues.put(neq.getLeft().getToken().getIndex(),
                    makeValues(parseInt(neq.getLeft().getValue()), neq.getRight().getValue(),"!=L", neq.getLeft().getToken().getIndex()));
        }

        return null;
    }

    @Override
    public Object visit(GTR gtr) {

        if (gtr.getRight().getType().equals(TokenType.NUMBER.toString())){ //Clock > Boundary
            boundaryValues.put(gtr.getRight().getToken().getIndex(),
                    makeValues(parseInt(gtr.getRight().getValue()), gtr.getLeft().getValue(), ">R", gtr.getRight().getToken().getIndex()));
        }
        if (gtr.getLeft().getType().equals(TokenType.NUMBER.toString())){  //Boundary > Clock
            boundaryValues.put(gtr.getLeft().getToken().getIndex(),
                    makeValues(parseInt(gtr.getLeft().getValue()), gtr.getRight().getValue(), ">L", gtr.getLeft().getToken().getIndex()));
        }

        return null;
    }

    @Override
    public Object visit(GEQ geq) {

        if (geq.getRight().getType().equals(TokenType.NUMBER.toString())){  //Clock >= Boundary
            boundaryValues.put(geq.getRight().getToken().getIndex(),
                    makeValues(parseInt(geq.getRight().getValue()), geq.getLeft().getValue(),">=R", geq.getRight().getToken().getIndex()));
        }
        if (geq.getLeft().getType().equals(TokenType.NUMBER.toString())){  //Boundary >= Clock
            boundaryValues.put(geq.getLeft().getToken().getIndex(),
                    makeValues(parseInt(geq.getLeft().getValue()), geq.getRight().getValue(),">=L", geq.getLeft().getToken().getIndex()));
        }

        return null;
    }

    @Override
    public Object visit(LSS lss) {

        if (lss.getLeft().getType().equals(TokenType.NUMBER.toString())){  //Clock < Boundary
            boundaryValues.put(lss.getLeft().getToken().getIndex(),
                    makeValues(parseInt(lss.getLeft().getValue()), lss.getRight().getValue(),"<L", lss.getLeft().getToken().getIndex()));
        }
        if (lss.getRight().getType().equals(TokenType.NUMBER.toString())){  //Boundary < Clock
            boundaryValues.put(lss.getRight().getToken().getIndex(),
                    makeValues(parseInt(lss.getRight().getValue()), lss.getLeft().getValue(),"<R", lss.getRight().getToken().getIndex()));
        }

        return null;
    }

    @Override
    public Object visit(LEQ leq) {

        if (leq.getRight().getType().equals(TokenType.NUMBER.toString())){  //Clock <= Boundary
            boundaryValues.put(leq.getRight().getToken().getIndex(),
                    makeValues(parseInt(leq.getRight().getValue()), leq.getLeft().getValue(),"<=R", leq.getRight().getToken().getIndex()));
        }
        if (leq.getLeft().getType().equals(TokenType.NUMBER.toString())){  //Boundary <= Clock
            boundaryValues.put(leq.getLeft().getToken().getIndex(),
                    makeValues(parseInt(leq.getLeft().getValue()), leq.getRight().getValue(),"<=L", leq.getLeft().getToken().getIndex()));
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

    public ArrayList<BoundaryValue> makeValues(int x, String clock, String operator, int index) {
        ArrayList<BoundaryValue> temp = new ArrayList<>();

        switch (operator) {
            //clock is greater than constant
            case "<L", ">R" -> {
                temp.add(new BoundaryValue(x, true, clock, x + 1, index, x));
                temp.add(new BoundaryValue(x - 2, false, clock, x - 1, index, x));
                temp.add(new BoundaryValue(x - 1, false, clock, x, index, x));
            }
            //clock is less than constant
            case "<R", ">L" -> {
                temp.add(new BoundaryValue(x + 2, false, clock, x + 1, index, x));
                temp.add(new BoundaryValue(x, true, clock,  x - 1, index, x));
                temp.add(new BoundaryValue(x + 1, false, clock, x, index, x));
            }
            //clock is equal to or greater than constant
            case "<=L", ">=R" -> {
                temp.add(new BoundaryValue(x, true, clock, x, index, x));
                temp.add(new BoundaryValue(x, true, clock, x + 1, index, x));
                temp.add(new BoundaryValue(x - 1, false, clock, x - 1, index, x));
            }
            //clock is equal to or less than constant
            case "<=R", ">=L" -> {
                temp.add(new BoundaryValue(x, true, clock, x, index, x));
                temp.add(new BoundaryValue(x, true, clock, x - 1, index, x));
                temp.add(new BoundaryValue(x + 1, false, clock, x + 1, index, x));
            }
            //clock is equal to constant
            case "==L", "==R" -> {
                temp.add(new BoundaryValue(x, true, clock, x, index, x));
                temp.add(new BoundaryValue(x - 1, false, clock, x - 1, index, x));
                temp.add(new BoundaryValue(x + 1, false, clock, x + 1, index, x));
            }
            //clock is not equal to constant
            case "!=L", "!=R" -> {
                temp.add(new BoundaryValue(x + 1, false, clock, x, index, x));
                temp.add(new BoundaryValue(x, true, clock, x + 1, index, x));
                temp.add(new BoundaryValue(x, true, clock, x - 1, index, x));
            }
            default -> System.out.println("Error");
        }

        return temp;
    }
}


