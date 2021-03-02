import com.uppaal.engine.CannotEvaluateException;
import com.uppaal.engine.Engine;
import com.uppaal.engine.EngineException;
import com.uppaal.engine.Problem;
import com.uppaal.model.core2.*;
import com.uppaal.model.core2.lsc.*;
import com.uppaal.model.system.SystemEdge;
import com.uppaal.model.system.SystemEdgeSelect;
import com.uppaal.model.system.SystemLocation;
import com.uppaal.model.system.UppaalSystem;
import com.uppaal.model.system.symbolic.SymbolicState;
import com.uppaal.model.system.symbolic.SymbolicTransition;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;


public class Durum {
    URL url = new URL("https://raw.githubusercontent.com/GodSpiller/CAS/main/CAS_final(hopefully).xml");
    Engine engine = new Engine();
    Document document;
    UppaalSystem system;
    SymbolicState state;

    public Durum() throws IOException, EngineException, CannotEvaluateException {
        document = new PrototypeDocument().load(url);
        engine.setServerPath("C:\\Users\\Yann\\Desktop\\uppaal-4.1.24\\bin-Windows\\server.exe");
        engine.connect();
        ArrayList<Problem> problems = new ArrayList<Problem>();
        system = engine.getSystem(document, problems);
        state = engine.getInitialState(system);
    }

    public String getDurum(String location) throws Exception {

        ArrayList<SymbolicTransition> trans = engine.getTransitions(system,state);
        SymbolicTransition transition;

        StringBuilder stringBuilder = new StringBuilder();


        while (!state.getLocations()[0].getName().equals("LockedAndOpened")) {
            trans = engine.getTransitions(system, state);
            transition = trans.get((int) Math.floor(Math.random() * trans.size()));

            if (transition.getSource().getLocations()[0].getLocation().getPropertyValue("testcodeEnter") != null) {
                stringBuilder.append(transition.getSource().getLocations()[0].getLocation().getPropertyValue("testcodeEnter"));
            }

            for (SystemEdgeSelect edge : transition.getEdges()) {
                if (edge.getEdge().getTarget().getName().equals(transition.getTarget().getLocations()[0].getLocation().getName())) {
                    stringBuilder.append(transition.getEdges()[0].getEdge().getPropertyValue("testcode"));
                }
            }


            state = transition.getTarget();
        }
        return stringBuilder.toString();
    }

}
