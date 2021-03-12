import ast.BoundaryVisitor;
import lexer.Lexer;
import parser.Parser;
import ast.ASTNode;

import java.util.ArrayList;

public class TestMaker {


    public ArrayList<String> guardMaker(StringBuilder sb) {
        Parser ps = new Parser(new Lexer(sb.toString()));
        ArrayList<String> guardList = new ArrayList<>();

        StringBuilder SUPER = sb;

        for (ArrayList<Integer> list : ps.getSuperList()) {
            for (int i = 0; i < list.size(); i++) {
                if(i != 0) {
                    SUPER = new StringBuilder();
                    SUPER.append(sb);
                    guardList.add(replace(SUPER, list.get(0).toString(), list.get(i).toString()));
                }
            }
        }

        return guardList;
    }

    public static String replace(StringBuilder builder, String from, String to){
        int index = builder.indexOf(from);
        while (index != -1) {
            builder.replace(index, index + from.length(), to);
            index += to.length();
            index = builder.indexOf(from, index);
        }
        return builder.toString();
    }

}

