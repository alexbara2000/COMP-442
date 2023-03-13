package Nodes;

import SemanticAnalysis.Visitor.Visitor;

import java.util.ArrayList;

public class StartNode extends Node{


    public StartNode(Node parent, ArrayList<Node> childrens, int depth) {
        super(parent, childrens, "start", depth);
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}
