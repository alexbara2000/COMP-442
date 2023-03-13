package Nodes;

import SemanticAnalysis.Visitor.Visitor;

import java.util.ArrayList;

public class WriteNode extends Node{


    public WriteNode(Node parent, ArrayList<Node> childrens, int depth) {
        super(parent, childrens, "write", depth);
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}
