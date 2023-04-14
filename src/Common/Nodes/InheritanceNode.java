package Common.Nodes;

import Common.Visitor.Visitor;

import java.util.ArrayList;

public class InheritanceNode extends Node{


    public InheritanceNode(Node parent, ArrayList<Node> childrens, int depth) {
        super(parent, childrens, "inheritance", depth);
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}
