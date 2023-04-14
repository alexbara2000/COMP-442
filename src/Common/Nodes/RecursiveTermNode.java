package Common.Nodes;

import Common.Visitor.Visitor;

import java.util.ArrayList;

public class RecursiveTermNode extends Node{


    public RecursiveTermNode(Node parent, ArrayList<Node> childrens, int depth) {
        super(parent, childrens, "recursive term", depth);
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}
