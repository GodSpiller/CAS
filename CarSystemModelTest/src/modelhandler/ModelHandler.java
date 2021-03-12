package modelhandler;

import com.uppaal.engine.*;
import com.uppaal.model.core2.*;
import com.uppaal.model.system.*;
import com.uppaal.model.system.Process;
import com.uppaal.model.system.concrete.ConcreteTrace;
import com.uppaal.model.system.symbolic.SymbolicState;
import com.uppaal.model.system.symbolic.SymbolicTrace;
import com.uppaal.model.system.symbolic.SymbolicTransition;
import com.uppaal.engine.QueryFeedback;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

public class ModelHandler {
    URL url = new URL("https://raw.githubusercontent.com/GodSpiller/CAS/main/CAS_final(hopefully).xml");
    Engine engine = new Engine();
    public Document document;
    public UppaalSystem system;
    SymbolicState state;
    ModelHandlerVisitor dv = new ModelHandlerVisitor();

    public ModelHandler() throws IOException, EngineException, CannotEvaluateException {
        document = new PrototypeDocument().load(url);
        engine.setServerPath("C:\\Users\\Esben\\Desktop\\uppaal-4.1.24\\bin-Windows\\server.exe");
        engine.connect();
        ArrayList<Problem> problems = new ArrayList<Problem>();
        system = engine.getSystem(document, problems);
        state = engine.getInitialState(system);
    }

    public String getTrace() throws EngineException, IOException {
        Query q = new Query("E<> Spec.weird", "");
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

    public void ChangeProperty(SystemEdge edge, String newGuard){
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

    public Template CloneProcess() throws CloneNotSupportedException {
        return (Template) document.getTemplate("Spec").clone();
    }

    public void SaveMutant(Template t, int i){
        document.insert(t, null).setProperty("name", "mutant"+i);
        document.setProperty("system", "system Spec, User, mutant" + i + ";");
        try {
            document.save("sampledoc.xml");
        } catch (IOException e) {
            e.printStackTrace(System.err);
        }
    }

    public Template CreateMutant(int i) throws CloneNotSupportedException {
        Template t = CloneProcess();

        return t;
    }

}
