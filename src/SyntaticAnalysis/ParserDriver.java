package SyntaticAnalysis;

import java.io.File;
import java.io.FileWriter;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ParserDriver {
    public static void main(String[] args) throws Exception {
        List<String> files = Stream.of(Objects.requireNonNull(new File("Files/Source").listFiles()))
                .filter(file -> !file.isDirectory())
                .map(File::getName)
                .collect(Collectors.toList());

        for(var file : files){
            Parser parser = new Parser(file);
            parser.parse();
            String fileName = file.substring(0, file.length() - 4);

            FileWriter outDerivationWriter = new FileWriter("Files/Derivation/"+ fileName +".outderivation");
            outDerivationWriter.write(parser.outDerivation.toString());
            outDerivationWriter.close();

            FileWriter outSyntaxErrorsWriter = new FileWriter("Files/SyntaxErrors/"+ fileName +".outsyntaxerrors");
            outSyntaxErrorsWriter.write(parser.outSyntaxErrors.toString());
            outSyntaxErrorsWriter.close();

            FileWriter outASTWriter = new FileWriter("Files/Ast/"+ fileName +".outast");
            outASTWriter.write(parser.outAST.toString());
            outASTWriter.close();
        }
    }
}