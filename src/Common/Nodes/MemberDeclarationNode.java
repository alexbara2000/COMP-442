package Common.Nodes;

import SemanticAnalysis.Visitor.Visitor;

import java.util.ArrayList;

public class MemberDeclarationNode extends Node{


    public MemberDeclarationNode(Node parent, ArrayList<Node> childrens, int depth) {
        super(parent, childrens, "member declaration", depth);
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}
