package Nodes;

import Common.Token;
import SemanticAnalysis.Table.SymbolTable;
import SemanticAnalysis.Table.SymbolTableEntry;
import SemanticAnalysis.Visitor.Visitor;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Stack;

public class Node {
    Node parent;
    ArrayList<Node> childrens;
    Object concept;
    int depth;


    public Object getConcept() {
        return concept;
    }

    SymbolTable table = null;
    SymbolTableEntry entry = null;

    public SymbolTableEntry getEntry() {
        return entry;
    }

    public void setEntry(SymbolTableEntry entry) {
        this.entry = entry;
    }



    public void setParent(Node parent) {
        this.parent = parent;
    }
    public Node getParent() {
        return parent;
    }

    public int getDepth() {
        return depth;
    }

    public void setDepth(int depth) {
        this.depth = depth;
    }
    public void setChildren(ArrayList<Node> children) {
        this.childrens = children;
    }
    public ArrayList<Node> getChildren() {
        return childrens;
    }

    public void setTable(SymbolTable table) {
        this.table = table;
    }

    public SymbolTable getTable() {
        return table;
    }

    public Node(Node parent, ArrayList<Node> childrens, Object concept, int depth){
        this.parent = parent;
        this.childrens = childrens;
        this.concept = concept;
        this.depth = depth;
    }

    static public Node makeNode(Token concept, Stack<Node> nodeStack){
        Node node = new Node(null, null, concept,  0);
        nodeStack.push(node);
        return node;
    }

    static public Node makeFamily(Object concept, int numOfPops, Stack<Node> nodeStack){
        ArrayList<Node> childrens = new ArrayList<>();
        if(numOfPops != -1){
            for(int i = 0; i < numOfPops; i++){
                childrens.add(nodeStack.pop());
            }
        }
        else {
            while(nodeStack.peek() != null){
                childrens.add(nodeStack.pop());
            }
            nodeStack.pop();
        }
        Node parent = new Node(null, childrens, concept,  0);

        for (var child: parent.childrens){
            child.setParent(parent);
        }
        parent.updateDepth();

        Collections.reverse(childrens);

        nodeStack.push(parent);

        return parent;
    }

    public void updateDepth(){
        if(this.childrens == null)
            return;
        for (var child: this.childrens){
            child.setDepth(child.getDepth()+1);
            child.updateDepth();
        }
    }

    @Override
    public String toString() {
        StringBuilder tree = new StringBuilder();
        for(int i=0;i<depth; i++){
            tree.append("|  ");
        }
        tree.append(concept).append("\n");
        if(childrens != null){
            for(var subtree: childrens){
                tree.append(subtree.toString());
            }
        }
        return tree.toString();
    }

    public void accept(Visitor p_visitor) {
        p_visitor.visit(this);
    }
}



