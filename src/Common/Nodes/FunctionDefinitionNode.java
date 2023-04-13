package Common.Nodes;

import SemanticAnalysis.Visitor.Visitor;

import java.util.ArrayList;

public class FunctionDefinitionNode extends Node{


    public FunctionDefinitionNode(Node parent, ArrayList<Node> childrens, int depth) {
        super(parent, childrens, "function definition", depth);
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}
