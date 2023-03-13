package Nodes;

import SemanticAnalysis.Visitor.Visitor;

import java.util.ArrayList;

public class FunctionParamsNode extends Node{


    public FunctionParamsNode(Node parent, ArrayList<Node> childrens, int depth) {
        super(parent, childrens, "function params", depth);
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}
