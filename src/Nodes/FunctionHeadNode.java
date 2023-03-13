package Nodes;

import SemanticAnalysis.Visitor.Visitor;

import java.util.ArrayList;

public class FunctionHeadNode extends Node{


    public FunctionHeadNode(Node parent, ArrayList<Node> childrens, int depth) {
        super(parent, childrens, "function head", depth);
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}
