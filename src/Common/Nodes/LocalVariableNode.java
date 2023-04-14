package Common.Nodes;

import Common.Visitor.Visitor;

import java.util.ArrayList;

public class LocalVariableNode extends Node{


    public LocalVariableNode(Node parent, ArrayList<Node> childrens, int depth) {
        super(parent, childrens, "local variable", depth);
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}
