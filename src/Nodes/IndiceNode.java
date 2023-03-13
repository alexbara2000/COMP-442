package Nodes;

import SemanticAnalysis.Visitor.Visitor;

import java.util.ArrayList;

public class IndiceNode extends Node{


    public IndiceNode(Node parent, ArrayList<Node> childrens, int depth) {
        super(parent, childrens, "indice", depth);
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}
