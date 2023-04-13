package Common.Nodes;

import SemanticAnalysis.Visitor.Visitor;

import java.util.ArrayList;

public class ProgramNode extends Node{


    public ProgramNode(Node parent, ArrayList<Node> childrens, int depth) {
        super(parent, childrens, "Program", depth);
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}
