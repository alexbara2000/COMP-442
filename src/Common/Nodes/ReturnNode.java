package Common.Nodes;

import SemanticAnalysis.Visitor.Visitor;

import java.util.ArrayList;

public class ReturnNode extends Node{


    public ReturnNode(Node parent, ArrayList<Node> childrens, int depth) {
        super(parent, childrens, "return", depth);
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}
