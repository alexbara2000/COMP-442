package Nodes;

import SemanticAnalysis.Visitor.Visitor;

import java.util.ArrayList;

public class ReadNode extends Node{


    public ReadNode(Node parent, ArrayList<Node> childrens, int depth) {
        super(parent, childrens, "read", depth);
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}
