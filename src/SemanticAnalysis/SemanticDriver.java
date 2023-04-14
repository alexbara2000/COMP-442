package SemanticAnalysis;

import Common.Nodes.Node;
import Common.Token.Token;
import Common.Token.TokenType;
import LexicalAnalysis.LexicalAnalyzer;
import SemanticAnalysis.Visitor.SymbolTableCreatorVisitor;
import SemanticAnalysis.Visitor.TypeCheckingVisitor;
import SyntaticAnalysis.Parser;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class SemanticDriver {
    public static void main(String[] args) throws Exception {
        List<String> files = Stream.of(Objects.requireNonNull(new File("Files/Source").listFiles()))
                .filter(file -> !file.isDirectory())
                .map(File::getName)
                .collect(Collectors.toList());

        for(var file : files){
            String fileName = file.substring(0, file.length() - 4);
            SemanticAnalyzer sa = new SemanticAnalyzer(file);

            FileWriter outSymbolTablesWriter = new FileWriter("Files/SymbolTables/"+ fileName +".outsymboltables");
            outSymbolTablesWriter.write(sa.symbolTable);
            outSymbolTablesWriter.close();

            FileWriter outSemanticErrorsWriter = new FileWriter("Files/SemanticErrors/"+ fileName +".outsemanticerrors");
            outSemanticErrorsWriter.write(sa.semanticErrors);
            outSemanticErrorsWriter.close();

        }
    }
}