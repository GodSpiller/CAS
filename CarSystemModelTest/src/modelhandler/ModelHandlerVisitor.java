package modelhandler;

import com.uppaal.model.core2.*;
import com.uppaal.model.core2.lsc.*;

public class ModelHandlerVisitor implements Visitor {
    public StringBuilder testCode = new StringBuilder();

    @Override
    public void visitElement(Element element) throws Exception {

    }

    @Override
    public void visitNode(Node node) throws Exception {

    }

    @Override
    public void visitProperty(Property property) throws Exception {

    }

    @Override
    public void visitDocument(Document document) throws Exception {

    }

    @Override
    public void visitTemplate(AbstractTemplate abstractTemplate) throws Exception {

    }

    @Override
    public void visitLocation(Location location) throws Exception {
        if (!location.getPropertyValue("testcodeEnter").equals("")) {
            testCode.append(location.getPropertyValue("testcodeEnter") + "\n");
        }
    }

    @Override
    public void visitBranchPoint(BranchPoint branchPoint) throws Exception {

    }

    @Override
    public void visitEdge(Edge edge) throws Exception {
        if (!edge.getPropertyValue("testcode").toString().equals("")) {
            testCode.append(edge.getPropertyValue("testcode") + "\n");
        }
    }

    @Override
    public void visitNail(Nail nail) throws Exception {

    }

    @Override
    public void visitInstanceLine(InstanceLine instanceLine) throws Exception {

    }

    @Override
    public void visitPrechart(Prechart prechart) throws Exception {

    }

    @Override
    public void visitMessage(Message message) throws Exception {

    }

    @Override
    public void visitUpdate(Update update) throws Exception {

    }

    @Override
    public void visitCondition(Condition condition) throws Exception {

    }

    @Override
    public void visitCut(Cut cut) throws Exception {

    }

    @Override
    public void visitQueries(QueryList queryList) throws Exception {

    }

    @Override
    public void visitQuery(Query query) throws Exception {

    }

    public StringBuilder getStringBuilder() {
        StringBuilder temp = testCode;
        testCode = new StringBuilder();
        return temp;
    }
}
