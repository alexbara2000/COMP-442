package SemanticAnalysis;

import Common.Nodes.Node;
import SemanticAnalysis.Visitor.CodeGenVisitor;
import SemanticAnalysis.Visitor.MemorySizeVisitor;
import SemanticAnalysis.Visitor.SymbolTableCreatorVisitor;
import SemanticAnalysis.Visitor.TypeCheckingVisitor;
import SyntaticAnalysis.Parser;

public class SemanticAnalyzer {
    public static void main(String[] args) throws Exception {
        String fileToParse = "example-main13.src";
        Parser parser=new Parser(fileToParse);
        Node headNode = parser.parse();
        System.out.println(headNode);

        SymbolTableCreatorVisitor tableCreatorVisitor = new SymbolTableCreatorVisitor(fileToParse);
        headNode.accept(tableCreatorVisitor);

        TypeCheckingVisitor typeCheckingVisitor = new TypeCheckingVisitor(fileToParse);
        headNode.accept(typeCheckingVisitor);

        MemorySizeVisitor memorySizeVisitor = new MemorySizeVisitor(fileToParse);
        headNode.accept(memorySizeVisitor);

        CodeGenVisitor codeGenVisitor = new CodeGenVisitor(fileToParse);
        headNode.accept(codeGenVisitor);
    }
}
