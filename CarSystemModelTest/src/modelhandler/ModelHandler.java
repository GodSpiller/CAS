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

// Normale mennesker
//  "C:\\Users\\Esben\\Desktop\\uppaal-4.1.24\\bin-Windows\\server.exe"
// Kasper
//  "D:\\AAU\\Programmer\\Uppaal\\uppaal-4.1.24\\bin-Windows\\server.exe"
// Krozmoz
//  "E:\\Uni\\6semester\\MTCPS\\uppaal-4.1.24\\bin-Windows\\server.exe"

public class ModelHandler {
    public Engine engine = new Engine();

    ArrayList<Problem> problems = new ArrayList<Problem>();
    URL url = new URL("https://raw.githubusercontent.com/GodSpiller/CAS/main/CAS2.1.xml");
    Document document;;
    ModelHandlerVisitor modelHandlerVisitor = new ModelHandlerVisitor();
    Template template;

    public ModelHandler() throws IOException, EngineException {
        document = new PrototypeDocument().load(url);
        engine.setServerPath("\"C:\\\\Users\\\\Yann\\\\Desktop\\\\uppaal-4.1.24\\\\bin-Windows\\\\server.exe\"");
        engine.connect();
    }

    /*
     * Creates test code of test cases based on BVA
     *
     * @return an ArrayList of test cases
     */
    public ArrayList<StringBuilder> createTestCode(String processName) throws EngineException, CloneNotSupportedException {
        GuardMaker gm = new GuardMaker();
        StringBuilder sb;
        ArrayList<StringBuilder> testCases = new ArrayList<>();
        UppaalSystem system = engine.getSystem(document, problems);
        int j = 0;
        for (Process process : system.getProcesses()) {
            if(process.getTemplate().getPropertyValue("name").equals(processName)) {
                for (SystemEdge edge : process.getEdges()) {
                    if (hasProperty(edge, "guard")) {
                        HashMap<Integer, ArrayList<BoundaryValue>> guards;
                        sb = new StringBuilder();
                        sb.append(edge.getEdge().getPropertyValue("guard"));
                        // creates new guards for an edge
                        guards = gm.makeGuards(edge.getEdge().getPropertyValue("guard").toString());
                        for (Integer i : guards.keySet()){
                            for (BoundaryValue boundaryValue : guards.get(i)){
                                if (!boundaryValue.getValidity()) {
                                    makeNegativeEdge(edge.getEdge(), boundaryValue, document, processName);
                                }
                                try {
                                    document.save("sampledoc" + j++ + ".xml");
                                } catch (IOException e) {
                                    e.printStackTrace(System.err);
                                }
                                testCases.add(getTrace(edge.getEdge().getTarget().getName(), boundaryValue, processName));
                                removeNegativeEdge(processName);
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

    private void makeNegativeEdge(Edge edge, BoundaryValue boundaryValue, Document doc, String processName) throws CloneNotSupportedException {
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
     * @param clockValue: the clock value of the query
     * @param clockVariable: the clock variable of the query
     * @return A StringBuilder with the test code of the trace
     */
    private StringBuilder getTrace(String location, BoundaryValue boundaryValue, String processName) throws EngineException {
        UppaalSystem system = engine.getSystem(document, problems);
        Query q;
        if(!boundaryValue.getValidity()) {
            q = new Query("E<> testgoal == true", "");
        }
        else {
            return new StringBuilder();
            //q = new Query("E<> " + processName + "." + location, "");
        }


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
