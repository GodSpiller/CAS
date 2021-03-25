import ast.BoundaryVisitor;
import lexer.Lexer;
import parser.Parser;
import ast.ASTNode;

import java.util.ArrayList;

public class GuardMaker {

    public ArrayList<String> makeGuards(StringBuilder sb) {
        Parser parser = new Parser(new Lexer(sb.toString()));
        ArrayList<String> guardList = new ArrayList<>();

        StringBuilder guard;

        for (Integer i : parser.boundaryValues.keySet()) {
            for (Integer x : parser.boundaryValues.get(i)) {
                guard = new StringBuilder();
                guard.append(sb);
                guardList.add(replace(guard, i.toString(), x.toString()));
            }
        }

        return removeDuplicates(guardList);
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

    public ArrayList<String> removeDuplicates(ArrayList<String> guardList) {
        ArrayList<String> newList = new ArrayList<>();

        for (String s : guardList) {
            if (!newList.contains(s)) {
                newList.add(s);
            }
        }

        return newList;
    }
}

