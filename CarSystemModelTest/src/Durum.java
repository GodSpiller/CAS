import com.uppaal.engine.*;
import com.uppaal.model.core2.*;
import com.uppaal.model.system.*;
import com.uppaal.model.system.concrete.ConcreteTrace;
import com.uppaal.model.system.symbolic.SymbolicState;
import com.uppaal.model.system.symbolic.SymbolicTrace;
import com.uppaal.model.system.symbolic.SymbolicTransition;
import com.uppaal.engine.QueryFeedback;
import com.uppaal.engine.*;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;


public class Durum {
    URL url = new URL("https://raw.githubusercontent.com/GodSpiller/CAS/main/CAS_final(hopefully).xml");
    Engine engine = new Engine();
    Document document;
    UppaalSystem system;
    SymbolicState state;
    DurumVisitor dv = new DurumVisitor();


    public Durum() throws IOException, EngineException, CannotEvaluateException {
        document = new PrototypeDocument().load(url);
        engine.setServerPath("C:\\Users\\Yann\\Desktop\\uppaal-4.1.24\\bin-Windows\\server.exe");
        engine.connect();
        ArrayList<Problem> problems = new ArrayList<Problem>();
        system = engine.getSystem(document, problems);
        state = engine.getInitialState(system);
    }

    public void getTrace() throws EngineException {
        QueryFeedback qf = new QueryFeedback() {
            @Override
            public void setProgressAvail(boolean b) {
                System.out.println("setProgressAvail");
            }

            @Override
            public void setProgress(int i, long l, long l1, long l2, long l3, long l4, long l5, long l6, long l7, long l8) {
                System.out.println("setProgress");
            }

            @Override
            public void setSystemInfo(long l, long l1, long l2) {
                System.out.println("setSystemInfo");
            }

            @Override
            public void setLength(int i) {
                System.out.println("setLength");
            }

            @Override
            public void setCurrent(int i) {
                System.out.println("setCurrent");
            }

            @Override
            public void setTrace(char c, String s, SymbolicTrace symbolicTrace, QueryResult queryResult) {
                System.out.println( queryResult.getResult());
                System.out.println("hej");
            }

            @Override
            public void setTrace(char c, String s, ConcreteTrace concreteTrace, QueryResult queryResult) {
                System.out.println( queryResult.getResult());
                System.out.println("hej");
            }

            @Override
            public void setFeedback(String s) {
                System.out.println("setFeedback");

            }

            @Override
            public void appendText(String s) {
                System.out.println("appendText");

            }

            @Override
            public void setResultText(String s) {
                System.out.println("setResultText");
            }
        };

        Query q = new Query("E<> Spec.weird", "");
        QueryResult qr = engine.query(system, "order 0", q, qf);

        System.out.println(qr.getResult());
    }

    public String getTestCode(String locationName) throws Exception {

        ArrayList<SymbolicTransition> transitions;
        SymbolicTransition transition;
        StringBuilder stringBuilder = new StringBuilder();

        while (!state.getLocations()[0].getName().equals(locationName)) {
            transitions = engine.getTransitions(system, state);
            transition = transitions.get((int) Math.floor(Math.random() * transitions.size()));

            for (SystemEdge edge : transition.getEdges()) {
                if (edge.getProcess().getName().equals("Spec")) {
                    stringBuilder.append(edge.getEdge().getPropertyValue("testcode") + "\n");
                    edge.getEdge().accept(dv);
                }
            }

            if (transition.getTarget().getLocations()[0].getLocation().getPropertyValue("testcodeEnter") != null) {
                stringBuilder.append(transition.getTarget().getLocations()[0].getLocation().getPropertyValue("testcodeEnter") + "\n");
            }

            state = transition.getTarget();
        }
        return stringBuilder.toString();
    }

}
