import ast.nodes.BoundaryValue;
import modelhandler.GuardMaker;
import modelhandler.ModelHandler;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;

public class Main {

    public static void main(String[] args) throws Exception {

        ModelHandler modelHandler = new ModelHandler();

        GuardMaker guardMaker = new GuardMaker();
        HashMap<Integer, ArrayList<BoundaryValue>> hm = new HashMap<>();

        hm = guardMaker.makeGuards(new StringBuilder("15 < c && c < 40"));

        modelHandler.createTestCode();

        for (Integer i : hm.keySet()) {
            for (BoundaryValue boundaryValue : hm.get(i)) {
                //System.out.format("%s : %s \n",boundaryValue.getGuard(), boundaryValue.getValidity());
            }

        }
    }

    public static void makeUnitTests(ArrayList<StringBuilder> testCases) throws IOException {
        File file = new File("CarSystemModelTest\\test\\CarSystemTests.java");
        int numberOfTestCases = testCases.size();
        StringBuilder sb = new StringBuilder();
        FileWriter writer = new FileWriter(file);
        Path path;
        String filePath;
        sb.append("import org.junit.jupiter.api.BeforeEach;\n");
        sb.append("import org.junit.jupiter.api.Test;\n");
        sb.append("import static org.junit.jupiter.api.Assertions.*;\n" + "\n");
        sb.append("class CarSystemTests{\n\n");
        sb.append("@BeforeEach\n void setup(){\n carSystem.CarSystem cs = new carSystem.CarSystem();\n}\n");
        for (int i = 0; i < numberOfTestCases; i++){
            sb.append("\n@Test\nvoid testcase" +  new DecimalFormat("000").format(i) + "(){\n");
            sb.append("carSystem.CarSystem cs = new carSystem.CarSystem();\n");
            sb.append(testCases.get(i));
            sb.append("}\n");

        }
    }

    public static void makeUnitTest(StringBuilder testCode) throws Exception {
        ModelHandler modelHandler = new ModelHandler();
        StringBuilder sb = new StringBuilder();
        File file = new File("CarSystemModelTest\\test\\CarSystemTest.java");
        FileWriter writer = new FileWriter(file);
        sb.append("import org.junit.jupiter.api.BeforeEach;\n");
        sb.append("import org.junit.jupiter.api.Test;\n");
        sb.append("import static org.junit.jupiter.api.Assertions.*;\n" + "\n");
        sb.append("class CarSystemTests{\n\n");
        sb.append("@BeforeEach\n void setup(){\n carSystem.CarSystem cs = new carSystem.CarSystem();\n}\n");
        sb.append("\n@Test\nvoid test(){\n");
        sb.append("carSystem.CarSystem cs = new carSystem.CarSystem();\n");
        sb.append(testCode);
        sb.append("}\n}");
        writer.write(sb.toString());
        writer.close();
    }

        //"D:\\git\\Projekter\\CAS\\testCases\\test" - Kap
        //"D:\\repos\\CAS\\testCases\\test" - Esben
    public static void loadTestCases() throws IOException {
        File file = new File("CarSystemModelTest\\test\\CarSystemTests.java");
        int numberOfTestCases = new File("testCases").listFiles().length;
        StringBuilder sb = new StringBuilder();
        FileWriter writer = new FileWriter(file);
        Path path;
        String filePath;
        sb.append("import carsystem.CarSystem;\n");
        sb.append("import org.junit.jupiter.api.BeforeEach;\n");
        sb.append("import org.junit.jupiter.api.Test;\n");
        sb.append("import static org.junit.jupiter.api.Assertions.*;\n" + "\n");
        sb.append("class CarSystemTests{\n\n");
        for (int i = 0; i < numberOfTestCases; i++){
            filePath = "C:\\Users\\Yann\\Desktop\\Projekter\\CAS\\testCases\\test" + new DecimalFormat("000").format(i)+".txt";
            path = Paths.get(filePath);
            sb.append("\n@Test\nvoid testcase" +  new DecimalFormat("000").format(i) + "(){\n");
            sb.append("CarSystem cs = new CarSystem();\n");
            sb.append(Files.readString(path));
            sb.append("}\n");
        }
        sb.append("}");
        writer.write(sb.toString());
        writer.close();
    }


}
