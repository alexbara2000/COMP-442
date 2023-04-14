import CodeGeneration.CodeGenerationDriver;
import LexicalAnalysis.LexDriver;
import SemanticAnalysis.SemanticDriver;
import SyntaticAnalysis.ParserDriver;

import java.io.File;
import java.util.Objects;

public class Driver {
    public static void main(String[] args) throws Exception {
        deleteDirectory(new File("Files"));

        if(args == null || args.length == 0){
            LexDriver.main(null);
            ParserDriver.main(null);
            SemanticDriver.main(null);
            CodeGenerationDriver.main(null);
        }
        else {
            for(var arg: args){
                CreateFiles(arg);
            }
        }

    }

    public static void deleteDirectory(File directory) {
        for (File file: Objects.requireNonNull(directory.listFiles())) {
            if (file.isDirectory() && !file.getName().equals("Source")) {
                deleteDirectory(file);
            } else {
                file.delete();
            }
        }
    }
    public static void CreateFiles(String fileName) throws Exception {
        LexDriver.main(new String[]{fileName});
        ParserDriver.main(new String[]{fileName});
        SemanticDriver.main(new String[]{fileName});
        CodeGenerationDriver.main(new String[]{fileName});
    }
}
