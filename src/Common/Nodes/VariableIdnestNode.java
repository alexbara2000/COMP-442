package Common.Nodes;

import SemanticAnalysis.Visitor.Visitor;

import java.util.ArrayList;

public class VariableIdnestNode extends Node{


    public VariableIdnestNode(Node parent, ArrayList<Node> childrens, int depth) {
        super(parent, childrens, "variable idnest", depth);
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}
