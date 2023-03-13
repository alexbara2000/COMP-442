package SemanticAnalysis.Actions;

import SemanticAnalysis.Visitor.Visitor;

public class ReturnAction implements SemanticAction{

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    @Override
    public String toString() {
        return "return";
    }
}
