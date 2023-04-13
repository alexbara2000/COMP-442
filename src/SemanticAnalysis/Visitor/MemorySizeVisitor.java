package SemanticAnalysis.Visitor;

import Common.Token.Token;
import Common.Token.TokenType;
import Common.Nodes.*;
import SemanticAnalysis.Table.LitValEntry;
import SemanticAnalysis.Table.SymbolTable;

import java.io.FileWriter;
import java.io.IOException;

public class MemorySizeVisitor implements Visitor{
    Node headNode = null;
    int offset = 0;
    FileWriter outMoonCode;
    int currStatBlock =1;
    public MemorySizeVisitor(String path) throws IOException {
        String pathPrefix = path.split("\\.")[0];
        outMoonCode = new FileWriter(pathPrefix+".m");
    }
    @Override
    public void visit(ArgumentParamsNode node) {
        for (Node child : node.getChildren() ) {
            //make all children use this scopes' symbol table
            child.accept(this);
        }
    }

    @Override
    public void visit(ArithmeticExpressionNode node) {
        for (Node child : node.getChildren() ) {
            //make all children use this scopes' symbol table
            child.accept(this);
        }
//        var currType = node.getChildren().get(0).getEntry().m_type;
//        for(var childs: node.getChildren()){
//            if(childs.getConcept() instanceof Token)
//                node.getTable().addEntry(new TempVarEntry(currType));
//        }
    }

    @Override
    public void visit(ArraySizeNode node) {
        for (Node child : node.getChildren() ) {
            //make all children use this scopes' symbol table
            child.accept(this);
        }
    }

    @Override
    public void visit(ClassDefinitionNode node) {
        for (Node child : node.getChildren() ) {
            //make all children use this scopes' symbol table
            child.accept(this);
        }
        var table = node.getEntry().m_subtable;
        var size = 0;
        for(var entry: table.m_symlist){
            size+=entry.m_size;
        }
        node.getEntry().m_size = size;
    }

    @Override
    public void visit(ExpressionNode node) {
        for (Node child : node.getChildren() ) {
            //make all children use this scopes' symbol table
            child.accept(this);
        }
    }

    @Override
    public void visit(FactorNode node) {
        for (Node child : node.getChildren() ) {
            //make all children use this scopes' symbol table
            child.accept(this);
        }
        if(node.getChildren() != null && node.getChildren().size() >= 1)
            if(node.getChildren().get(0).getConcept() instanceof Token){
                var factorType = ((Token)node.getChildren().get(0).getConcept()).getType();
                var factorname = ((Token)node.getChildren().get(0).getConcept()).getLexeme();
                if(factorType == TokenType.ID){
                    node.setEntry(node.getTable().lookupName(factorname));
                    node.setMoonVarName(factorname);
                }
                if(factorType == TokenType.INTNUM){
                    var newEntry = new LitValEntry("integer", factorname);
                    node.setEntry(newEntry);
                    node.getTable().addEntry(newEntry);
                }
                if(factorType == TokenType.FLOATNUM){
                    var newEntry = new LitValEntry("float", factorname);
                    node.setEntry(newEntry);
                    node.getTable().addEntry(newEntry);
                }
            }
    }

    @Override
    public void visit(FunctionBodyNode node) {
        for (Node child : node.getChildren() ) {
            //make all children use this scopes' symbol table
            child.accept(this);
        }
    }

    @Override
    public void visit(FunctionDefinitionNode node) {
        for (Node child : node.getChildren() ) {
            //make all children use this scopes' symbol table
            child.accept(this);
        }
        int size = 0;
        for(var entries: node.getTable().m_symlist){
            size+= entries.m_size;
        }
        node.getEntry().m_size = size;
    }

    @Override
    public void visit(FunctionHeadNode node) {
        for (Node child : node.getChildren() ) {
            //make all children use this scopes' symbol table
            child.accept(this);
        }
    }

    @Override
    public void visit(FunctionHeadTailNode node) {
        for (Node child : node.getChildren() ) {
            //make all children use this scopes' symbol table
            child.accept(this);
        }
    }

