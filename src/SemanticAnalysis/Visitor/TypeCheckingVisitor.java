package SemanticAnalysis.Visitor;

import Common.Token.Token;
import Common.Token.TokenType;
import Common.Nodes.*;
import Common.Table.FuncEntry;
import Common.Visitor.Visitor;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class TypeCheckingVisitor implements Visitor {
    public StringBuilder outSemanticErrors;
    Node headNode = null;

    String currentType = "";

    Token previousId = null;
    Token previousToken = null;
    int numberOfDims = 0;

    public TypeCheckingVisitor(StringBuilder currentErrors) throws IOException {
        outSemanticErrors = new StringBuilder(currentErrors);
        outSemanticErrors.append("\n");
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
        String id = ((Token)node.getChildren().get(1).getConcept()).getLexeme();
        String type = ((Token)node.getChildren().get(2).getConcept()).getLexeme();
        int location = ((Token)node.getChildren().get(2).getConcept()).getLocation();
        if(id.equals(type)){
            outSemanticErrors.append("Cannot assign a variable to itself: ").append(location).append("\n");
        }
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
                    outSemanticErrors.append("Variable ").append(currToken.getLexeme()).append(" has not been declared: ").append(currToken.getLocation()).append("\n");
            }
            if(currentType.length() != 0){
                String localType = node.getTable().lookupName(currToken.getLexeme()).m_type;
                if(localType != null && !localType.equals(currentType)){
                        outSemanticErrors.append("the type of the left and right hand side of the assignment operator must be the same at location: ").append(currToken.getLocation()).append("\n");
                }
            }
            else{
                try{
                    ArrayList<Node> cousins = node.getParent().getChildren();
                    for (var cousin: cousins){
                        if(cousin.getConcept().equals("argument params")){
                            int numberOfParams = cousin.getChildren().size();
                            String funcCallToCheck = ((Token)cousins.get(0).getConcept()).getLexeme();
                            ArrayList<FuncEntry> funcCalls =  headNode.getTable().isFuncCallReturnTables(funcCallToCheck);
                            if(funcCalls.size() != 0){
                                boolean validNumbOfArgs = false;
                                for(var funcCall: funcCalls){
                                    if(funcCall.m_params.size() == numberOfParams)
                                        validNumbOfArgs = true;
                                }
                                if(!validNumbOfArgs){
                                    outSemanticErrors.append("Invalid number of params: ").append(currToken.getLocation()).append("\n");
                                }
                            }
                        }
                    }
                }
                catch(Exception ignored){

                }
            }
        }


        if(currToken.getType() == TokenType.DOT){
            if(previousId == null){
                    outSemanticErrors.append("undeclared member function").append(currToken.getLocation()).append("\n");
            }
            try{
                String callerKind = node.getTable().lookupName(previousId.getLexeme()).m_kind;
                String callertType = node.getTable().lookupName(previousId.getLexeme()).m_type;
                if(!(callerKind.equals("data") || (!callertType.equals("integer") && !callertType.equals("float")))){
                    outSemanticErrors.append("Cannot call a function without a member of the class: ").append(currToken.getLocation()).append("\n");
                }
            }
            catch (Exception e){
                outSemanticErrors.append("undeclared member function ").append(currToken.getLocation()).append("\n");
            }
        }

        if(currToken.getType() == TokenType.ID){
            previousId = currToken;
        }
        if(currToken.getType() == TokenType.SELF )
            if( previousToken != null && previousToken.getType() == TokenType.DOT){
                outSemanticErrors.append("Wrong use of the self operator").append(currToken.getLocation()).append("\n");
        }
        previousToken = currToken;


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
        String tableName = node.getTable().m_name;
        for (Node child : node.getChildren() ) {
            //make all children use this scopes' symbol table
            child.accept(this);
        }
        if(tableName.equals("Constructor")){
            if(previousToken.getType() == TokenType.INTNUM || previousToken.getType() == TokenType.FLOATNUM){
                outSemanticErrors.append("wrong return type ").append(previousToken.getLocation()).append("\n");
            }
        }
    }

    @Override
    public void visit(StartNode node) {
        headNode = node;
        for (Node child : node.getChildren() ) {
            //make all children use this scopes' symbol table
            child.accept(this);
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
                outSemanticErrors.append("function ").append(id).append(" has not been declared: ").append(location).append("\n");
            }

        }
        else{
            if(isFirstChildId && !node.getTable().lookupNameReturnsBool(id)){
                if(!headNode.getTable().isDataMember(id)){
                    outSemanticErrors.append("Variable ").append(id).append(" has not been declared: ").append(location).append("\n");
                }
            }

            if(isFirstChildId){
                currentType = node.getTable().lookupName(id).m_type;
                if(node.getChildren().get(1).getConcept() instanceof Token)
                    if(currentType == null || ((Token)node.getChildren().get(1).getConcept()).getType() != TokenType.ASSIGN)
                        currentType = "";
            }
        }
        numberOfDims = 0;
        for(var childs: node.getChildren()){
            if(childs.getConcept().equals("indice")){
                numberOfDims++;
            }
        }
        if(numberOfDims!=0){
            for(var entries: node.getTable().m_symlist) {
                if (entries.m_name.equals(id) && entries.m_dims.size() != numberOfDims) {
                    outSemanticErrors.append("wrong number of dimensions used: "+ location + "\n");
                }
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
