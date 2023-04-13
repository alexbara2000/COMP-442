package Common.Nodes;

import SemanticAnalysis.Visitor.Visitor;

import java.util.ArrayList;

public class MemberFunctionDeclarationNode extends Node{


    public MemberFunctionDeclarationNode(Node parent, ArrayList<Node> childrens, int depth) {
        super(parent, childrens, "member function declaration", depth);
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}
