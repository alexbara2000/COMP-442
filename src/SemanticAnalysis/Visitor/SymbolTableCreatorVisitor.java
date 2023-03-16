package SemanticAnalysis.Visitor;

import Common.Token;
import Common.TokenType;
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
        String classname = ((Token)node.getChildren().get(0).getConcept()).getLexeme();
        SymbolTable localtable = new SymbolTable(1,classname, node.getTable());
        node.setEntry( new ClassEntry(classname, localtable));
        node.getTable().addEntry(node.getEntry());
        node.setTable(localtable);

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

        Node fundHead = node.getChildren().get(0);
        boolean isMemberFunction = false;
        String memberClass = "";
        String fname ="";
        String fType = "";
        ArrayList<VarEntry> fParams= new ArrayList<>();

        try{
            Token secondParam = ((Token)fundHead.getChildren().get(1).getConcept());
            if(secondParam.getType() == TokenType.ID){
                memberClass = ((Token)fundHead.getChildren().get(0).getConcept()).getLexeme();
                fname = ((Token)fundHead.getChildren().get(1).getConcept()).getLexeme();
                isMemberFunction = true;
            }
        }
        catch (Exception e){
            //free function
        }
        if(!isMemberFunction){
            fname = ((Token)fundHead.getChildren().get(0).getConcept()).getLexeme();
        }
        // TODO check for constructor
        fType = ((Token)fundHead.getChildren().get(fundHead.getChildren().size()-1).getConcept()).getLexeme();

        
        // get params list
        Node params = null;
        boolean hasParams = false;
        if(isMemberFunction && fundHead.getChildren().get(2).getConcept().equals("function params")){
            hasParams = true;
            params = fundHead.getChildren().get(2);
        }
        if(!isMemberFunction && fundHead.getChildren().get(1).getConcept().equals("function params")){
            hasParams = true;
            params = fundHead.getChildren().get(1);
        }
        if(hasParams){
            for(var entry: params.getChildren()){
                ArrayList<Integer> dims = new ArrayList<>();
                String innerType = ((Token)entry.getChildren().get(1).getConcept()).getLexeme();
                String innerName = ((Token)entry.getChildren().get(0).getConcept()).getLexeme();

                for(var arraySize: entry.getChildren().get(2).getChildren()){
                    if(((Token)arraySize.getConcept()).getLexeme().equals("epsilon")){
                        dims.add(null);
                    }
                    else {
                        dims.add(Integer.parseInt(((Token)arraySize.getConcept()).getLexeme()));
                    }
                }
                fParams.add(new VarEntry("param", innerType, innerName, dims));
            }
        }

        SymbolTable localtable = new SymbolTable(node.getTable().m_tablelevel+1,fname, node.getTable());

        FuncEntry newFuncEntry = new FuncEntry(fType,fname, fParams, localtable, memberClass);


        node.setEntry(newFuncEntry);
        node.getTable().addEntry(node.getEntry());
        node.setTable(localtable);


