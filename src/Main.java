import modelhandler.ModelHandler;

public class Main {

    public static void main(String[] args) throws Exception {


        ModelHandler modelHandler = new ModelHandler();

        UnitTestFactory.makeUnitTests1(modelHandler.createTestCode("Spec"));
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
