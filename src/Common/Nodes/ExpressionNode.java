package Common.Nodes;

import SemanticAnalysis.Visitor.Visitor;

import java.util.ArrayList;

public class ExpressionNode extends Node{


    public ExpressionNode(Node parent, ArrayList<Node> childrens, int depth) {
        super(parent, childrens, "expression", depth);
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}
