package CodeGeneration;

import CodeGeneration.Visitor.CodeGenVisitor;
import SemanticAnalysis.SemanticAnalyzer;

public class CodeGeneration {

    String code = "";
    public CodeGeneration(String fileToParse) throws Exception {
        SemanticAnalyzer sa = new SemanticAnalyzer(fileToParse);
        if(sa.headNode == null)
            return;

        CodeGenVisitor codeGenVisitor = new CodeGenVisitor();
        sa.headNode.accept(codeGenVisitor);

        code += codeGenVisitor.execCode + "\n" + codeGenVisitor.dataCode;
    }
}