    @Override
    public void visit(FunctionParamsNode node) {
        for (Node child : node.getChildren() ) {
            //make all children use this scopes' symbol table
            child.accept(this);
        }
    }

    @Override
    public void visit(FunctionParamsTailNode node) {
        for (Node child : node.getChildren() ) {
            //make all children use this scopes' symbol table
            child.accept(this);
        }
        var entry = node.getEntry();
        var dimsList = entry.m_dims;
        var type = entry.m_type;
        var name = entry.m_name;
        System.out.println(type);
        System.out.println(dimsList);
        int size = 0;
        if(type.equals("integer")){
            size=4;
            int totalarray = 1;
            if(dimsList != null && dimsList.size() != 0){
                for(var dim: dimsList){
                    totalarray*=dim;
                }
            }
            size = totalarray*size;
            node.getEntry().m_size = size;
        }
        else{
            size=8;
            int totalarray = 1;
            if(dimsList != null && dimsList.size() != 0){
                for(var dim: dimsList){
                    totalarray*=dim;
                }
            }
            size = totalarray*size;
            node.getEntry().m_size = size;
        }
    }

    @Override
    public void visit(IdnestNode node) {
        for (Node child : node.getChildren() ) {
            //make all children use this scopes' symbol table
            child.accept(this);
        }
    }

    @Override
    public void visit(IfStatementNode node) {
        for (Node child : node.getChildren() ) {
            //make all children use this scopes' symbol table
            child.accept(this);
        }
    }

    @Override
    public void visit(IndiceNode node) {
        for (Node child : node.getChildren() ) {
            //make all children use this scopes' symbol table
            child.accept(this);
        }
    }

    @Override
    public void visit(InheritanceNode node) {
        for (Node child : node.getChildren() ) {
            //make all children use this scopes' symbol table
            child.accept(this);
        }
    }

    @Override
    public void visit(LocalVariableNode node) {
        var entry = node.getEntry();
        var dimsList = entry.m_dims;
        var type = entry.m_type;
        var name = entry.m_name;
        int size = 0;
        if(type.equals("integer")){
            size=4;
            int totalarray = 1;
            if(dimsList != null && dimsList.size() != 0){
                for(var dim: dimsList){
                    totalarray*=dim;
                }
            }
            size = totalarray*size;
            node.getEntry().m_size = size;
        }
        else if(type.equals("float")){
            size=8;
            int totalarray = 1;
            if(dimsList != null && dimsList.size() != 0){
                for(var dim: dimsList){
                    totalarray*=dim;
                }
            }
            size = totalarray*size;
            node.getEntry().m_size = size;
        }
        else{
            for(var mainEntry: headNode.getTable().m_symlist){
                if(mainEntry.m_name.equals(type)){
                    size = mainEntry.m_size;
                }
            }
            int totalarray = 1;
            if(dimsList != null && dimsList.size() != 0){
                for(var dim: dimsList){
                    totalarray*=dim;
                }
            }
            size = totalarray*size;
            node.getEntry().m_size = size;

        }
        for (Node child : node.getChildren() ) {
            //make all children use this scopes' symbol table
            child.accept(this);
        }

    }
    @Override
    public void visit(MemberDeclarationNode node) {
        var entry = node.getEntry();
        var dimsList = entry.m_dims;
        var type = entry.m_type;
        var name = entry.m_name;
        int size = 0;
        if(type.equals("integer")){
            size=4;
            int totalarray = 1;
            if(dimsList != null && dimsList.size() != 0){
                for(var dim: dimsList){
                    totalarray*=dim;
                }
            }
            size = totalarray*size;
            node.getEntry().m_size = size;

        }
        else{
            size=8;
            int totalarray = 1;
            if(dimsList != null && dimsList.size() != 0){
                for(var dim: dimsList){
                    totalarray*=dim;
                }
            }
            size = totalarray*size;
            node.getEntry().m_size = size;
        }
        node.getEntry().m_offset = offset;
        offset += size;
        for (Node child : node.getChildren() ) {
            //make all children use this scopes' symbol table
            child.accept(this);
        }
    }

    @Override
    public void visit(MemberFunctionDeclarationNode node) {
        for (Node child : node.getChildren() ) {
            //make all children use this scopes' symbol table
            child.accept(this);
        }
    }

