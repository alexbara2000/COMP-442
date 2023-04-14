package Common.Nodes;

import Common.Visitor.Visitor;

import java.util.ArrayList;

public class IdnestNode extends Node{


    public IdnestNode(Node parent, ArrayList<Node> childrens, int depth) {
        super(parent, childrens, "idnest", depth);
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}
