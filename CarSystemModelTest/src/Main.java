import java.io.*;
import java.math.BigDecimal;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DecimalFormat;
import java.util.ArrayList;

import com.uppaal.model.core2.*;
import com.uppaal.engine.*;
import com.uppaal.model.system.GanttChart;
import com.uppaal.model.system.SystemEdge;
import com.uppaal.model.system.UppaalSystem;
import com.uppaal.model.system.concrete.ConcreteTrace;
import com.uppaal.model.system.symbolic.SymbolicState;
import com.uppaal.model.system.symbolic.SymbolicTrace;
import com.uppaal.model.system.symbolic.SymbolicTransition;


public class Main {

    public static void main(String[] args) throws IOException, EngineException, CannotEvaluateException {
        URL url = new URL("https://raw.githubusercontent.com/GodSpiller/CAS/main/CAS_final(hopefully).xml");
        Document doc = new PrototypeDocument().load(url);

        try {
            doc.save("sampledoc.xml");
        } catch (IOException e) {
            e.printStackTrace(System.err);
        }

        Engine engine = new Engine();
        engine.setServerPath("C:\\Users\\Yann\\Desktop\\uppaal-4.1.24\\bin-Windows\\server.exe");
        engine.connect();

        ArrayList<Problem> problems = new ArrayList<Problem>();
        UppaalSystem system = engine.getSystem(doc, problems);


        Query q = new Query("E<> Spec.weird", "");

        QueryFeedback qr = new QueryFeedback() {
            @Override
            public void setProgressAvail(boolean b) {
                if (b) {
                    System.out.println("Progress information available");
                }
                else {
                    System.out.println("Progress information not available");
                }
            }

            @Override
            public void setProgress(int i, long l, long l1, long l2, long l3, long l4, long l5, long l6, long l7, long l8) {
              /*  System.out.println(i);
                System.out.println(l);
                System.out.println(l1);
                System.out.println(l2);
                System.out.println(l3);
                System.out.println(l4);
                System.out.println(l5);
                System.out.println(l6);
                System.out.println(l7);
                System.out.println(l8);
               */
            }

            @Override
            public void setSystemInfo(long l, long l1, long l2) {
                /*System.out.println(l);
                System.out.println(l1);
                System.out.println(l2);

                 */
            }

            @Override
            public void setLength(int i) {
                System.out.println(i);
            }

            @Override
            public void setCurrent(int i) {
                System.out.println(i);
            }

            @Override
            public void setTrace(char c, String s, SymbolicTrace symbolicTrace, QueryResult queryResult) {
                System.out.println("Im in setTrace1: " + c);
            }

            @Override
            public void setTrace(char c, String s, ConcreteTrace concreteTrace, QueryResult queryResult) {
                System.out.println("Im in setTrace2: " + queryResult);
            }

            @Override
            public void setFeedback(String s) {
                //System.out.println("setFeedback" + s);
            }

            @Override
            public void appendText(String s) {
                System.out.println(s);
            }

            @Override
            public void setResultText(String s) {
                System.out.println(s);
            }
        };

        for (Query qs: system.getDocument().getQueryList()) {
            System.out.println(qs);
        }

        System.out.println(engine.query(system, "trace 0", q, qr));


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
