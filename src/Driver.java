import CodeGeneration.CodeGenerationDriver;
import Common.Errors.ErrorLogger;
import LexicalAnalysis.LexDriver;
import SemanticAnalysis.SemanticDriver;
import SyntaticAnalysis.ParserDriver;

import java.io.File;
import java.io.FileWriter;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Driver {
    public static void main(String[] args) throws Exception {
        deleteDirectory(new File("Files"));

        List<String> files = Stream.of(Objects.requireNonNull(new File("Files/Source").listFiles()))
                .filter(file -> !file.isDirectory())
                .map(File::getName)
                .collect(Collectors.toList());

        if(args != null && args.length >= 1){
            files = List.of(args);
        }
        for(var file : files){
            CreateFiles(file);
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
        ErrorLogger.getInstance().deleteAll();
//        LexDriver.main(new String[]{fileName});
//        ParserDriver.main(new String[]{fileName});
//        SemanticDriver.main(new String[]{fileName});
        CodeGenerationDriver.main(new String[]{fileName});

        FileWriter outErrorWriter = new FileWriter("Files/Errors/"+ fileName +".outerrors");
        outErrorWriter.write(ErrorLogger.getInstance().getErrors());
        outErrorWriter.close();
    }
}
