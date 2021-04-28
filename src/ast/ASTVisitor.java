package ast;

import ast.nodes.*;
import ast.nodes.literals.*;
import ast.nodes.operators.arithmetic.*;
import ast.nodes.operators.compare.*;
import ast.nodes.operators.logical.*;

public interface ASTVisitor {
    Object visit(Program program);
    Object visit(Identifier identifier);
    Object visit(NumberLit integer);
    Object visit(And and);
    Object visit(Or or);
    Object visit(EQL eql);
    Object visit(NEQ neq);
    Object visit(GTR gtr);
    Object visit(GEQ geq);
    Object visit(LSS lss);
    Object visit(LEQ leq);
    Object visit(Plus plus);
    Object visit(Minus minus);
    Object visit(Divide divide);
    Object visit(Multiply multiply);
}

