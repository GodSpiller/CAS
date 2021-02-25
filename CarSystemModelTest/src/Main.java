import java.io.*;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DecimalFormat;
import java.util.ArrayList;

import com.uppaal.model.core2.*;
import com.uppaal.engine.*;
import com.uppaal.model.system.UppaalSystem;
import com.uppaal.model.system.symbolic.SymbolicState;
import com.uppaal.model.system.symbolic.SymbolicTransition;


public class Main {

    public static void main(String[] args) throws IOException, EngineException {
        URL url = new URL("https://pastebin.com/raw/54yhYF8g");
        Document doc = new PrototypeDocument().load(url);

        Engine engine = new Engine();
        engine.setServerPath("C:\\Users\\Yann\\Desktop\\uppaal-4.1.24\\bin-Windows\\server.exe");
        engine.connect();
        engine.disconnect();

    }

    /*public static void LoadTestCases() throws IOException {
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
        sb.append("@BeforeEach\n void setup(){\n CarSystem cs = new CarSystem();\n}\n");
        for (int i = 0; i < numberOfTestCases; i++){
            filePath = "D:\\repos\\CAS\\testCases\\test" + new DecimalFormat("000").format(i)+".txt";
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
         */
}
