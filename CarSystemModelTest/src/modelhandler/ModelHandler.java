package modelhandler;

import ast.nodes.BoundaryValue;
import com.uppaal.engine.*;
import com.uppaal.model.core2.*;
import com.uppaal.model.system.*;
import com.uppaal.model.system.Process;
import com.uppaal.model.system.concrete.ConcreteTrace;
import com.uppaal.model.system.symbolic.SymbolicState;
import com.uppaal.model.system.symbolic.SymbolicTrace;
import com.uppaal.model.system.symbolic.SymbolicTransition;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

// Normale mennesker
//  "C:\\Users\\Esben\\Desktop\\uppaal-4.1.24\\bin-Windows\\server.exe"
// Kasper
//  "D:\\AAU\\Programmer\\Uppaal\\uppaal-4.1.24\\bin-Windows\\server.exe"
// Krozmoz
//  "E:\\Uni\\6semester\\MTCPS\\uppaal-4.1.24\\bin-Windows\\server.exe"

public class ModelHandler {
    public ArrayList<Problem> problems = new ArrayList<Problem>();
    URL url = new URL("https://raw.githubusercontent.com/GodSpiller/CAS/main/CAS2.0.xml");
    Engine engine = new Engine();
    public Document document;
    public UppaalSystem system;
    SymbolicState state;
    ModelHandlerVisitor modelHandlerVisitor = new ModelHandlerVisitor();
    Template template;

    public ModelHandler() throws IOException, EngineException, CannotEvaluateException {
        document = new PrototypeDocument().load(url);
        engine.setServerPath("\"C:\\\\Users\\\\Yann\\\\Desktop\\\\uppaal-4.1.24\\\\bin-Windows\\\\server.exe\"");
        engine.connect();
        system = engine.getSystem(document, problems);
        state = engine.getInitialState(system);
    }

    public void hmm() throws CloneNotSupportedException {
        makeEdge(system.getProcess(0).getEdge(10).getEdge().getSource(), system.getProcess(0).getEdge(10).getEdge().getTarget());
        try {
            document.save("sampledoc0.xml");
        } catch (IOException e) {
            e.printStackTrace(System.err);
        }
    }

    /*
     * Creates test code of test cases based on BVA
     *
     * @return an ArrayList of test cases
     */
    public ArrayList<StringBuilder> createTestCode() throws EngineException, IOException, CloneNotSupportedException {
        GuardMaker gm = new GuardMaker();
        StringBuilder sb;
        StringBuilder oldGuard;
        ArrayList<StringBuilder> testCases = new ArrayList<>();

        int j = 0;
        for (SystemEdge edge : system.getProcess(0).getEdges()) { // for each edge in the process
            if (hasProperty(edge, "guard")) {
                // resets the oldGuard variable when we have a new edge
                oldGuard = new StringBuilder();
                HashMap<Integer, ArrayList<BoundaryValue>> guards;
                sb = new StringBuilder();
                sb.append(edge.getEdge().getPropertyValue("guard"));
                // creates new guards for an edge
                guards = gm.makeGuards(sb);
                // set the oldGuard variable to the guard of the edge
                oldGuard.append(edge.getEdge().getPropertyValue("guard"));
                for (Integer i : guards.keySet()){
                    for (BoundaryValue boundaryValue : guards.get(i)){
                        makeEdge(edge.getEdge().getSource(), edge.getEdge().getTarget());

                        testCases.add(getTrace(edge.getEdge().getTarget().getName(), String.valueOf(boundaryValue.getValue()), boundaryValue.getClock()));

                        document.getTemplate("Negative").remove();

                    }
                }
            }
        }
        return testCases;
    }

    private void makeEdge(AbstractLocation source, AbstractLocation target) throws CloneNotSupportedException {
        template = (Template) document.getTemplate("Spec").clone();
        document.insert(template, document.getTemplate("Spec")).setProperty("name", "Negative");
        Edge edge = template.createEdge();
        template.insert(edge, null);
        edge.setSource(source);
        edge.setTarget(target);
        edge.setProperty("assignment", "x=10000");
        //edge.setProperty("guard", boundaryValue.getClock() + "==" + boundaryValue.getValue());
        edge.setProperty("testcode", "fail(); ");
    }

    /*
     * get the test code from the start state to the location
     *
     * @param location: the target location of the query
     * @param clockValue: the clock value of the query
     * @param clockVariable: the clock variable of the query
     * @return A StringBuilder with the test code of the trace
     */
    private StringBuilder getTrace(String location, String clockValue, String clockVariable) throws EngineException, IOException {
        system = engine.getSystem(document, problems);
        Query q = new Query("E<> Negative." + location + " && " + clockVariable + " == " + clockValue, "");
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
            }
        };
        QueryResult qr = engine.query(system, "trace 1", q, qf);

        return modelHandlerVisitor.getStringBuilder();
    }

    /*
     * Update the guard of an edge
     *
     * @param edge: the edge to be updated
     * @param newGuard: the new guard
     */
    private void updateGuard(SystemEdge edge, String newGuard){
        edge.getEdge().setProperty("guard", newGuard);
    }


    /*
     * checks if an edge has a property
     *
     * @param edge: the edge to be checked
     * @param param property: the property
     * @return true if the edge has the property, false if it does not
     */
    private boolean hasProperty(SystemEdge edge, String property) {
        if (!edge.getEdge().getPropertyValue(property).equals("")) {
            return true;
        }
        return false;
    }

    /*
     * checks if a location has a property
     *
     * @param location: the location to be checked
     * @param param property: the property
     * @return true if the location has the property, false if it does not
     */
    private boolean hasProperty(Location location, String property){
        if (!location.getPropertyValue(property).equals("")){
            return true;
        }
        return false;
    }

}
