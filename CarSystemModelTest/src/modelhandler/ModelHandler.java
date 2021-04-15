package modelhandler;

import ast.nodes.BoundaryValue;
import com.uppaal.engine.*;
import com.uppaal.model.core2.*;
import com.uppaal.model.system.*;
import com.uppaal.model.system.concrete.ConcreteTrace;
import com.uppaal.model.system.symbolic.SymbolicTrace;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;

import static java.lang.Integer.parseInt;

// Normale mennesker
//  "C:\\Users\\Esben\\Desktop\\uppaal-4.1.24\\bin-Windows\\server.exe"
// Kasper
//  "D:\\AAU\\Programmer\\Uppaal\\uppaal-4.1.24\\bin-Windows\\server.exe"
// Krozmoz
//  "E:\\Uni\\6semester\\MTCPS\\uppaal-4.1.24\\bin-Windows\\server.exe"

public class ModelHandler {
    public ArrayList<Problem> problems = new ArrayList<Problem>();
    URL url = new URL("https://raw.githubusercontent.com/GodSpiller/CAS/main/CAS2.1.xml");
    public Engine engine = new Engine();
    public Document document;;
    ModelHandlerVisitor modelHandlerVisitor = new ModelHandlerVisitor();
    Template template;

    public ModelHandler() throws IOException, EngineException, CannotEvaluateException {
        document = new PrototypeDocument().load(url);
        engine.setServerPath("\"C:\\\\Users\\\\Esben\\\\Desktop\\\\uppaal-4.1.24\\\\bin-Windows\\\\server.exe\"");
        engine.connect();
    }

    /*
     * Creates test code of test cases based on BVA
     *
     * @return an ArrayList of test cases
     */
    public ArrayList<StringBuilder> createTestCode() throws EngineException, CloneNotSupportedException {
        GuardMaker gm = new GuardMaker();
        StringBuilder sb;
        StringBuilder oldGuard;
        ArrayList<StringBuilder> testCases = new ArrayList<>();
        UppaalSystem system = engine.getSystem(document, problems);

        int j = 0;
        for (SystemEdge edge : system.getProcess(0).getEdges()) {
            // for each edge in the process
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
                        if (!boundaryValue.getValidity()) {
                            makeNegativeEdge(edge.getEdge().getSource(), edge.getEdge().getTarget(), boundaryValue, document);
                        }

                        testCases.add(getTrace(edge.getEdge().getTarget().getName(), boundaryValue, engine.getSystem(document, problems)));

                        try {
                            document.save("sampledoc" + j++ + ".xml");
                        } catch (IOException e) {
                            e.printStackTrace(System.err);
                        }

                        removeNegativeEdge();

                    }
                }
            }
        }
        return testCases;
    }

    private void removeNegativeEdge() throws EngineException {
        UppaalSystem system = engine.getSystem(document, problems);
        for (SystemEdge edge : system.getProcess(0).getEdges()) {
            if (edge.getEdge().getPropertyValue("testcode").equals("fail();")) {
                edge.getEdge().remove();
            }
        }

    }

    private void makeNegativeEdge(AbstractLocation source, AbstractLocation target, BoundaryValue boundaryValue, Document doc) throws CloneNotSupportedException {
        template = (Template) doc.getTemplate("Spec").clone();
        Edge edge = template.createEdge();
        document.getTemplate("Spec").insert(edge, doc.getTemplate("Spec").getLast());
        edge.setSource(source);
        edge.setTarget(target);
        edge.setProperty("guard", boundaryValue.getGuard());
        edge.setProperty("testcode", "fail();");
        edge.setProperty("assignment", "testgoal = true");
        edge.setProperty("name", "negativeEdge");
    }

    /*
     * get the test code from the start state to the location
     *
     * @param location: the target location of the query
     * @param clockValue: the clock value of the query
     * @param clockVariable: the clock variable of the query
     * @return A StringBuilder with the test code of the trace
     */
    private StringBuilder getTrace(String location, BoundaryValue boundaryValue, UppaalSystem system) throws EngineException {
        Query q = new Query("E<> testgoal", "");
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

    private String makeRestriction(BoundaryValue boundaryValue) {
        return " && " + (boundaryValue.getValue() - 1) + " < " + boundaryValue.getClock() + " && " + boundaryValue.getClock() + " < " + boundaryValue.getValue();
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
