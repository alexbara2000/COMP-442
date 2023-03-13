package SemanticAnalysis;

import Common.AST;
import SemanticAnalysis.Table.SymbolTable;
import SyntaticAnalysis.Parser;

public class SemanticAnalyzer {
    public static void main(String[] args) throws Exception {
        String fileToParse = "example-bubblesort.src";
        Parser parser=new Parser(fileToParse);
        AST headNode = parser.parse();
        System.out.println(headNode);

        firstPass(headNode);
    }

    private static void firstPass(AST headNode) {
        SymbolTable sm = new SymbolTable(0, "global", null);
        System.out.println(sm);
    }

}
