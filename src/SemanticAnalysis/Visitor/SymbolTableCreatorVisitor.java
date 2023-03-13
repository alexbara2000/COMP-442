package SemanticAnalysis.Visitor;

import Common.Token;
import Nodes.*;
import SemanticAnalysis.Table.*;

import java.util.ArrayList;
import java.util.Vector;

public class SymbolTableCreatorVisitor implements Visitor{
    @Override
    public void visit(ArgumentParamsNode node) {
        for (Node child : node.getChildren() ) {
            //make all children use this scopes' symbol table
            child.setTable(node.getTable());
            child.accept(this);
        }
    }

    @Override
    public void visit(ArithmeticExpressionNode node) {
        for (Node child : node.getChildren() ) {
            //make all children use this scopes' symbol table
            child.setTable(node.getTable());
            child.accept(this);
        }
    }

    @Override
    public void visit(ArraySizeNode node) {
        for (Node child : node.getChildren() ) {
            //make all children use this scopes' symbol table
            child.setTable(node.getTable());
            child.accept(this);
        }
    }

    @Override
    public void visit(ClassDefinitionNode node) {
        for (Node child : node.getChildren() ) {
            //make all children use this scopes' symbol table
            child.setTable(node.getTable());
            child.accept(this);
        }
    }

    @Override
    public void visit(ExpressionNode node) {
        for (Node child : node.getChildren() ) {
            //make all children use this scopes' symbol table
            child.setTable(node.getTable());
            child.accept(this);
        }
    }

    @Override
    public void visit(FactorNode node) {
        for (Node child : node.getChildren() ) {
            //make all children use this scopes' symbol table
            child.setTable(node.getTable());
            child.accept(this);
        }
    }

    @Override
    public void visit(FunctionBodyNode node) {
        for (Node child : node.getChildren() ) {
            //make all children use this scopes' symbol table
            child.setTable(node.getTable());
            child.accept(this);
        }
    }

    @Override
    public void visit(FunctionDefinitionNode node) {
//        String ftype = p_node.getChildren().get(0).getData();
//        String fname = p_node.getChildren().get(1).getData();
//        SymTab localtable = new SymTab(1,fname, p_node.m_symtab);
//        Vector<VarEntry> paramlist = new Vector<VarEntry>();
//        for (Node param : p_node.getChildren().get(2).getChildren()){
//            // parameter dimension list
//            Vector<Integer> dimlist = new Vector<Integer>();
//            for (Node dim : param.getChildren().get(2).getChildren()){
//                // parameter dimension
//                Integer dimval = Integer.parseInt(dim.getData());
//                dimlist.add(dimval);
//            }
//            paramlist.add((VarEntry) p_node.m_symtabentry);
//        }
//        p_node.m_symtabentry = new FuncEntry(ftype, fname, paramlist, localtable);
//        p_node.m_symtab.addEntry(p_node.m_symtabentry);
//        p_node.m_symtab = localtable;
        Token id =(Token)node.getChildren().get(0).getChildren().get(0).getConcept();
        ArrayList<Node> funcParams =node.getChildren().get(0).getChildren().get(1).getChildren();
        String returnType = ((Token)funcParams.get(funcParams.size()-1).getConcept()).getLexeme();
        String fname = id.getLexeme();
        SymbolTable localtable = new SymbolTable(1,fname, node.getTable());

        ArrayList<VarEntry> paramlist = new ArrayList<>();
        for (Node param : funcParams.get(0).getChildren()){
            // parameter dimension list
            ArrayList<Integer> dimlist = new ArrayList<>();
            for (Node dim : param.getChildren().get(2).getChildren()){
                // parameter dimension
                Integer dimval;
                if(((Token)dim.getConcept()).getLexeme().equals("epsilon")){
                    dimval = null;
                }
                else{
                    dimval = Integer.parseInt(((Token)dim.getConcept()).getLexeme());
                }
                dimlist.add(dimval);

            }
            paramlist.add((new VarEntry("function parameter", ((Token) param.getChildren().get(1).getConcept()).getLexeme(), ((Token) param.getChildren().get(0).getConcept()).getLexeme(), dimlist)));
        }


        node.setEntry(new FuncEntry(returnType, fname, paramlist, localtable));
        node.getTable().addEntry(node.getEntry());
        node.setTable(localtable);

        for (Node child : node.getChildren() ) {
            //make all children use this scopes' symbol table
            child.setTable(node.getTable());
            child.accept(this);
        }
    }

