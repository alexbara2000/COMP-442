package Nodes;

import SemanticAnalysis.Visitor.Visitor;

import java.util.ArrayList;

public class RecursiveMemberDeclarationNode extends Node{


    public RecursiveMemberDeclarationNode(Node parent, ArrayList<Node> childrens, int depth) {
        super(parent, childrens, "recursive member declaration", depth);
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}
