package Common.Nodes;

import Common.Visitor.Visitor;

import java.util.ArrayList;

public class FunctionHeadTailNode extends Node{


    public FunctionHeadTailNode(Node parent, ArrayList<Node> childrens, int depth) {
        super(parent, childrens, "function head tail", depth);
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}
