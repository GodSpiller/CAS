package parser;

import ast.ASTNode;
import ast.BoundaryVisitor;
import ast.PrettyPrintVisitor;
import ast.nodes.BoundaryValue;
import ast.nodes.Identifier;
import ast.nodes.Program;
import ast.nodes.literals.NumberLit;
import ast.nodes.operators.arithmetic.Divide;
import ast.nodes.operators.arithmetic.Multiply;
import ast.nodes.operators.arithmetic.Plus;
import ast.nodes.operators.compare.*;
import ast.nodes.operators.logical.And;
import ast.nodes.operators.logical.Or;
import lexer.Lexer;
import token.TokenStream;
import token.TokenType;
import ast.PrettyPrintVisitor;

import java.util.ArrayList;
import java.util.HashMap;

public class Parser {

    private ArrayList<ArrayList<Integer>> superList = new ArrayList<>();

    private TokenStream tokenStream;

    private ASTNode program;

    public HashMap<Integer, ArrayList<BoundaryValue>> boundaryValues;

    public Parser(Lexer lexer){
        tokenStream = new TokenStream(lexer.getFilteredTokens());
        program = new Program(null);
        parse(program);
        boundaryValues = (HashMap)program.accept(new BoundaryVisitor());
    }

    public ASTNode getProgram() {
        return program;
    }

    private void parse(ASTNode parent){
        parent.addChild(boolExpr());
    }

    private ASTNode boolExpr() {
        ASTNode node;
        ASTNode compareExpr = compareExpr();

        if (tokenStream.peek(TokenType.OR)) {
            node = new Or(tokenStream.expect(TokenType.OR));
            node.addChild(compareExpr);
            node.addChild(compareExpr());
        }
        else {
            return compareExpr;
        }

        return node;
    }

    private ASTNode compareExpr() {
        ASTNode node;
        ASTNode logicOpExpr = logicOpExpr();

        if (tokenStream.peek(TokenType.AND)) {
            node = new And(tokenStream.expect(TokenType.AND));
            node.addChild(logicOpExpr);
            node.addChild(compareExpr());
        }
        else {
            return logicOpExpr;
        }

        return node;
    }

    private ASTNode logicOpExpr() {
        ASTNode node;
        ASTNode operatorExpr = operatorExpr();

        if (tokenStream.peek(TokenType.EQUAL)) {
            node = new EQL(tokenStream.expect(TokenType.EQUAL));
            node.addChild(operatorExpr);
            node.addChild(operatorExpr());
        }
        else if (tokenStream.peek(TokenType.NOT_EQUAL)) {
            node = new NEQ(tokenStream.expect(TokenType.NOT_EQUAL));
            node.addChild(operatorExpr);
            node.addChild(operatorExpr());
        }
        else if (tokenStream.peek(TokenType.GREATER_THAN)) {
            node = new GTR(tokenStream.expect(TokenType.GREATER_THAN));
            node.addChild(operatorExpr);
            node.addChild(operatorExpr());
        }
        else if (tokenStream.peek(TokenType.GT_EQUAL)) {
            node = new GEQ(tokenStream.expect(TokenType.GT_EQUAL));
            node.addChild(operatorExpr);
            node.addChild(operatorExpr());
        }
        else if (tokenStream.peek(TokenType.LESS_THAN)) {
            node = new LSS(tokenStream.expect(TokenType.LESS_THAN));
            node.addChild(operatorExpr);
            node.addChild(operatorExpr());
        }
        else if (tokenStream.peek(TokenType.LT_EQUAL)) {
            node = new LEQ(tokenStream.expect(TokenType.LT_EQUAL));
            node.addChild(operatorExpr);
            node.addChild(operatorExpr());
        }
        else {
            return operatorExpr;
        }

        return node;
    }

    private ASTNode operatorExpr() {
        ASTNode node;
        ASTNode timesExpr = timesExpr();

        if (tokenStream.peek(TokenType.PLUS)) {
            node = new Plus(tokenStream.expect(TokenType.PLUS));
            node.addChild(timesExpr);
            node.addChild(operatorExpr());
        }
        else if (tokenStream.peek(TokenType.MINUS)) {
            node = new Plus(tokenStream.expect(TokenType.MINUS));
            node.addChild(timesExpr);
            node.addChild(operatorExpr());
        }
        else {
            return timesExpr;
        }

        return node;
    }

    private ASTNode timesExpr() {
        ASTNode node;
        ASTNode operatorTail = operatorTail();

        if (tokenStream.peek(TokenType.TIMES)) {
            node = new Multiply(tokenStream.expect(TokenType.TIMES));
            node.addChild(operatorTail);
            node.addChild(timesExpr());
        }
        else if (tokenStream.peek(TokenType.DIVIDED)) {
            node = new Divide(tokenStream.expect(TokenType.DIVIDED));
            node.addChild(operatorTail);
            node.addChild(timesExpr());
        }
        else {
            return operatorTail;
        }

        return node;
    }

    private ASTNode operatorTail() {
        ASTNode node;

        if (tokenStream.peek(TokenType.NUMBER)) {
            node = new NumberLit(tokenStream.expect(TokenType.NUMBER));
        }
        else if (tokenStream.peek(TokenType.VARIABLE)) {
            node = new Identifier(tokenStream.expect(TokenType.VARIABLE));
        }
        else {
            tokenStream.expect(TokenType.LPAR);
            node = boolExpr();
            tokenStream.expect(TokenType.RPAR);
        }

        return node;
    }


}
