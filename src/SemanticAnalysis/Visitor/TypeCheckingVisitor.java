package SemanticAnalysis.Visitor;

import Common.Token;
import Common.TokenType;
import Nodes.*;
import SemanticAnalysis.Table.FuncEntry;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class TypeCheckingVisitor implements Visitor{
    FileWriter outSemanticErrorsWriter;
    Node headNode = null;

    String currentType = "";

    Token previousId = null;

    public TypeCheckingVisitor(String path) throws IOException {
        String pathPrefix = path.split("\\.")[0];
        outSemanticErrorsWriter = new FileWriter(pathPrefix+".outsemanticerrors", true);
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
        for (Node child : node.getChildren() ) {
            //make all children use this scopes' symbol table
            child.accept(this);
        }
    }

    @Override
    public void visit(MemberDeclarationNode node) {
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
        Token currToken = (Token)node.getConcept();
        if(currToken.getType() == TokenType.ID){
            boolean doesIdExist = headNode.getTable().idExists(currToken.getLexeme());
            if(!doesIdExist){
                try {
                    outSemanticErrorsWriter.write("Variable "+currToken.getLexeme()+" has not been declared: "+ currToken.getLocation() + "\n");
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            if(currentType.length() != 0){
                String localType = node.getTable().lookupName(currToken.getLexeme()).m_type;
                if(localType != null && !localType.equals(currentType)){
                    try {
                        outSemanticErrorsWriter.write("the type of the left and right hand side of the assignment operator must be the same at location: "+ currToken.getLocation() + "\n");
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
            else{
                try{
                    ArrayList<Node> cousins = node.getParent().getChildren();
                    for (var cousin: cousins){
                        if(cousin.getConcept().equals("argument params")){
                            int numberOfParams = cousin.getChildren().size();
                            String funcCallToCheck = ((Token)cousins.get(2).getConcept()).getLexeme();
                            ArrayList<FuncEntry> funcCalls =  headNode.getTable().isFuncCallReturnTables(funcCallToCheck);
                            if(funcCalls.size() != 0){
                                boolean validNumbOfArgs = false;
                                for(var funcCall: funcCalls){
                                    if(funcCall.m_params.size() == numberOfParams)
                                        validNumbOfArgs = true;
                                }
                                if(!validNumbOfArgs){
                                    outSemanticErrorsWriter.write("Invalid number of params: "+ currToken.getLocation() + "\n");
                                }
                            }
                        }
                    }
                }
                catch(Exception e){

                }
            }
        }


        if(currToken.getType() == TokenType.DOT){
            if(previousId == null){
                try {
                    outSemanticErrorsWriter.write("undeclared member function"+ currToken.getLocation() + "\n");
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            try{
                String callerKind = node.getTable().lookupName(previousId.getLexeme()).m_kind;
                String callertType = node.getTable().lookupName(previousId.getLexeme()).m_type;
                if(!(callerKind.equals("data") || (!callertType.equals("integer") && !callertType.equals("float")))){
                    outSemanticErrorsWriter.write("Cannot call a function without a member of the class: "+ currToken.getLocation() + "\n");
                }
            }
            catch (Exception e){
                try {
                    outSemanticErrorsWriter.write("undeclared member function"+ currToken.getLocation() + "\n");
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        }

        if(currToken.getType() == TokenType.ID){
            previousId = currToken;
        }

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
        for (Node child : node.getChildren() ) {
            //make all children use this scopes' symbol table
            child.accept(this);
        }
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

        try {
            outSemanticErrorsWriter.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void visit(StatementNode node) {
        boolean isFirstChildId = false;
        boolean isFuncCall = false;
        String id = "";
        int location = 0;
        try{
            id = ((Token)node.getChildren().get(0).getConcept()).getLexeme();
            location = ((Token)node.getChildren().get(0).getConcept()).getLocation();
            isFirstChildId = true;
        }
        catch (Exception ignored){
        }
        ArrayList<FuncEntry> funcsWithName = new ArrayList<>();
        if(isFirstChildId && node.getChildren().get(1).getConcept().equals("argument params")){
            //Function call
            funcsWithName = headNode.getTable().isFuncCallReturnTables(id);
            if(funcsWithName.size() == 0){
                //function not declared
                try {
                    outSemanticErrorsWriter.write("function "+id+" has not been declared: "+ location + "\n");
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }

        }
        else{
            if(isFirstChildId && !node.getTable().lookupNameReturnsBool(id)){
                try {
                    if(!headNode.getTable().isDataMember(id)){
                        outSemanticErrorsWriter.write("Variable "+id+" has not been declared: "+ location + "\n");
                    }
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }

            if(isFirstChildId){
                currentType = node.getTable().lookupName(id).m_type;
                if(currentType == null || ((Token)node.getChildren().get(1).getConcept()).getType() != TokenType.ASSIGN)
                    currentType = "";
            }
        }
        for (Node child : node.getChildren() ) {
            //make all children use this scopes' symbol table
            child.accept(this);
        }
        currentType = "";

    }

    @Override
    public void visit(StatementIdnestNode node) {
        for (Node child : node.getChildren() ) {
            //make all children use this scopes' symbol table
            child.accept(this);
        }
    }

    @Override
    public void visit(TermNode node) {
        for (Node child : node.getChildren() ) {
            //make all children use this scopes' symbol table
            child.accept(this);
        }
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
