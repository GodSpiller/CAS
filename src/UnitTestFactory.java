import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;

public class UnitTestFactory {
    /*
     * Creates a Unit Test for each element in the ArrayList testCases
     *
     * @param testCases: Arraylist of testcode
     */
    public static void makeUnitTestsUpDown(ArrayList<StringBuilder> testCases) throws IOException {
        File file = new File("test\\evalclass\\UpDownTest.java");
        int numberOfTestCases = testCases.size();
        StringBuilder sb = new StringBuilder();
        FileWriter writer = new FileWriter(file);

        sb.append("package evalclass;\n");
        sb.append("import org.junit.Test;\n");
        sb.append("import static org.junit.Assert.*;\n" + "\n");
        sb.append("public class UpDownTest{\n\n");
        for (int i = 0; i < numberOfTestCases; i++){
            sb.append("\n@Test\npublic void testcase" +  new DecimalFormat("000").format(i) + "(){\n");
            sb.append("App cs = new App();\n");
            sb.append(testCases.get(i));
            sb.append("}\n");
        }
        sb.append("}");
        writer.write(sb.toString());
        writer.close();
    }

    public static void makeUnitTestsCAS(ArrayList<StringBuilder> testCases) throws IOException {
        File file = new File("test\\carsystem\\CarSystemTest.java");
        int numberOfTestCases = testCases.size();
        StringBuilder sb = new StringBuilder();
        FileWriter writer = new FileWriter(file);

        sb.append("package carsystem;\n");
        sb.append("import org.junit.Test;\n");
        sb.append("import static org.junit.Assert.*;\n" + "\n");
        sb.append("public class CarSystemTest{\n\n");
        for (int i = 0; i < numberOfTestCases; i++){
            sb.append("\n@Test\npublic void testcase" +  new DecimalFormat("000").format(i) + "(){\n");
            sb.append("CarSystem cs = new CarSystem();\n");
            sb.append(testCases.get(i));
            sb.append("}\n");
        }
        sb.append("}");
        writer.write(sb.toString());
        writer.close();
    }
}
