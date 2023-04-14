package SemanticAnalysis;

import Common.Nodes.Node;
import SemanticAnalysis.Visitor.MemorySizeVisitor;
import SemanticAnalysis.Visitor.SymbolTableCreatorVisitor;
import SemanticAnalysis.Visitor.TypeCheckingVisitor;
import SyntaticAnalysis.Parser;
import SyntaticAnalysis.ParserDriver;

public class SemanticAnalyzer {

    String symbolTable = "";
    public SemanticAnalyzer(String fileToParse) throws Exception {
        ParserDriver.main(new String[]{fileToParse});
        SemanticDriver.headNode = ParserDriver.headNode;
        if(ParserDriver.headNode == null)
            return;

        SymbolTableCreatorVisitor tableCreatorVisitor = new SymbolTableCreatorVisitor();
        ParserDriver.headNode.accept(tableCreatorVisitor);

        TypeCheckingVisitor typeCheckingVisitor = new TypeCheckingVisitor();
        ParserDriver.headNode.accept(typeCheckingVisitor);

        MemorySizeVisitor memorySizeVisitor = new MemorySizeVisitor();
        ParserDriver.headNode.accept(memorySizeVisitor);

        symbolTable = memorySizeVisitor.outSymbolTables;
    }
}
