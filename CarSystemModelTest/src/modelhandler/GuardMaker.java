package modelhandler;

import ast.BoundaryVisitor;
import ast.nodes.BoundaryValue;
import lexer.Lexer;
import parser.Parser;
import ast.ASTNode;

import java.util.ArrayList;
import java.util.HashMap;

public class GuardMaker {

    public HashMap<Integer, ArrayList<BoundaryValue>> makeGuards(StringBuilder sb) {
        Parser parser = new Parser(new Lexer(sb.toString()));
        StringBuilder guard;

        for (Integer i : parser.boundaryValues.keySet()) {
            for (BoundaryValue boundaryValue : parser.boundaryValues.get(i)) {
                guard = new StringBuilder();
                guard.append(sb);
                boundaryValue.setGuard(replace(guard, i.toString(), String.valueOf(boundaryValue.getValue())));
            }
        }

        return parser.boundaryValues;
    }

    public String replace(StringBuilder builder, String from, String to){
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

