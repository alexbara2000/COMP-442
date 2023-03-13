package Nodes;

import SemanticAnalysis.Visitor.Visitor;

import java.util.ArrayList;

public class FunctionParamsTailNode extends Node{


    public FunctionParamsTailNode(Node parent, ArrayList<Node> childrens, int depth) {
        super(parent, childrens, "function params tail", depth);
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}
