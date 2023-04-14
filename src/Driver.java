import CodeGeneration.CodeGenerationDriver;
import LexicalAnalysis.LexDriver;
import SemanticAnalysis.SemanticDriver;
import SyntaticAnalysis.ParserDriver;

public class Driver {
    public static void main(String[] args) throws Exception {
        LexDriver.main(null);
        ParserDriver.main(null);
        SemanticDriver.main(null);
        CodeGenerationDriver.main(null);
    }
}
