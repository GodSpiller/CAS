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

public class PrettyPrintVisitor implements ASTVisitor{
    @Override
    public Object visit(Program program) {
        for (ASTNode node : program.getChildren())
            System.out.println(node.accept(this));

        return null;
    }

    @Override
    public Object visit(Identifier identifier) {
        return identifier.getValue();
    }

    @Override
    public Object visit(NumberLit integer) {
        return integer.getValue();
    }

    @Override
    public Object visit(And and) {
        var left = and.getLeft().accept(this);
        var right = and.getRight().accept(this);

        return String.format("(%s AND %s)", left, right);
    }

    @Override
    public Object visit(Or or) {
        var left = or.getLeft().accept(this);
        var right = or.getRight().accept(this);

        return String.format("(%s OR %s)", left, right);
    }

    @Override
    public Object visit(EQL eql) {
        var left = eql.getLeft().accept(this);
        var right = eql.getRight().accept(this);

        return String.format("(%s = %s)", left, right);
    }

    @Override
    public Object visit(NEQ neq) {
        var left = neq.getLeft().accept(this);
        var right = neq.getRight().accept(this);

        return String.format("(%s != %s)", left, right);
    }

    @Override
    public Object visit(GTR gtr) {
        var left = gtr.getLeft().accept(this);
        var right = gtr.getRight().accept(this);

        return String.format("(%s > %s)", left, right);
    }

    @Override
    public Object visit(GEQ geq) {
        var left = geq.getLeft().accept(this);
        var right = geq.getRight().accept(this);

        return String.format("(%s >= %s)", left, right);
    }

    @Override
    public Object visit(LSS lss) {
        var left = lss.getLeft().accept(this);
        var right = lss.getRight().accept(this);

        return String.format("(%s < %s)", left, right);
    }

    @Override
    public Object visit(LEQ leq) {
        var left = leq.getLeft().accept(this);
        var right = leq.getRight().accept(this);

        return String.format("(%s <= %s)", left, right);
    }

    @Override
    public Object visit(Plus plus) {
        var left = plus.getLeft().accept(this);
        var right = plus.getRight().accept(this);

        return String.format("(%s + %s)", left, right);
    }

    @Override
    public Object visit(Minus minus) {
        var left = minus.getLeft().accept(this);
        var right = minus.getRight().accept(this);

        return String.format("(%s - %s)", left, right);
    }

    @Override
    public Object visit(Divide divide) {
        var left = divide.getLeft().accept(this);
        var right = divide.getRight().accept(this);

        return String.format("(%s / %s)", left, right);
    }

    @Override
    public Object visit(Multiply multiply) {
        var left = multiply.getLeft().accept(this);
        var right = multiply.getRight().accept(this);

        return String.format("(%s * %s)", left, right);
    }
}
