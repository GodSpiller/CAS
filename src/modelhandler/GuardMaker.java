package modelhandler;

import ast.BoundaryValue;
import lexer.Lexer;
import parser.Parser;

import java.util.ArrayList;
import java.util.HashMap;

public class GuardMaker {

    /*
     * An AST is created through the lexer and parser, which is then visited by the BoundaryVisitor.
     * parser.boundaryValues is a hashmap containing each boundary value of a guard.
     * New guards are created for the boundary values which exhibit "negative" behaviour
     *
     * @param guard: The guard of an edge in a UPPAAL model
     * @return a HashMap containing the boundary values of a guard
     */
    public HashMap<Integer, ArrayList<BoundaryValue>> makeGuards(String guard) {
        Parser parser = new Parser(new Lexer(guard));

        for (Integer i : parser.boundaryValues.keySet()) {
            for (BoundaryValue boundaryValue : parser.boundaryValues.get(i)) {
                if(!boundaryValue.getValidity()) {
                    boundaryValue.setGuard(createNewGuard(guard, boundaryValue));
                }
            }
        }

        return parser.boundaryValues;
    }


    /*
     *
     * Splits a string in two, before a constant and after. These string are then combined with a new constant value.
     *
     * @param guard: The guard of an edge in a UPPAAL model
     * @param boundaryValue: boundaryValue object, which contains information about the placement of the constant in the guard string.
     *
     * @return the string of the new guard
     */
    private String createNewGuard(String guard, BoundaryValue boundaryValue) {

        String sub1 = guard.substring(0, boundaryValue.getIndexStart());
        String sub2 = guard.substring(boundaryValue.getIndexEnd());

        return sub1 + boundaryValue.getValue() + sub2;
    }
}

