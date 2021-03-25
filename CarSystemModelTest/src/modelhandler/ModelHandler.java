package modelhandler;

import com.uppaal.engine.*;
import com.uppaal.model.core2.Document;
import com.uppaal.model.core2.PrototypeDocument;
import com.uppaal.model.core2.Query;
import com.uppaal.model.core2.Template;
import com.uppaal.model.system.SystemEdge;
import com.uppaal.model.system.SystemEdgeSelect;
import com.uppaal.model.system.UppaalSystem;
import com.uppaal.model.system.concrete.ConcreteTrace;
import com.uppaal.model.system.symbolic.SymbolicState;
import com.uppaal.model.system.symbolic.SymbolicTrace;
import com.uppaal.model.system.symbolic.SymbolicTransition;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

// Normale mennesker
//  "C:\\Users\\Esben\\Desktop\\uppaal-4.1.24\\bin-Windows\\server.exe"
// Kasper
//  "D:\\AAU\\Programmer\\Uppaal\\uppaal-4.1.24\\bin-Windows\\server.exe"

public class ModelHandler {

    URL url = new File("C:\\Users\\Yann\\Desktop\\Projekter\\CAS\\CAS_FINAL_DESTINATION.xml").toURI().toURL();
    Engine engine = new Engine();
    public Document document;
    public UppaalSystem system;
    SymbolicState state;
    ModelHandlerVisitor dv = new ModelHandlerVisitor();

    public ModelHandler() throws IOException, EngineException, CannotEvaluateException {
        document = new PrototypeDocument().load(url);
        engine.setServerPath("C:\\Users\\Yann\\Desktop\\uppaal-4.1.24\\bin-Windows\\server.exe");
        engine.connect();
        ArrayList<Problem> problems = new ArrayList<Problem>();
        system = engine.getSystem(document, problems);
        state = engine.getInitialState(system);
    }

    public String getTrace() throws EngineException, IOException {
        Query q = new Query("E<> Spec.ArmedAndClosedAndLocked", "");
        QueryFeedback qf = new QueryFeedback() {
            @Override
            public void setProgressAvail(boolean b) {

            }

            @Override
            public void setProgress(int i, long l, long l1, long l2, long l3, long l4, long l5, long l6, long l7, long l8) {

            }

            @Override
            public void setSystemInfo(long l, long l1, long l2) {

            }

            @Override
            public void setLength(int i) {

            }

            @Override
            public void setCurrent(int i) {

            }

            @Override
            public void setTrace(char c, String s, SymbolicTrace symbolicTrace, QueryResult queryResult) {

                symbolicTrace.forEach(symbolicTransition -> {
                    if (symbolicTransition.getEdges() != null) {
                        try {
                            for (SystemEdgeSelect edge : symbolicTransition.getEdges()){
                                edge.getEdge().accept(dv);
                            }
                            symbolicTransition.getTarget().getLocations()[0].getLocation().accept(dv);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });
            }

            @Override
            public void setTrace(char c, String s, ConcreteTrace concreteTrace, QueryResult queryResult) {

            }

            @Override
            public void setFeedback(String s) {

            }

            @Override
            public void appendText(String s) {
            }

            @Override
            public void setResultText(String s) {
                System.out.println("setResultText");
            }
        };
        QueryResult qr = engine.query(system, "trace 1", q, qf);
        return dv.testCode.toString();
    }

    public void changeTestCode(SystemEdge edge){
        String testcode = edge.getEdge().getTarget().getPropertyValue("testcodeEnter").toString();
        String[] testcodes = testcode.split("(?<=[(;])", -1);
        int j = 0;

        for (String s : testcodes){
            if (s.equals("assertTrue(") || s.equals("\nassertTrue(")){
                testcodes[j] = "assertFalse(";
            }
            else if (s.equals("assertFalse(") || s.equals("\nassertFalse(")){
                testcodes[j] = "assertTrue(";
            }
            j++;
        }

        for (int i = 0; i < testcodes.length-1; i++){
            if (i%2 == 0){
                testcodes[i] = testcodes[i]+testcodes[i+1];
            }

        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < testcodes.length-1; i++){
            if (i%2 == 0){
                sb.append(testcodes[i] + "\n");
            }

        }
        //System.out.println(sb.toString());
        edge.getEdge().getTarget().setProperty("testcodeEnter", sb.toString());
    }

    public void changeGuard(SystemEdge edge, String newGuard){
        edge.getEdge().setProperty("guard", newGuard);
    }

    public ArrayList<SymbolicTransition> getTransitionInfo() throws EngineException {

        ArrayList<SymbolicTransition> symbolicTransitions = new ArrayList<>();
        Query q = new Query("E<> Spec.weird", " ");
        QueryFeedback qf = new QueryFeedback() {
            @Override
            public void setProgressAvail(boolean b) {

            }

            @Override
            public void setProgress(int i, long l, long l1, long l2, long l3, long l4, long l5, long l6, long l7, long l8) {

            }

            @Override
            public void setSystemInfo(long l, long l1, long l2) {

            }

            @Override
            public void setLength(int i) {

            }

            @Override
            public void setCurrent(int i) {

            }

            @Override
            public void setTrace(char c, String s, SymbolicTrace symbolicTrace, QueryResult queryResult) {

                symbolicTrace.forEach(symbolicTransition -> {
                    if (symbolicTransition.getEdges() != null) {
                        symbolicTransitions.add(symbolicTransition);
                    }
                });
            }

            @Override
            public void setTrace(char c, String s, ConcreteTrace concreteTrace, QueryResult queryResult) {

            }

            @Override
            public void setFeedback(String s) {

            }

            @Override
            public void appendText(String s) {
            }

            @Override
            public void setResultText(String s) {
                System.out.println("setResultText");
            }
        };
        QueryResult qr = engine.query(system,"trace 1",q, qf);

        return symbolicTransitions;
    }

}
