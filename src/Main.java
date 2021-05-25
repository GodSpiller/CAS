import ast.BoundaryValue;
import modelhandler.GuardMaker;
import modelhandler.ModelHandler;

import java.util.ArrayList;
import java.util.HashMap;

public class Main {

    public static void main(String[] args) throws Exception {

        ModelHandler modelHandler = new ModelHandler();

        //the parameter of creatTestCode is the name of the uppaal template we wish to create tests for
        UnitTestFactory.makeUnitTestsCAS(modelHandler.createTestCode("Spec"));
        modelHandler.engine.disconnect();
    }
}
