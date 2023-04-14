package Common.Nodes;

import Common.Visitor.Visitor;

import java.util.ArrayList;

public class ArgumentParamsNode extends Node{


    public ArgumentParamsNode(Node parent, ArrayList<Node> childrens, int depth) {
        super(parent, childrens, "argument params", depth);
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}
