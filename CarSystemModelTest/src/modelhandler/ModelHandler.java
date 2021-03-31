package modelhandler;

import ast.nodes.BoundaryValue;
import com.uppaal.engine.*;
import com.uppaal.model.core2.Document;
import com.uppaal.model.core2.Location;
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
    public ArrayList<Problem> problems = new ArrayList<Problem>();
    URL url = new URL("https://raw.githubusercontent.com/GodSpiller/CAS/main/CAS2.0.xml");
    Engine engine = new Engine();
    public Document document;
    public UppaalSystem system;
    SymbolicState state;
    ModelHandlerVisitor modelHandlerVisitor = new ModelHandlerVisitor();

    public ModelHandler() throws IOException, EngineException, CannotEvaluateException {
        document = new PrototypeDocument().load(url);
        engine.setServerPath("\"C:\\\\Users\\\\Esben\\\\Desktop\\\\uppaal-4.1.24\\\\bin-Windows\\\\server.exe\"");
        engine.connect();
        system = engine.getSystem(document, problems);
        state = engine.getInitialState(system);
    }

    public ArrayList<StringBuilder> createTestCode() throws EngineException, IOException {
        GuardMaker gm = new GuardMaker();
        StringBuilder sb;
        StringBuilder oldGuard;
        ArrayList<StringBuilder> testCases = new ArrayList<>();

        for (Process p : system.getProcesses()) {
            for (SystemEdge edge : p.getEdges()) {
                if (hasProperty(edge, "guard")) {
                    oldGuard = new StringBuilder(); // resets the oldGuard
                    HashMap<Integer, ArrayList<BoundaryValue>> guards;
                    sb = new StringBuilder();
                    sb.append(edge.getEdge().getPropertyValue("guard"));
                    guards = gm.makeGuards(sb);
                    oldGuard.append(edge.getEdge().getPropertyValue("guard"));
                    for (Integer i : guards.keySet()){
                        for (BoundaryValue boundaryValue : guards.get(i)){
                            updateGuard(edge, boundaryValue.getGuard());
                            String oldLocation = edge.getEdge().getTarget().getPropertyValue("testcodeEnter").toString();
                            if (!boundaryValue.getValidity()) {
                                edge.getEdge().getTarget().setProperty("testcodeEnter", "assertTrue(false);");
                            }
                            testCases.add(getTrace(edge.getEdge().getTarget().getName()));
                            if (!boundaryValue.getValidity()) {
                                edge.getEdge().getTarget().setProperty("testcodeEnter", oldLocation);
                            }
                        }
                    }
                    // update the edge's guard to the original before moving on the next edge
                    updateGuard(edge, oldGuard.toString());
                }
            }
        }
        return testCases;
    }

    /*
     * get the test code from the start state to the location
     *
     * @param location: the target location of the query
     * @return A StringBuilder with the test code of the trace
     */
    private StringBuilder getTrace(String location) throws EngineException, IOException {
        system = engine.getSystem(document, problems);
        Query q = new Query("E<> Spec." + location, "");
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
