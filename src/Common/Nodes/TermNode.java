package Common.Nodes;

import Common.Visitor.Visitor;

import java.util.ArrayList;

public class TermNode extends Node{


    public TermNode(Node parent, ArrayList<Node> childrens, int depth) {
        super(parent, childrens, "term", depth);
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}