    @Override
    public void visit(FunctionHeadNode node) {
        SymbolTable functionTable = new SymbolTable(node.getTable().m_tablelevel+1, node.getChildren().get(0).toString(), node.getTable());
        node.setTable(functionTable);
        for (Node child : node.getChildren() ) {
            //make all children use this scopes' symbol table
            child.setTable(node.getTable());
            child.accept(this);
        }
    }

    @Override
    public void visit(FunctionHeadTailNode node) {
        for (Node child : node.getChildren() ) {
            //make all children use this scopes' symbol table
            child.setTable(node.getTable());
            child.accept(this);
        }
    }

    @Override
    public void visit(FunctionParamsNode node) {
        for (Node child : node.getChildren() ) {
            //make all children use this scopes' symbol table
            child.setTable(node.getTable());
            child.accept(this);
        }
    }

    @Override
    public void visit(FunctionParamsTailNode node) {
        for (Node child : node.getChildren() ) {
            //make all children use this scopes' symbol table
            child.setTable(node.getTable());
            child.accept(this);
        }
    }

    @Override
    public void visit(IdnestNode node) {
        for (Node child : node.getChildren() ) {
            //make all children use this scopes' symbol table
            child.setTable(node.getTable());
            child.accept(this);
        }
    }

    @Override
    public void visit(IfStatementNode node) {
        for (Node child : node.getChildren() ) {
            //make all children use this scopes' symbol table
            child.setTable(node.getTable());
            child.accept(this);
        }
    }

    @Override
    public void visit(IndiceNode node) {
        for (Node child : node.getChildren() ) {
            //make all children use this scopes' symbol table
            child.setTable(node.getTable());
            child.accept(this);
        }
    }

    @Override
    public void visit(InheritanceNode node) {
        for (Node child : node.getChildren() ) {
            //make all children use this scopes' symbol table
            child.setTable(node.getTable());
            child.accept(this);
        }
    }

    @Override
    public void visit(LocalVariableNode node) {
        // propagate accepting the same visitor to all the children
        // this effectively achieves Depth-First AST Traversal

        String vartype = ((Token)node.getChildren().get(2).getConcept()).getLexeme();
        String varid = ((Token)node.getChildren().get(1).getConcept()).getLexeme();
        ArrayList<Integer> dimlist = null;
        if (!(node.getChildren().get(3).getChildren().size() == 0)){
            dimlist = new ArrayList<>();
        }
        for (Node dim : node.getChildren().get(3).getChildren()){

            // parameter dimension
            Integer dimval;
            if(((Token)dim.getConcept()).getLexeme().equals("epsilon")){
                dimval=null;
            }
            else{
                dimval = Integer.parseInt(((Token)dim.getConcept()).getLexeme());
            }
            dimlist.add(dimval);
        }
        node.setEntry(new VarEntry("local", vartype, varid, dimlist));
        node.getTable().addEntry(node.getEntry());
        for (Node child : node.getChildren() ) {
            //make all children use this scopes' symbol table
            child.setTable(node.getTable());
            child.accept(this);
        }


    }

    @Override
    public void visit(MemberDeclarationNode node) {
        for (Node child : node.getChildren() ) {
            //make all children use this scopes' symbol table
            child.setTable(node.getTable());
            child.accept(this);
        }
    }

