package Common.Nodes;

import Common.Visitor.Visitor;

import java.util.ArrayList;

public class VariableNode extends Node{


    public VariableNode(Node parent, ArrayList<Node> childrens, int depth) {
        super(parent, childrens, "variable", depth);
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}
