package Common.Nodes;

import SemanticAnalysis.Visitor.Visitor;

import java.util.ArrayList;

public class IfStatementNode extends Node{


    public IfStatementNode(Node parent, ArrayList<Node> childrens, int depth) {
        super(parent, childrens, "if statement", depth);
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}
