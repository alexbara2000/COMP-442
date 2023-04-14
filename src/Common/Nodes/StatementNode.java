package Common.Nodes;

import Common.Visitor.Visitor;

import java.util.ArrayList;

public class StatementNode extends Node{


    public StatementNode(Node parent, ArrayList<Node> childrens, int depth) {
        super(parent, childrens, "statement", depth);
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}
