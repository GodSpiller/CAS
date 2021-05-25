package modelhandler;

import ast.BoundaryValue;
import com.uppaal.engine.*;
import com.uppaal.model.core2.*;
import com.uppaal.model.system.*;
import com.uppaal.model.system.Process;
import com.uppaal.model.system.concrete.ConcreteTrace;
import com.uppaal.model.system.symbolic.SymbolicTrace;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;

import static java.lang.Integer.parseInt;

public class ModelHandler {
    public Engine engine = new Engine();
    ArrayList<Problem> problems = new ArrayList<Problem>();
    //url for CAS
    URL url = new URL("https://raw.githubusercontent.com/GodSpiller/CAS/main/CAS2.3.xml");
    //url for Updown
    //URL url = new URL("https://raw.githubusercontent.com/GodSpiller/CAS/main/Updown.xml");
    Document document;;
    ModelHandlerVisitor modelHandlerVisitor = new ModelHandlerVisitor();
    Template template;

    public ModelHandler() throws IOException, EngineException {
        document = new PrototypeDocument().load(url);
        //change to local path - found in uppaal folder (version 4.1.24)
        engine.setServerPath("\"C:\\\\Users\\\\Yann\\\\Desktop\\\\uppaal-4.1.24\\\\bin-Windows\\\\server.exe\"");
        engine.connect();
    }

    /*
     * Creates test code of test cases based on BVA
     *
     * @param processName: The name of the template or "process", which tests are being made for.
     * @return an ArrayList of test cases
     */
    public ArrayList<StringBuilder> createTestCode(String processName) throws EngineException, CloneNotSupportedException {
        GuardMaker gm = new GuardMaker();
        ArrayList<StringBuilder> testCases = new ArrayList<>();
        UppaalSystem system = engine.getSystem(document, problems);

        for (Process process : system.getProcesses()) {
            if(process.getTemplate().getPropertyValue("name").equals(processName)) {
                for (SystemEdge edge : process.getEdges()) {
                    if (hasProperty(edge, "guard")) {
                        HashMap<Integer, ArrayList<BoundaryValue>> guards = gm.makeGuards(edge.getEdge().getPropertyValue("guard").toString());
                        for (Integer i : guards.keySet()){
                            for (BoundaryValue boundaryValue : guards.get(i)){
                                // Creates a new edge, allowing "negative" behaviour in the UPPAAL model
                                // A query is made which showcases that behaviour and returns test code, the edge is then removed
                                if (!boundaryValue.getValidity()) {
                                    createNegativeEdge(edge.getEdge(), boundaryValue, document, processName);
                                    testCases.add(getTrace(edge.getEdge().getTarget().getName(), boundaryValue, processName));
                                    removeNegativeEdge(processName);
                                }
                                // If the test case is showcasing valid behaviour and the edge we wish to traverse contains an assigment
                                // testgoal == true is added to the assignment, a query is run which returns test code.
                                // Assignment is then returned to normal
                                else if (hasProperty(edge, "assignment")) {
                                    String oldAssignment = edge.getEdge().getPropertyValue("assignment").toString();
                                    edge.getEdge().setProperty("assignment", edge.getEdge().getPropertyValue("assignment") + ", testgoal = true");
                                    testCases.add(getTrace(edge.getEdge().getTarget().getName(), boundaryValue, processName));
                                    edge.getEdge().setProperty("assignment", oldAssignment);
                                }
                                // same case as above, but assignment is empty.
                                else {
                                    edge.getEdge().setProperty("assignment", "testgoal = true");
                                    testCases.add(getTrace(edge.getEdge().getTarget().getName(), boundaryValue, processName));
                                    edge.getEdge().setProperty("assignment", "");
                                }
                            }
                        }
                    }
                }
            }
        }
        return testCases;
    }

    private void removeNegativeEdge(String processName) throws EngineException {
        UppaalSystem system = engine.getSystem(document, problems);
        for (Process process : system.getProcesses()) {
            if(process.getTemplate().getPropertyValue("name").equals(processName)) {
                for (SystemEdge edge : process.getEdges()) {
                    if (edge.getEdge().getPropertyValue("testcode").equals("fail();")) {
                        edge.getEdge().remove();
                    }
                }
            }
        }
    }

    private void createNegativeEdge(Edge edge, BoundaryValue boundaryValue, Document doc, String processName) throws CloneNotSupportedException {
        template = (Template) doc.getTemplate(processName).clone();
        Edge newEdge = template.createEdge();
        document.getTemplate(processName).insert(newEdge, doc.getTemplate(processName).getLast());
        newEdge.setSource(edge.getSource());
        newEdge.setTarget(edge.getTarget());
        newEdge.setProperty("guard", boundaryValue.getGuard());
        newEdge.setProperty("testcode", "fail();");
        newEdge.setProperty("assignment", "testgoal = true");
        newEdge.setProperty("synchronisation", edge.getPropertyValue("synchronisation").toString());
    }

    /*
     * get the test code from the start state to the location
     *
     * @param location: the target location of the query
     * @param boundaryValue: Contains information needed to create the query.
     * @param processName: The name of the template we are querying. May be necessary if a clock value is local.
     * @return A StringBuilder with the test code of the trace
     */
    private StringBuilder getTrace(String location, BoundaryValue boundaryValue, String processName) throws EngineException {
        UppaalSystem system = engine.getSystem(document, problems);
        Query q;
        //The query specifies that testgoal must be true, and the clock must have a certain value.

        //for Updown model
        //q = new Query("E<> testgoal == true && " + processName + "." + boundaryValue.getClock() + " == " + boundaryValue.getQueryValue(), "");
        //for CAS model
        q = new Query("E<> testgoal == true && " + boundaryValue.getClock() + " == " + boundaryValue.getQueryValue(), "");

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
                // We utilize the modelHandlerVisitor to extract testcode from the edges we visit throughout the trace
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

        engine.query(system, "trace 1", q, qf);

        return modelHandlerVisitor.getStringBuilder();
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
