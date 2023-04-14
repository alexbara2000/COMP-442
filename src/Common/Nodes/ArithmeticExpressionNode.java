package Common.Nodes;

import Common.Visitor.Visitor;

import java.util.ArrayList;

public class ArithmeticExpressionNode extends Node{


    public ArithmeticExpressionNode(Node parent, ArrayList<Node> childrens, int depth) {
        super(parent, childrens, "arithmetic expression", depth);
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}
