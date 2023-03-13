package Nodes;

import SemanticAnalysis.Visitor.Visitor;

import java.util.ArrayList;

public class WhileLoopNode extends Node{


    public WhileLoopNode(Node parent, ArrayList<Node> childrens, int depth) {
        super(parent, childrens, "while loop", depth);
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}
