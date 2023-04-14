package CodeGeneration;

import CodeGeneration.Visitor.CodeGenVisitor;
import SemanticAnalysis.SemanticAnalyzer;
import SemanticAnalysis.SemanticDriver;

public class CodeGeneration {

    String code = "";
    public CodeGeneration(String fileToParse) throws Exception {
        SemanticDriver.main(new String[]{fileToParse});
        if(SemanticDriver.headNode == null)
            return;

        CodeGenVisitor codeGenVisitor = new CodeGenVisitor();
        SemanticDriver.headNode.accept(codeGenVisitor);

        code += codeGenVisitor.execCode + "\n" + codeGenVisitor.dataCode;
    }
}
