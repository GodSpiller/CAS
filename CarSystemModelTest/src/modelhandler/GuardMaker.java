package modelhandler;

import ast.BoundaryValue;
import lexer.Lexer;
import parser.Parser;

import java.util.ArrayList;
import java.util.HashMap;

public class GuardMaker {

    /*
     * calculates boundary values of a guard, and returns a new guard for each new value
     *
     * @param sb: StringBuilder to create new guards of
     * @return a HashMap containing the boundary values of a guard
     */
    public HashMap<Integer, ArrayList<BoundaryValue>> makeGuards(String guard) {
        Parser parser = new Parser(new Lexer(guard));

        for (Integer i : parser.boundaryValues.keySet()) {
            for (BoundaryValue boundaryValue : parser.boundaryValues.get(i)) {
                boundaryValue.setGuard(newGuard(guard, boundaryValue));
                //boundaryValue.setGuard(replace(guard, i.toString(), String.valueOf(boundaryValue.getValue())));
            }
        }

        return parser.boundaryValues;
    }

    /*
     * Replaces a string with another in a StringBuilder
     *
     * @param builder: StringBuilder to be modified
     * @param from: the string to be replaced
     * @param to: the string that replaces the 'from' string
     */
    private String replace(String guard, String from, String to){
        StringBuilder builder = new StringBuilder(guard);
        int index = builder.indexOf(from);
        while (index != -1) {
            builder.replace(index, index + from.length(), to);
            index += to.length();
            index = builder.indexOf(from, index);
        }
        return builder.toString();
    }

    private String newGuard(String guard, BoundaryValue boundaryValue) {
        String sub1 = guard.substring(0, boundaryValue.getIndexStart());
        String sub2 = guard.substring(boundaryValue.getIndexEnd());

        return sub1 + boundaryValue.getValue() + sub2;
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