    @Override
    public void visit(MemberFunctionDeclarationNode node) {
        for (Node child : node.getChildren() ) {
            //make all children use this scopes' symbol table
            child.setTable(node.getTable());
            child.accept(this);
        }
    }

    @Override
    public void visit(MemberVariableDeclarationNode node) {
        for (Node child : node.getChildren() ) {
            //make all children use this scopes' symbol table
            child.setTable(node.getTable());
            child.accept(this);
        }
    }

    @Override
    public void visit(Node node) {
        for (Node child : node.getChildren()) {
            //make all children use this scopes' symbol table
            child.setTable(node.getTable());
            child.accept(this);
        }
    }

    @Override
    public void visit(ProgramNode node) {
        for (Node child : node.getChildren() ) {
            //make all children use this scopes' symbol table
            child.setTable(node.getTable());
            child.accept(this);
        }
    }

    @Override
    public void visit(ReadNode node) {
        for (Node child : node.getChildren() ) {
            //make all children use this scopes' symbol table
            child.setTable(node.getTable());
            child.accept(this);
        }
    }

    @Override
    public void visit(RecursiveArithmeticExpressionNode node) {
        for (Node child : node.getChildren() ) {
            //make all children use this scopes' symbol table
            child.setTable(node.getTable());
            child.accept(this);
        }
    }

    @Override
    public void visit(RecursiveTermNode node) {
        for (Node child : node.getChildren() ) {
            //make all children use this scopes' symbol table
            child.setTable(node.getTable());
            child.accept(this);
        }
    }

    @Override
    public void visit(RelExpressionNode node) {
        for (Node child : node.getChildren() ) {
            //make all children use this scopes' symbol table
            child.setTable(node.getTable());
            child.accept(this);
        }
    }

    @Override
    public void visit(RecursiveMemberDeclarationNode node) {
        for (Node child : node.getChildren() ) {
            //make all children use this scopes' symbol table
            child.setTable(node.getTable());
            child.accept(this);
        }
    }

    @Override
    public void visit(ReturnNode node) {
        for (Node child : node.getChildren() ) {
            //make all children use this scopes' symbol table
            child.setTable(node.getTable());
            child.accept(this);
        }
    }

    @Override
    public void visit(StartNode node) {
        SymbolTable sm = new SymbolTable(0, "global", null);
        node.setTable(sm);
        for (Node child : node.getChildren() ) {
            //make all children use this scopes' symbol table
            child.setTable(node.getTable());
            child.accept(this);
        }
        System.out.println(node.getTable());
    }

    @Override
    public void visit(StatementNode node) {
        for (Node child : node.getChildren() ) {
            //make all children use this scopes' symbol table
            child.setTable(node.getTable());
            child.accept(this);
        }
    }

    @Override
    public void visit(StatementIdnestNode node) {
        for (Node child : node.getChildren() ) {
            //make all children use this scopes' symbol table
            child.setTable(node.getTable());
            child.accept(this);
        }
    }

    @Override
    public void visit(TermNode node) {
        for (Node child : node.getChildren() ) {
            //make all children use this scopes' symbol table
            child.setTable(node.getTable());
            child.accept(this);
        }
    }

    @Override
    public void visit(VariableNode node) {
        for (Node child : node.getChildren() ) {
            //make all children use this scopes' symbol table
            child.setTable(node.getTable());
            child.accept(this);
        }
    }

    @Override
    public void visit(VariableIdnestNode node) {
        for (Node child : node.getChildren() ) {
            //make all children use this scopes' symbol table
            child.setTable(node.getTable());
            child.accept(this);
        }
    }

    @Override
    public void visit(WhileLoopNode node) {
        for (Node child : node.getChildren() ) {
            //make all children use this scopes' symbol table
            child.setTable(node.getTable());
            child.accept(this);
        }
    }

    @Override
    public void visit(WriteNode node) {
        for (Node child : node.getChildren() ) {
            //make all children use this scopes' symbol table
            child.setTable(node.getTable());
            child.accept(this);
        }
    }
}