    @Override
    public void visit(MemberVariableDeclarationNode node) {
        for (Node child : node.getChildren() ) {
            //make all children use this scopes' symbol table
            child.accept(this);
        }
    }

    @Override
    public void visit(Node node) {
        for (Node child : node.getChildren() ) {
            //make all children use this scopes' symbol table
            child.accept(this);
        }
    }

    @Override
    public void visit(ProgramNode node) {
        for (Node child : node.getChildren() ) {
            //make all children use this scopes' symbol table
            child.accept(this);
        }
    }

    @Override
    public void visit(ReadNode node) {
        for (Node child : node.getChildren() ) {
            //make all children use this scopes' symbol table
            child.accept(this);
        }
    }

    @Override
    public void visit(RecursiveArithmeticExpressionNode node) {
        for (Node child : node.getChildren() ) {
            //make all children use this scopes' symbol table
            child.accept(this);
        }
    }

    @Override
    public void visit(RecursiveTermNode node) {
        for (Node child : node.getChildren() ) {
            //make all children use this scopes' symbol table
            child.accept(this);
        }
    }

    @Override
    public void visit(RelExpressionNode node) {
        for (Node child : node.getChildren() ) {
            //make all children use this scopes' symbol table
            child.accept(this);
        }
    }

    @Override
    public void visit(RecursiveMemberDeclarationNode node) {
        offset = 0;
        for (Node child : node.getChildren() ) {
            //make all children use this scopes' symbol table
            child.accept(this);
        }
        offset = 0;
    }

    @Override
    public void visit(ReturnNode node) {
        for (Node child : node.getChildren() ) {
            //make all children use this scopes' symbol table
            child.accept(this);
        }
    }

    @Override
    public void visit(StartNode node) {
        headNode = node;
        for (Node child : node.getChildren() ) {
            //make all children use this scopes' symbol table
            child.accept(this);
        }
        populateSize(node.getTable());
        System.out.println(node.getTable());
        try {
            outMoonCode.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void populateSize(SymbolTable table) {
        for(var functionEntries: table.m_symlist){
            int totalSize = 0;
            if(functionEntries.m_subtable != null){
                for(var entries: functionEntries.m_subtable.m_symlist){
                    int localsize = 0;
                    var type = entries.m_type;
                }
            }
        }
    }

    @Override
    public void visit(StatementNode node) {
        for (Node child : node.getChildren() ) {
            //make all children use this scopes' symbol table
            child.accept(this);
        }
    }

    @Override
    public void visit(StatementIdnestNode node) {
        String tempStatBlock = "statblock"+currStatBlock;
        currStatBlock++;
        for (Node child : node.getChildren() ) {
            //make all children use this scopes' symbol table
            child.accept(this);
        }
        node.setMoonVarName(tempStatBlock);
    }

    @Override
    public void visit(TermNode node) {
        for (Node child : node.getChildren() ) {
            //make all children use this scopes' symbol table
            child.accept(this);
        }
//        if(node.getChildren().size() ==1){
//            node.setEntry(node.getChildren().get(0).getEntry());
//        }
//        else{
//            var currType = node.getChildren().get(0).getEntry().m_type;
//            for(var childs: node.getChildren()){
//                if(childs.getConcept() instanceof Token)
//                    node.getTable().addEntry(new TempVarEntry(currType));
//            }
//        }
    }

    @Override
    public void visit(VariableNode node) {
        for (Node child : node.getChildren() ) {
            //make all children use this scopes' symbol table
            child.accept(this);
        }
    }

    @Override
    public void visit(VariableIdnestNode node) {
        for (Node child : node.getChildren() ) {
            //make all children use this scopes' symbol table
            child.accept(this);
        }
    }

    @Override
    public void visit(WhileLoopNode node) {
        for (Node child : node.getChildren() ) {
            //make all children use this scopes' symbol table
            child.accept(this);
        }
    }

    @Override
    public void visit(WriteNode node) {
        for (Node child : node.getChildren() ) {
            //make all children use this scopes' symbol table
            child.accept(this);
        }
    }
}
