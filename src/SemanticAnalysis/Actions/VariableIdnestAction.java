package SemanticAnalysis.Actions;

import SemanticAnalysis.Visitor.Visitor;

public class VariableIdnestAction implements SemanticAction{

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    @Override
    public String toString() {
        return "variable idnest";
    }
}
