package modelhandler;

import ast.BoundaryVisitor;
import ast.nodes.BoundaryValue;
import lexer.Lexer;
import parser.Parser;
import ast.ASTNode;

import java.util.ArrayList;
import java.util.HashMap;

public class GuardMaker {

    /*
     * calculates boundary values of a guard, and returns a new guard for each new value
     *
     * @param sb: StringBuilder to create new guards of
     * @return a HashMap containing the boundary values of a guard
     */
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

    /*
     * Replaces a string with another in a StrinBuilder
     *
     * @param builder: StringBuilder to be modified
     * @param from: the string to be replaced
     * @param to: the string that replaces the 'from' string
     */
    private String replace(StringBuilder builder, String from, String to){
        int index = builder.indexOf(from);
        while (index != -1) {
            builder.replace(index, index + from.length(), to);
            index += to.length();
            index = builder.indexOf(from, index);
        }
        return builder.toString();
    }

    private ArrayList<String> removeDuplicates(ArrayList<String> guardList) {
        ArrayList<String> newList = new ArrayList<>();

        for (String s : guardList) {
            if (!newList.contains(s)) {
                newList.add(s);
            }
        }

        return newList;
    }
}

