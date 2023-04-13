package Common.Nodes;

import SemanticAnalysis.Visitor.Visitor;

import java.util.ArrayList;

public class FunctionBodyNode extends Node{


    public FunctionBodyNode(Node parent, ArrayList<Node> childrens, int depth) {
        super(parent, childrens, "function body", depth);
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}
