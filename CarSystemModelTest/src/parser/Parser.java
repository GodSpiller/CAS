package parser;

import ast.ASTNode;
import ast.nodes.Program;
import lexer.Lexer;
import token.TokenStream;

public class Parser {

    private TokenStream tokenStream;

    private ASTNode program;

    public Parser(Lexer lexer){
        tokenStream = new TokenStream(lexer.getFilteredTokens());
        program = new Program(null);
        parse(program);
    }

    private void parse(ASTNode parent){

    }

    private ASTNode boolExpr() {
        ASTNode node;
        ASTNode 
    }


}
