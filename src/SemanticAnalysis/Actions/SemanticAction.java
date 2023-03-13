package SemanticAnalysis.Actions;

import SemanticAnalysis.Visitor.Visitor;

public interface SemanticAction {
    public void accept(Visitor visitor);
}
