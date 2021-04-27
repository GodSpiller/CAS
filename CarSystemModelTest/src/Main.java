import ast.BoundaryValue;
import modelhandler.GuardMaker;
import modelhandler.ModelHandler;

import java.util.ArrayList;
import java.util.HashMap;

public class Main {

    public static void main(String[] args) throws Exception {


        ModelHandler modelHandler = new ModelHandler();

        UnitTestFactory.makeUnitTests(modelHandler.createTestCode("Spec"));
        modelHandler.engine.disconnect();


        /*GuardMaker gm = new GuardMaker();

        HashMap<Integer, ArrayList<BoundaryValue>> guards = gm.makeGuards("a == x + 10");

        for (Integer i : guards.keySet()) {
            for (BoundaryValue bv : guards.get(i)) {
                System.out.println(bv.getGuard() + " " + bv.getValidity());
            }
        }
*/

    }
}
