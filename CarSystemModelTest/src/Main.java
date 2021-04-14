import ast.nodes.BoundaryValue;
import modelhandler.GuardMaker;
import modelhandler.ModelHandler;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;

public class Main {

    public static void main(String[] args) throws Exception {

        ModelHandler modelHandler = new ModelHandler();

        modelHandler.createTestCode();

    }
}
