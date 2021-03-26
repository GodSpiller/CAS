package modelhandler;

import ast.nodes.BoundaryValue;
import com.uppaal.engine.*;
import com.uppaal.model.core2.Document;
import com.uppaal.model.core2.PrototypeDocument;
import com.uppaal.model.core2.Query;
import com.uppaal.model.system.Process;
import com.uppaal.model.system.SystemEdge;
import com.uppaal.model.system.SystemEdgeSelect;
import com.uppaal.model.system.UppaalSystem;
import com.uppaal.model.system.concrete.ConcreteTrace;
import com.uppaal.model.system.symbolic.SymbolicState;
import com.uppaal.model.system.symbolic.SymbolicTrace;
import com.uppaal.model.system.symbolic.SymbolicTransition;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;

// Normale mennesker
//  "C:\\Users\\Esben\\Desktop\\uppaal-4.1.24\\bin-Windows\\server.exe"
// Kasper
//  "D:\\AAU\\Programmer\\Uppaal\\uppaal-4.1.24\\bin-Windows\\server.exe"
// Krozmoz
//  "E:\\Uni\\6semester\\MTCPS\\uppaal-4.1.24\\bin-Windows\\server.exe"

public class ModelHandler {

    URL url = new URL("https://raw.githubusercontent.com/GodSpiller/CAS/main/CAS_FINAL_DESTINATION.xml");
    Engine engine = new Engine();
    public Document document;
    public UppaalSystem system;
    SymbolicState state;
    ModelHandlerVisitor modelHandlerVisitor = new ModelHandlerVisitor();

    public ModelHandler() throws IOException, EngineException, CannotEvaluateException {
        document = new PrototypeDocument().load(url);
        engine.setServerPath("D:\\AAU\\Programmer\\Uppaal\\uppaal-4.1.24\\bin-Windows\\server.exe");
        engine.connect();
        ArrayList<Problem> problems = new ArrayList<Problem>();
        system = engine.getSystem(document, problems);
        state = engine.getInitialState(system);
    }

    public void createTestCode() {
        //find edge

        GuardMaker gm = new GuardMaker();
        StringBuilder sb;


        for (Process p : system.getProcesses()) {
            for (SystemEdge edge : p.getEdges()) {
                //System.out.println(edge.getEdge().getPropertyValue("guard"));



                if (!edge.getEdge().getPropertyValue("guard").equals("")) {
                    //Parser parser = new Parser(new Lexer(edge.getEdge().getPropertyValue("guard").toString()));

                    HashMap<Integer, ArrayList<BoundaryValue>> guards = new HashMap<>();

                    guards = gm.makeGuards(new StringBuilder(edge.getEdge().getPropertyValue("guard").toString()));



                    sb = new StringBuilder();

                    sb.append(edge.getEdge().getPropertyValue("guard"));
                    guards = gm.makeGuards(sb);


                    for (Integer i : guards.keySet()){
                        for (BoundaryValue boundaryValue : guards.get(i)){
                            System.out.format("%s : %s \n", boundaryValue.getGuard(), boundaryValue.getValidity());
                        }
                    }
                }
            }
        }
        //boundary shit
        //ændre guard og testcode
        //få testcode
        //repeat 2,3,4
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
                                edge.getEdge().accept(modelHandlerVisitor);
                            }
                            symbolicTransition.getTarget().getLocations()[0].getLocation().accept(modelHandlerVisitor);
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
        return modelHandlerVisitor.testCode.toString();
    }

    public void changeTestCode(HashMap<Integer, ArrayList<BoundaryValue>> boundaryValues) {

    }

    public void changeTestCode1(SystemEdge edge){
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

    public void updateGuard(SystemEdge edge, String newGuard){
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
