import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DecimalFormat;

public class Main {
    public static void main(String[] args) throws IOException {
        LoadTestCases();

    }

    public static void LoadTestCases() throws IOException {
        int numberOfTestCases = new File("testCases").listFiles().length;
        for (int i = 0; i < numberOfTestCases; i++){
            File file = new File("CarSystemModelTest\\src\\Test"+ new DecimalFormat("000").format(i)+".java");
            String filePath = "D:\\repos\\CAS\\testCases\\test" + new DecimalFormat("000").format(i)+".txt";
            Path path = Paths.get(filePath);

            FileWriter writer = new FileWriter(file);
            StringBuilder sb = new StringBuilder();
            sb.append("import static org.junit.jupiter.api.Assertions.*;\n" +
                    "\n");
            sb.append("public class Test" + new DecimalFormat("000").format(i) + "{\n");
            sb.append("public void test(){\n");
            sb.append("CarSystem cs = new CarSystem();\n");
            // reads the test code provided by UPPAAL
            sb.append(Files.readString(path));
            sb.append("}\n}");
            writer.write(sb.toString());
            writer.close();
            System.out.println(sb);
        }
    }
}
