package SemanticAnalysis.Visitor;

import Common.Token;
import Common.TokenType;
import Nodes.*;
import SemanticAnalysis.Table.*;

import javax.xml.crypto.Data;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Vector;

public class SymbolTableCreatorVisitor implements Visitor{
    FileWriter outSemanticErrorsWriter;
    FileWriter outSymbolTablesWriter;
    Node headNode = null;

    String currentClass = "";

    public SymbolTableCreatorVisitor(String path) throws IOException {
        String pathPrefix = path.split("\\.")[0];
        outSemanticErrorsWriter = new FileWriter(pathPrefix+".outsemanticerrors");
        outSymbolTablesWriter = new FileWriter(pathPrefix+".outsymboltables");
    }

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
        currentClass = classname;
        int location = ((Token)node.getChildren().get(0).getConcept()).getLocation();
        SymbolTable localtable = new SymbolTable(1,classname, node.getTable());
        ClassEntry tempClassEntry = new ClassEntry(classname, localtable);
        if(node.getTable().lookupLocalEntry(tempClassEntry)){
            try {
                outSemanticErrorsWriter.write("SEMANTIC ERRORS: multiply declared classes at line: " +location+"\n");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        node.setEntry(tempClassEntry);
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
        boolean isConstructor = false;
        String memberClass = "";
        String fname ="";
        String fType = "";
        int location = 0;
        ArrayList<VarEntry> fParams= new ArrayList<>();

        try{
            Token secondParam = ((Token)fundHead.getChildren().get(1).getConcept());
            if(secondParam.getType() == TokenType.ID){
                memberClass = ((Token)fundHead.getChildren().get(0).getConcept()).getLexeme();
                fname = ((Token)fundHead.getChildren().get(1).getConcept()).getLexeme();
                location = ((Token)fundHead.getChildren().get(1).getConcept()).getLocation();
                isMemberFunction = true;
            }
            if(secondParam.getType() == TokenType.CONSTRUCTOR){
                isConstructor = true;
                fname = "build";
                memberClass = ((Token)fundHead.getChildren().get(0).getConcept()).getLexeme();
                location = ((Token)fundHead.getChildren().get(0).getConcept()).getLocation();
            }
        }
        catch (Exception e){
        }
        if(!isMemberFunction && !isConstructor){
            location = ((Token)fundHead.getChildren().get(0).getConcept()).getLocation();
            fname = ((Token)fundHead.getChildren().get(0).getConcept()).getLexeme();
        }

        if(!isConstructor)
            fType = ((Token)fundHead.getChildren().get(fundHead.getChildren().size()-1).getConcept()).getLexeme();

        
        // get params list
        Node params = null;
        boolean hasParams = false;
        if((isMemberFunction || isConstructor) && fundHead.getChildren().get(2).getConcept().equals("function params")){
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
        SymbolTable localTable;
        FuncEntry newFuncEntry;

        if(isConstructor){
            localTable = new SymbolTable(node.getTable().m_tablelevel+1,"Constructor", node.getTable());
            newFuncEntry = new FuncEntry("Constructor", fType, "build", fParams, localTable, memberClass);
        }
        else {
            localTable = new SymbolTable(node.getTable().m_tablelevel+1,fname, node.getTable());
            newFuncEntry = new FuncEntry("Function", fType,fname, fParams, localTable, memberClass);
        }

        if(node.getTable().lookupLocalEntry(newFuncEntry)){
            if(headNode.getTable().hasSameParams(newFuncEntry)){
                try {
                    outSemanticErrorsWriter.write("SEMANTIC ERRORS: multiple declared functions at line: " +location +"\n");
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            else {
                try {
                    outSemanticErrorsWriter.write("SEMANTIC WARNING: two functions with the same name but with different parameter lists: " +location + "\n");
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }

        }
        node.setEntry(newFuncEntry);
        node.getTable().addEntry(node.getEntry());
        node.setTable(localTable);

        for(var fparam: fParams){
            node.getTable().addEntry(fparam);
        }


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
        String tableName = node.getTable().m_name;

        if(idNames.size() != 0){
            for(var id: idNames){
                var iList = headNode.getTable().getInheritanceList(id);
                if(iList != null && iList.size() != 0 && iList.contains(tableName)){
                    try {
                        outSemanticErrorsWriter.write("SEMANTIC ERRORS: circular dependency: " +((Token) node.getChildren().get(0).getConcept()).getLocation() + "\n");
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
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
        int location = ((Token)node.getChildren().get(1).getConcept()).getLocation();
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
        VarEntry tempVarEntry = new VarEntry("local", vartype, varid, dimlist);
        if(node.getTable().lookupLocalEntryName(tempVarEntry)){
            try {
                outSemanticErrorsWriter.write("SEMANTIC ERRORS: multiply declared local variables at line: " +location + "\n");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        else {
            node.setEntry(tempVarEntry);
            node.getTable().addEntry(node.getEntry());
        }

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


            FuncEntry newFuncEntry;
            if(kind.equals("constructor")){
                newFuncEntry = new MemberFuncEntry(kind, type, fname, fParams, visibility, null,currentClass, false);
            }
            else{
                newFuncEntry = new MemberFuncEntry(kind, type,fname, fParams, visibility, null, currentClass,false);
            }

            SymbolTable localtable = headNode.getTable().getTableEntry(newFuncEntry);
            if(localtable == null){
                try {
                    outSemanticErrorsWriter.write("SEMANTIC ERROR: Use of undefined member function  " +((Token)node.getChildren().get(0).getConcept()).getLocation() + "\n");
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            else{
                localtable.m_tablelevel = 2;
                newFuncEntry.m_subtable = localtable;
            }


            node.setEntry(newFuncEntry);
            node.getTable().addEntry(node.getEntry());
            node.setTable(localtable);


        }
        else{
            //Data declaration
            String dname =((Token)node.getChildren().get(1).getChildren().get(0).getConcept()).getLexeme();
            int location =((Token)node.getChildren().get(1).getChildren().get(0).getConcept()).getLocation();
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

            DataEntry tempDataEntry = new DataEntry(dkind, dtype, dname, dims, visibility);
            if(headNode.getTable().isDataMember(dname)){
                try {
                    outSemanticErrorsWriter.write("SEMANTIC Warning: Shadow inheritance of data members: " +location + "\n");
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            if(node.getTable().lookupInheritedEntry(tempDataEntry)){
                try {
                    outSemanticErrorsWriter.write("SEMANTIC ERRORS: multiply declared data member at line: " +location + "\n");
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            else {
                node.setEntry(tempDataEntry);
                node.getTable().addEntry(node.getEntry());
            }
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
        headNode = node;
        SymbolTable sm = new SymbolTable(0, "global", null);
        node.setTable(sm);
        for (Node child : node.getChildren() ) {
            //make all children use this scopes' symbol table
            child.setTable(node.getTable());
            child.accept(this);
        }
        System.out.println(node.getTable());
        try {
            outSymbolTablesWriter.write(node.getTable().toString());
            outSymbolTablesWriter.close();
            outSemanticErrorsWriter.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
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
