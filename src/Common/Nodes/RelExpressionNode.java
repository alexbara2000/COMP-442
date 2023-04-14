package Common.Nodes;

import Common.Visitor.Visitor;

import java.util.ArrayList;

public class RelExpressionNode extends Node{


    public RelExpressionNode(Node parent, ArrayList<Node> childrens, int depth) {
        super(parent, childrens, "rel expression", depth);
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}
