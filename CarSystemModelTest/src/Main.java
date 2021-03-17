import com.uppaal.engine.CannotEvaluateException;
import com.uppaal.engine.EngineException;
import com.uppaal.model.core2.Document;
import com.uppaal.model.system.Process;
import com.uppaal.model.system.SystemEdge;
import modelhandler.ModelHandler;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DecimalFormat;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) throws Exception {
        insertBoundaries();
    }

    public static void insertBoundaries() throws CannotEvaluateException, EngineException, IOException {
        ModelHandler modelHandler = new ModelHandler();
        TestMaker tm = new TestMaker();


        for (Process process : modelHandler.system.getProcesses()){
            int i = 0;
            for (SystemEdge edge : process.getEdges()) {
                StringBuilder sb = new StringBuilder();
                ArrayList<String> guards;
                if (!edge.getEdge().getPropertyValue("guard").equals("")) {
                    sb.append(edge.getEdge().getPropertyValue("guard"));
                }
                if (!(sb.length() == 0)) {
                    guards = tm.guardMaker(sb);
                    Document newDocument = modelHandler.document;
                    for (String str : guards) {
                        modelHandler.ChangeGuard(edge, str);
                        System.out.println(edge.getEdge().getName() + " - " + str);
                        //System.out.println(edge.getEdge().getTarget().getPropertyValue("testcodeEnter") + "\n");
                        try {
                            newDocument.save("sampledoc" + i + ".xml");
                            i++;
                        } catch (IOException e) {
                            e.printStackTrace(System.err);
                        }
                    }
                    modelHandler.ChangeGuard(edge, guards.get(0));
                }
            }
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


    public static void LoadTestCases() throws IOException {
        File file = new File("CarSystemModelTest\\test\\CarSystemTests.java");
        int numberOfTestCases = new File("testCases").listFiles().length;
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
            filePath = "D:\\repos\\CAS\\testCases\\test" + new DecimalFormat("000").format(i)+".txt";
            path = Paths.get(filePath);
            sb.append("\n@Test\nvoid testcase" +  new DecimalFormat("000").format(i) + "(){\n");
            sb.append("carSystem.CarSystem cs = new carSystem.CarSystem();\n");
            sb.append(Files.readString(path));
            sb.append("}\n");
        }
        sb.append("}");
        writer.write(sb.toString());
        writer.close();
    }
}
