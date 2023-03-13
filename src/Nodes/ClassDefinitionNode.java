package Nodes;

import SemanticAnalysis.Visitor.Visitor;

import java.util.ArrayList;

public class ClassDefinitionNode extends Node{


    public ClassDefinitionNode(Node parent, ArrayList<Node> childrens, int depth) {
        super(parent, childrens, "class definition", depth);
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}
