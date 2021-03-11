import ast.BoundaryVisitor;
import lexer.Lexer;
import parser.Parser;
import ast.ASTNode;

public class TestMaker {

    StringBuilder sb = new StringBuilder();

    Parser parser = new Parser(new Lexer(sb.toString()));


    public static void replace(StringBuilder builder, String from, String to){
        int index = builder.indexOf(from);
        while (index != -1) {
            builder.replace(index, index + from.length(), to);
            index += to.length(); // Move to the end of the replacement
            index = builder.indexOf(from, index);
        }
    }

}

