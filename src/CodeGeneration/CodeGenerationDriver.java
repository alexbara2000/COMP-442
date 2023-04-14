package CodeGeneration;

import SemanticAnalysis.SemanticAnalyzer;

import java.io.File;
import java.io.FileWriter;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CodeGenerationDriver {
    public static void main(String[] args) throws Exception {
        List<String> files = Stream.of(Objects.requireNonNull(new File("Files/Source").listFiles()))
                .filter(file -> !file.isDirectory())
                .map(File::getName)
                .collect(Collectors.toList());

        if(args != null && args.length >= 1){
            files = List.of(args);
        }
        for(var file : files){
            String fileName = file.substring(0, file.length() - 4);
            CodeGeneration codeGeneration = new CodeGeneration(file);

            FileWriter outSymbolTablesWriter = new FileWriter("Files/Moon/"+ fileName +".m");
            outSymbolTablesWriter.write(codeGeneration.code);
            outSymbolTablesWriter.close();

        }
    }
}