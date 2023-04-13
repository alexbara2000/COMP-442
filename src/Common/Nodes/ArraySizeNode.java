package Common.Nodes;

import SemanticAnalysis.Visitor.Visitor;

import java.util.ArrayList;

public class ArraySizeNode extends Node{


    public ArraySizeNode(Node parent, ArrayList<Node> childrens, int depth) {
        super(parent, childrens, "array size", depth);
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}
