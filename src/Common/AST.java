package Common;

import java.util.ArrayList;
import java.util.List;

public class AST {
    AST parent;
    ArrayList<AST> childrens;
    SemanticConcepts concept;
    int depth;

    public AST getParent() {
        return parent;
    }

    public void setParent(AST parent) {
        this.parent = parent;
    }

    public ArrayList<AST> getChildrens() {
        return childrens;
    }

    public void setChildrens(ArrayList<AST> childrens) {
        this.childrens = childrens;
    }

    public SemanticConcepts getConcept() {
        return concept;
    }

    public void setConcept(SemanticConcepts concept) {
        this.concept = concept;
    }

    public int getDepth() {
        return depth;
    }

    public void setDepth(int depth) {
        this.depth = depth;
    }

    public AST(AST parent, ArrayList<AST> childrens,SemanticConcepts concept, int depth){
        this.parent = parent;
        this.childrens = childrens;
        this.concept = concept;
        this.depth = depth;
    }

    static public AST makeNode(){
        return new AST(null, null, null,  0);
    }
    static public AST makeNode(SemanticConcepts concept){
        return new AST(null, null, concept,  0);
    }
    static public AST makeNode(SemanticConcepts concept, ArrayList<AST> childrens){
        AST parent = new AST(null, childrens, concept,  0);

        for (var child: parent.childrens){
            child.setParent(parent);
        }

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
            tree.append("|");
        }
        tree.append(concept+"\n");
        if(childrens != null){
            for(var subtree: childrens){
                tree.append(subtree.toString());
            }
        }

        return tree.toString();
    }

    public static void main(String[] args){
//        AST first = AST.makeNode(new Token(TokenType.FLOAT, "Temp", 1));
//        AST second = AST.makeNode(new Token(TokenType.INTNUM, "324", 1));
//        AST parent = AST.makeNode(new Token(TokenType.FUNCTION, "Start", 2));
//        first.setDepth(1);
//        second.setDepth(1);
//        ArrayList<AST> childs = new ArrayList<AST>();
//        childs.add(first);
//        childs.add(second);
//        parent.setChildrens(childs);
//
//        System.out.println(parent);
    }
}
