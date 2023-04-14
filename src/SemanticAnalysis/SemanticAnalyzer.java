package SemanticAnalysis;

import Common.Nodes.Node;
import SemanticAnalysis.Visitor.MemorySizeVisitor;
import SemanticAnalysis.Visitor.SymbolTableCreatorVisitor;
import SemanticAnalysis.Visitor.TypeCheckingVisitor;
import SyntaticAnalysis.Parser;

public class SemanticAnalyzer {

    String symbolTable = "";
    String semanticErrors = "";
    public Node headNode = null;
    public SemanticAnalyzer(String fileToParse) throws Exception {
        Parser parser = new Parser(fileToParse);
        headNode = parser.parse();
        if(headNode == null)
            return;

        SymbolTableCreatorVisitor tableCreatorVisitor = new SymbolTableCreatorVisitor();
        headNode.accept(tableCreatorVisitor);

        TypeCheckingVisitor typeCheckingVisitor = new TypeCheckingVisitor(tableCreatorVisitor.outSemanticErrors);
        headNode.accept(typeCheckingVisitor);

        MemorySizeVisitor memorySizeVisitor = new MemorySizeVisitor();
        headNode.accept(memorySizeVisitor);

        symbolTable = memorySizeVisitor.outSymbolTables;
        semanticErrors = typeCheckingVisitor.outSemanticErrors.toString();

    }
}