//        Token id =(Token)node.getChildren().get(0).getChildren().get(0).getConcept();
//        ArrayList<Node> funcParams =node.getChildren().get(0).getChildren().get(1).getChildren();
//        String returnType;
//        try{
//            returnType = ((Token)funcParams.get(funcParams.size()-1).getConcept()).getLexeme();
//        }
//        catch (Exception e){
//            returnType = null;
//        }
//        String fname = id.getLexeme();
//        SymbolTable localtable = new SymbolTable(node.getTable().m_tablelevel+1,fname, node.getTable());
//
//        ArrayList<VarEntry> paramlist = new ArrayList<>();
//        for (Node param : funcParams.get(0).getChildren()){
//            // parameter dimension list
//            ArrayList<Integer> dimlist = new ArrayList<>();
//            for (Node dim : param.getChildren().get(2).getChildren()){
//                // parameter dimension
//                Integer dimval;
//                if(((Token)dim.getConcept()).getLexeme().equals("epsilon")){
//                    dimval = null;
//                }
//                else{
//                    dimval = Integer.parseInt(((Token)dim.getConcept()).getLexeme());
//                }
//                dimlist.add(dimval);
//
//            }
//            paramlist.add((new VarEntry("function parameter", ((Token) param.getChildren().get(1).getConcept()).getLexeme(), ((Token) param.getChildren().get(0).getConcept()).getLexeme(), dimlist)));
//        }
//
//
//        node.setEntry(new FuncEntry(returnType, fname, paramlist, localtable));
//        node.getTable().addEntry(node.getEntry());
//        node.setTable(localtable);

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
        ArrayList<String> idNames = new ArrayList<>();
        for(Node idNode: node.getChildren()){
            idNames.add(((Token)idNode.getConcept()).getLexeme());
        }
        node.getTable().addEntry(new InheritEntry(idNames, null));
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
        if(node.getChildren().get(3).getConcept().equals("argument params")){
            //localvar for function
        }
        else{
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
        String visibility = ((Token)node.getChildren().get(0).getConcept()).getLexeme();
        String memberType = node.getChildren().get(1).getConcept().toString();
        if(memberType.equals("member function declaration")){
            ArrayList<Node> functionDeclaration = node.getChildren().get(1).getChildren();
            String fname;
            String kind;
            String type = "";

            try{
                //this means it is not a constructor
                kind = "function";
                fname =((Token)functionDeclaration.get(1).getConcept()).getLexeme();
                type = ((Token)functionDeclaration.get(3).getConcept()).getLexeme();
            }
            catch (Exception e){
                //this is the constructor
                kind = "constructor";
                fname = "build";
                type = node.getTable().m_name;
            }

            //Getting the var entry with the dim size
            ArrayList<VarEntry> fParams = new ArrayList<>();

            try{
                Node params;
                if(kind.equals("constructor")){
                    params = functionDeclaration.get(1);
                }
                else{
                    params = functionDeclaration.get(2);
                }
                for(var entry: params.getChildren()){
                    ArrayList<Integer> dims = new ArrayList<>();
                    String innerType = ((Token)entry.getChildren().get(1).getConcept()).getLexeme();
                    String innerName = ((Token)entry.getChildren().get(0).getConcept()).getLexeme();

                    for(var arraySize: entry.getChildren().get(2).getChildren()){
                        if(((Token)arraySize.getConcept()).getLexeme().equals("epsilon")){
                            dims.add(null);
                        }
                        else {
                            dims.add(Integer.parseInt(((Token)arraySize.getConcept()).getLexeme()));
                        }
                    }

                    fParams.add(new VarEntry("param", innerType, innerName, dims));
                }
            }
            catch (Exception e){
             // there are no params for the function
            }

            SymbolTable localtable;
            if(kind.equals("constructor")){
                localtable = new SymbolTable(node.getTable().m_tablelevel+1,"function", node.getTable());
            }
            else{
                localtable = new SymbolTable(node.getTable().m_tablelevel+1,fname, node.getTable());
            }

            FuncEntry newFuncEntry;
            if(kind.equals("constructor")){
                newFuncEntry = new MemberConstructorEntry(kind, type, fname, fParams, visibility, localtable);
            }
            else{
                newFuncEntry = new MemberFuncEntry(kind, type,fname, fParams, visibility, localtable);
            }


            node.setEntry(newFuncEntry);
            node.getTable().addEntry(node.getEntry());
            node.setTable(localtable);

            for(var fparam: fParams){
                node.getTable().addEntry(fparam);
            }
            if (kind.equals("constructor")){
                node.getTable().addEntry(new VarEntry("local", "void", "result", null));
            }
            else {
                node.getTable().addEntry(new VarEntry("local", type, "result", null));
            }
        }
        else{
            //Data declaration
            String dname =((Token)node.getChildren().get(1).getChildren().get(0).getConcept()).getLexeme();
            String dtype =((Token)node.getChildren().get(1).getChildren().get(1).getConcept()).getLexeme();
            String dkind = "data";

            ArrayList<Integer> dims = new ArrayList<>();

            for(var arraySize: node.getChildren().get(1).getChildren().get(2).getChildren()){
                if(((Token)arraySize.getConcept()).getLexeme().equals("epsilon")){
                    dims.add(null);
                }
                else {
                    dims.add(Integer.parseInt(((Token)arraySize.getConcept()).getLexeme()));
                }
            }

            node.setEntry(new DataEntry(dkind, dtype, dname, dims, visibility));
            node.getTable().addEntry(node.getEntry());
        }


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
