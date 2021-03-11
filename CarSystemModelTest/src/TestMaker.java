import ast.BoundaryVisitor;
import lexer.Lexer;
import parser.Parser;
import ast.ASTNode;

import java.util.ArrayList;

public class TestMaker {


    public void superraplcer(StringBuilder sb) {
        Parser ps = new Parser(new Lexer(sb.toString()));
        ArrayList<String> guardList = new ArrayList<>();

        StringBuilder SUPER = sb;

        for (ArrayList<Integer> liste : ps.getSuperList()) {
            for (int i = 0; i < liste.size(); i++) {
                if(i != 0) {
                    SUPER = new StringBuilder();
                    SUPER.append(sb);
                    System.out.println(replace2(SUPER, liste.get(0).toString(), liste.get(i).toString()));
                }
            }
        }
    }

    public static String replace2(StringBuilder builder, String from, String to){
        int index = builder.indexOf(from);
        while (index != -1) {
            builder.replace(index, index + from.length(), to);
            index += to.length(); // Move to the end of the replacement
            index = builder.indexOf(from, index);
        }
        return builder.toString();
    }

}

