import modelhandler.ModelHandler;

public class Main {

    public static void main(String[] args) throws Exception {

        ModelHandler modelHandler = new ModelHandler();

        UnitTestFactory.makeUnitTests(modelHandler.createTestCode());
        modelHandler.engine.disconnect();

    }
}
