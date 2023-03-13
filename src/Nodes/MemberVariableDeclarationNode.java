package Nodes;

import SemanticAnalysis.Visitor.Visitor;

import java.util.ArrayList;

public class MemberVariableDeclarationNode extends Node{


    public MemberVariableDeclarationNode(Node parent, ArrayList<Node> childrens, int depth) {
        super(parent, childrens, "member variable declaration", depth);
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}
