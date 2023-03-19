package SemanticAnalysis;

import Common.AST;
import Nodes.Node;
import SemanticAnalysis.Table.SymbolTable;
import SemanticAnalysis.Visitor.SymbolTableCreatorVisitor;
import SemanticAnalysis.Visitor.TypeCheckingVisitor;
import SyntaticAnalysis.Parser;

public class SemanticAnalyzer {
    public static void main(String[] args) throws Exception {
        String fileToParse = "example-polynomial.src";
        Parser parser=new Parser(fileToParse);
        Node headNode = parser.parse();
        System.out.println(headNode);

        SymbolTableCreatorVisitor tableCreatorVisitor = new SymbolTableCreatorVisitor(fileToParse);
        headNode.accept(tableCreatorVisitor);

        TypeCheckingVisitor typeCheckingVisitor = new TypeCheckingVisitor(fileToParse);
        headNode.accept(typeCheckingVisitor);
    }

    private static void firstPass(Node headNode) {
        SymbolTable sm = new SymbolTable(0, "global", null);
        System.out.println(sm);
    }

}
