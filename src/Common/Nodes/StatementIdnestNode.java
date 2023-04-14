package Common.Nodes;

import Common.Visitor.Visitor;

import java.util.ArrayList;

public class StatementIdnestNode extends Node{


    public StatementIdnestNode(Node parent, ArrayList<Node> childrens, int depth) {
        super(parent, childrens, "statement idnest", depth);
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}
