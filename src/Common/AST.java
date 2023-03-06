package Common;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Stack;

public class AST {
    AST parent;
    ArrayList<AST> childrens;
    Object concept;
    int depth;

    static Stack<AST> astStack = new Stack<>();

    public void setParent(AST parent) {
        this.parent = parent;
    }

    public int getDepth() {
        return depth;
    }

    public void setDepth(int depth) {
        this.depth = depth;
    }

    public AST(AST parent, ArrayList<AST> childrens,Object concept, int depth){
        this.parent = parent;
        this.childrens = childrens;
        this.concept = concept;
        this.depth = depth;
    }

    static public AST makeNull(){
        astStack.push(null);
        return null;
    }

    static public AST makeNode(Token concept){
        AST node = new AST(null, null, concept,  0);
        astStack.push(node);
        return node;
    }

    static public AST makeFamily(Object concept, int numOfPops){
        ArrayList<AST> childrens = new ArrayList<>();
        if(numOfPops != -1){
            for(int i = 0; i < numOfPops; i++){
                childrens.add(astStack.pop());
            }
        }
        else {
            while(astStack.peek() != null){
                childrens.add(astStack.pop());
            }
            astStack.pop();
        }
        AST parent = new AST(null, childrens, concept,  0);

        for (var child: parent.childrens){
            child.setParent(parent);
        }
        parent.updateDepth();

        Collections.reverse(childrens);

        astStack.push(parent);

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

    public static String treeToString(){
        return astStack.peek().toString();
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
}
