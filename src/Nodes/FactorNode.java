package Nodes;

import SemanticAnalysis.Visitor.Visitor;

import java.util.ArrayList;

public class FactorNode extends Node{


    public FactorNode(Node parent, ArrayList<Node> childrens, int depth) {
        super(parent, childrens, "factor", depth);
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}
