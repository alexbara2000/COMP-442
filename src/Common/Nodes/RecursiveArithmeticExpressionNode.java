package Common.Nodes;

import SemanticAnalysis.Visitor.Visitor;

import java.util.ArrayList;

public class RecursiveArithmeticExpressionNode extends Node{


    public RecursiveArithmeticExpressionNode(Node parent, ArrayList<Node> childrens, int depth) {
        super(parent, childrens, "recursive arithmetic expression", depth);
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}
