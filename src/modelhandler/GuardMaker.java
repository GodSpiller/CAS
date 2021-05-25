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
                boundaryValue.setGuard(createNewGuard(guard, boundaryValue));
            }
        }

        return parser.boundaryValues;
    }

    private String createNewGuard(String guard, BoundaryValue boundaryValue) {

        String sub1 = guard.substring(0, boundaryValue.getIndexStart());
        String sub2 = guard.substring(boundaryValue.getIndexEnd());

        return sub1 + boundaryValue.getValue() + sub2;
    }
}

