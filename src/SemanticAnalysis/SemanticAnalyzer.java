package SemanticAnalysis;

import Common.AST;
import Nodes.Node;
import SemanticAnalysis.Table.SymbolTable;
import SemanticAnalysis.Visitor.SymbolTableCreatorVisitor;
import SyntaticAnalysis.Parser;

public class SemanticAnalyzer {
    public static void main(String[] args) throws Exception {
        String fileToParse = "example-bubblesort.src";
        Parser parser=new Parser(fileToParse);
        Node headNode = parser.parse();
        System.out.println(headNode);

        SymbolTableCreatorVisitor tableCreatorVisitor = new SymbolTableCreatorVisitor();
        headNode.accept(tableCreatorVisitor);
    }

    private static void firstPass(Node headNode) {
        SymbolTable sm = new SymbolTable(0, "global", null);
        System.out.println(sm);
    }

}
