import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;

public class UnitTestFactory {
    /*
     * Creates a Unit Test for each element in the ArrayList
     *
     * @param testCases: Arraylist of testcode
     */
    public static void makeUnitTests(ArrayList<StringBuilder> testCases) throws IOException {
        File file = new File("CarSystemModelTest\\test\\CarSystemTests.java");
        int numberOfTestCases = testCases.size();
        StringBuilder sb = new StringBuilder();
        FileWriter writer = new FileWriter(file);
        sb.append("import org.junit.jupiter.api.BeforeEach;\n");
        sb.append("import org.junit.jupiter.api.Test;\n");
        sb.append("import static org.junit.jupiter.api.Assertions.*;\n" + "\n");
        sb.append("import carsystem.CarSystem;");
        sb.append("class CarSystemTests{\n\n");
        for (int i = 0; i < numberOfTestCases; i++){
            sb.append("\n@Test\nvoid testcase" +  new DecimalFormat("000").format(i) + "(){\n");
            sb.append("CarSystem1 cs = new CarSystem();\n");
            sb.append(testCases.get(i));
            sb.append("}\n");
        }
        sb.append("}");
        writer.write(sb.toString());
        writer.close();
    }
}
