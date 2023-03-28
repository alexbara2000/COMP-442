package SemanticAnalysis.Visitor;

import Common.Token;
import Common.TokenType;
import Nodes.*;

import java.io.FileWriter;
import java.io.IOException;

public class CodeGenVisitor implements Visitor{
    FileWriter outMoonCode;
    String dataCode = "";
    String execCode = "";
    String m_mooncodeindent = "           ";
    int currTempVar = 1;

    Node currNode = null;
    public CodeGenVisitor(String path) throws IOException {
        String pathPrefix = path.split("\\.")[0];
        outMoonCode = new FileWriter(pathPrefix+".m", true);
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
        if(node.getChildren().size() == 1){
            node.setMoonVarName(node.getChildren().get(0).getMoonVarName());
        }
        else{
            var firstName = node.getChildren().get(0).getMoonVarName();
            var secondName = node.getChildren().get(2).getMoonVarName();
            var operatorType = ((Token)node.getChildren().get(1).getConcept()).getType();
            String tempvar = "t"+currTempVar;
            currTempVar++;
            if(operatorType == TokenType.PLUS){

                execCode += "\n";
                execCode += m_mooncodeindent + "% processing: " + tempvar + " := " + firstName + " + " + secondName + "\n";
                execCode += m_mooncodeindent + "lw r1," + firstName + "(r0)\n";
                execCode += m_mooncodeindent + "lw r2," + secondName + "(r0)\n";
                execCode += m_mooncodeindent + "add r3,r1,r2\n";
                execCode += m_mooncodeindent + "sw " + tempvar + "(r0),r3\n";

                dataCode +=  "% space for " + firstName + " + " + secondName + "\n";
                dataCode += String.format("%-10s",tempvar) + " res 4\n";
                execCode += "\n";

                node.setMoonVarName(tempvar);
            }
            else if(operatorType == TokenType.MINUS) {

                execCode += "\n";
                execCode += m_mooncodeindent + "% processing: " + tempvar + " := " + firstName + " - " + secondName + "\n";
                execCode += m_mooncodeindent + "lw r1," + firstName + "(r0)\n";
                execCode += m_mooncodeindent + "lw r2," + secondName + "(r0)\n";
                execCode += m_mooncodeindent + "sub r3,r1,r2\n";
                execCode += m_mooncodeindent + "sw " + tempvar + "(r0),r3\n";

                dataCode += "% space for " + firstName + " - " + secondName + "\n";
                dataCode += String.format("%-10s", tempvar) + " res 4\n";
                execCode += "\n";

                node.setMoonVarName(tempvar);
            }
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
        if(node.getChildren().size() == 1){
            node.setMoonVarName(node.getChildren().get(0).getMoonVarName());
        }
    }

    @Override
    public void visit(FactorNode node) {
        for (Node child : node.getChildren() ) {
            //make all children use this scopes' symbol table
            child.accept(this);
        }
        if(node.getChildren().size() == 1){
            node.setMoonVarName(node.getChildren().get(0).getMoonVarName());
        }
        if(node.getChildren().size() == 2 && node.getChildren().get(1).getConcept().equals("indice")){
            var indiceName = node.getChildren().get(1).getMoonVarName();
            var arrName = node.getChildren().get(0).getMoonVarName();
            String tempvar = "t"+currTempVar;
            currTempVar++;

            execCode += "\n";
            execCode += m_mooncodeindent + "%assigning values in factor\n";
            execCode += m_mooncodeindent + "lw r1,"+indiceName+"(r0)\n";
            //todo change 4 for size of
            execCode += m_mooncodeindent + "muli r2,r1,4\n";
            execCode += m_mooncodeindent + "lw r9,"+arrName+"(r2)\n";
            execCode += m_mooncodeindent + "sw "+tempvar+"(r0),r9\n";
            execCode += "\n";

            dataCode +=  "% space for array value\n";
            dataCode += String.format("%-10s",tempvar) + " res 4\n";
            execCode += "\n";
            node.setMoonVarName(tempvar);
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
        if(node.getEntry().m_name.equals("main")){
            execCode += "% start\n";
            execCode += m_mooncodeindent+ "entry\n";
            execCode += m_mooncodeindent + String.format("%-7s" ,"addi") + "r14,r0,topaddr  % Set stack pointer\n";
        }
        for (Node child : node.getChildren() ) {
            //make all children use this scopes' symbol table
            child.accept(this);
        }
        execCode+=m_mooncodeindent+"hlt";
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
        if(node.getChildren().size() == 1){
            node.setMoonVarName(node.getChildren().get(0).getMoonVarName());
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
        System.out.println(type);
        System.out.println(dimsList);
        if(type.equals("integer")){
            dataCode += "% space for variable "+name+"\n";
            dataCode += String.format("%-7s" ,name) + " res 4\n";
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
        currNode = node;
        var nodeToken = (Token)node.getConcept();
        if(nodeToken.getType() == TokenType.ID){
            node.setMoonVarName(((Token)node.getConcept()).getLexeme());
        }
        else if(nodeToken.getType() == TokenType.INTNUM){
            String tempvar = "t"+currTempVar;
            int value = Integer.parseInt(nodeToken.getLexeme());
            currTempVar++;

            execCode += "\n";
            execCode += m_mooncodeindent + "%assigning values\n";
            execCode += m_mooncodeindent + "sub r9,r9,r9\n";
            execCode += m_mooncodeindent + "addi r9,r9,"+value+"\n";
            execCode += m_mooncodeindent + "sw "+tempvar+"(r0),r9\n";
            execCode += "\n";

            dataCode += "% space for variable "+tempvar+"\n";
            dataCode += String.format("%-7s" ,tempvar) + " res 4\n";

            node.setMoonVarName(tempvar);
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
        if(node.getChildren().size() == 1){
            node.setMoonVarName(node.getChildren().get(0).getMoonVarName());
        }
        else{
            var firstName = node.getChildren().get(0).getMoonVarName();
            var secondName = node.getChildren().get(2).getMoonVarName();
            var operatorType = ((Token)node.getChildren().get(1).getConcept()).getType();
            String tempvar = "t"+currTempVar;
            currTempVar++;
            if(operatorType == TokenType.PLUS) {

                execCode += "\n";
                execCode += m_mooncodeindent + "% processing: " + tempvar + " := " + firstName + " + " + secondName + "\n";
                execCode += m_mooncodeindent + "lw r1," + firstName + "(r0)\n";
                execCode += m_mooncodeindent + "lw r2," + secondName + "(r0)\n";
                execCode += m_mooncodeindent + "add r3,r1,r2\n";
                execCode += m_mooncodeindent + "sw " + tempvar + "(r0),r3\n";

                dataCode += "% space for " + firstName + " + " + secondName + "\n";
                dataCode += String.format("%-10s", tempvar) + " res 4\n";
                execCode += "\n";

                node.setMoonVarName(tempvar);
            }
            else if(operatorType == TokenType.MINUS) {

                execCode += "\n";
                execCode += m_mooncodeindent + "% processing: " + tempvar + " := " + firstName + " - " + secondName + "\n";
                execCode += m_mooncodeindent + "lw r1," + firstName + "(r0)\n";
                execCode += m_mooncodeindent + "lw r2," + secondName + "(r0)\n";
                execCode += m_mooncodeindent + "sub r3,r1,r2\n";
                execCode += m_mooncodeindent + "sw " + tempvar + "(r0),r3\n";

                dataCode += "% space for " + firstName + " - " + secondName + "\n";
                dataCode += String.format("%-10s", tempvar) + " res 4\n";
                execCode += "\n";

                node.setMoonVarName(tempvar);
            }
        }
    }

    @Override
    public void visit(RecursiveTermNode node) {
        for (Node child : node.getChildren() ) {
            //make all children use this scopes' symbol table
            child.accept(this);
        }
        if(node.getChildren().size() == 1){
            node.setMoonVarName(node.getChildren().get(0).getMoonVarName());
        }
        else {
            var firstName = node.getChildren().get(0).getMoonVarName();
            var secondName = node.getChildren().get(2).getMoonVarName();
            var operatorType = ((Token) node.getChildren().get(1).getConcept()).getType();
            String tempvar = "t" + currTempVar;
            currTempVar++;
            if (operatorType == TokenType.MULT) {

                execCode += "\n";
                execCode += m_mooncodeindent + "% processing: " + tempvar + " := " + firstName + " * " + secondName + "\n";
                execCode += m_mooncodeindent + "lw r1," + firstName + "(r0)\n";
                execCode += m_mooncodeindent + "lw r2," + secondName + "(r0)\n";
                execCode += m_mooncodeindent + "mul r3,r1,r2\n";
                execCode += m_mooncodeindent + "sw " + tempvar + "(r0),r3\n";

                dataCode += "% space for " + firstName + " * " + secondName + "\n";
                dataCode += String.format("%-10s", tempvar) + " res 4\n";
                execCode += "\n";

                node.setMoonVarName(tempvar);
            }
            else if (operatorType == TokenType.DIV) {

                execCode += "\n";
                execCode += m_mooncodeindent + "% processing: " + tempvar + " := " + firstName + " / " + secondName + "\n";
                execCode += m_mooncodeindent + "lw r1," + firstName + "(r0)\n";
                execCode += m_mooncodeindent + "lw r2," + secondName + "(r0)\n";
                execCode += m_mooncodeindent + "div r3,r1,r2\n";
                execCode += m_mooncodeindent + "sw " + tempvar + "(r0),r3\n";

                dataCode += "% space for " + firstName + " / " + secondName + "\n";
                dataCode += String.format("%-10s", tempvar) + " res 4\n";
                execCode += "\n";

                node.setMoonVarName(tempvar);
            }
        }
    }

    @Override
    public void visit(RelExpressionNode node) {
        for (Node child : node.getChildren() ) {
            //make all children use this scopes' symbol table
            child.accept(this);
        }
        if(node.getChildren().size() == 1){
            node.setMoonVarName(node.getChildren().get(0).getMoonVarName());
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
        dataCode += "% space for variable buffer\n";
        dataCode += String.format("%-7s" ,"buf") + " res 20\n";
        for (Node child : node.getChildren() ) {
            //make all children use this scopes' symbol table
            child.accept(this);
        }
        try {
            outMoonCode.write(execCode);
            outMoonCode.write("\n");
            outMoonCode.write(dataCode);
            outMoonCode.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void visit(StatementNode node) {
        for (Node child : node.getChildren() ) {
            //make all children use this scopes' symbol table
            child.accept(this);
        }
        boolean isEqualSign = false;
        int positionOfEqual = 0;
        for(var childs: node.getChildren()){
            if(childs.getConcept() instanceof Token && ((Token)childs.getConcept()).getType() == TokenType.ASSIGN){
                isEqualSign = true;
                break;
            }
            positionOfEqual++;
        }
        if(isEqualSign){
            var assignName = node.getChildren().get(0).getMoonVarName();
            var nameToAssign = node.getChildren().get(node.getChildren().size()-1).getMoonVarName();
            execCode += "\n";
            if(node.getChildren().get(1).getConcept().equals("indice")){
                var arrayplaceName = node.getChildren().get(1).getMoonVarName();
                execCode += m_mooncodeindent + "%assigning values\n";
                execCode += m_mooncodeindent + "lw r1,"+arrayplaceName+"(r0)\n";
                //todo change 4 for size of
                execCode += m_mooncodeindent + "muli r2,r1,4\n";
                execCode += m_mooncodeindent + "lw r9,"+nameToAssign+"(r0)\n";
                execCode += m_mooncodeindent + "sw "+assignName+"(r2),r9\n";
            }
            else{
                execCode += m_mooncodeindent + "%assigning values\n";
                execCode += m_mooncodeindent + "lw r9,"+nameToAssign+"(r0)\n";
                execCode += m_mooncodeindent + "sw "+assignName+"(r0),r9\n";
            }
            execCode += "\n";
        }
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
        if(node.getChildren().size() == 1){
            node.setMoonVarName(node.getChildren().get(0).getMoonVarName());
        }
        else {
            var firstName = node.getChildren().get(0).getMoonVarName();
            var secondName = node.getChildren().get(2).getMoonVarName();
            var operatorType = ((Token) node.getChildren().get(1).getConcept()).getType();
            String tempvar = "t" + currTempVar;
            currTempVar++;
            if (operatorType == TokenType.MULT) {

                execCode += "\n";
                execCode += m_mooncodeindent + "% processing: " + tempvar + " := " + firstName + " * " + secondName + "\n";
                execCode += m_mooncodeindent + "lw r1," + firstName + "(r0)\n";
                execCode += m_mooncodeindent + "lw r2," + secondName + "(r0)\n";
                execCode += m_mooncodeindent + "mul r3,r1,r2\n";
                execCode += m_mooncodeindent + "sw " + tempvar + "(r0),r3\n";

                dataCode += "% space for " + firstName + " * " + secondName + "\n";
                dataCode += String.format("%-10s", tempvar) + " res 4\n";
                execCode += "\n";

                node.setMoonVarName(tempvar);
            }
            else if (operatorType == TokenType.DIV) {

                execCode += "\n";
                execCode += m_mooncodeindent + "% processing: " + tempvar + " := " + firstName + " / " + secondName + "\n";
                execCode += m_mooncodeindent + "lw r1," + firstName + "(r0)\n";
                execCode += m_mooncodeindent + "lw r2," + secondName + "(r0)\n";
                execCode += m_mooncodeindent + "div r3,r1,r2\n";
                execCode += m_mooncodeindent + "sw " + tempvar + "(r0),r3\n";

                dataCode += "% space for " + firstName + " / " + secondName + "\n";
                dataCode += String.format("%-10s", tempvar) + " res 4\n";
                execCode += "\n";

                node.setMoonVarName(tempvar);
            }
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
        if(node.getChildren().size() == 1){
            node.setMoonVarName(node.getChildren().get(0).getMoonVarName());
            execCode += "\n";
            execCode += m_mooncodeindent + "% processing: put("  + node.getMoonVarName() + ")\n";
            execCode += m_mooncodeindent + "lw " + "r1" + "," + node.getMoonVarName() + "(r0)\n";
            execCode += m_mooncodeindent + "% put value on stack\n";
            execCode += m_mooncodeindent + "sw -8(r14)," + "r1" + "\n";
            execCode += m_mooncodeindent + "% link buffer to stack\n";
            execCode += m_mooncodeindent + "addi " + "r1" + ",r0, buf\n";
            execCode += m_mooncodeindent + "sw -12(r14)," + "r1" + "\n";
            execCode += m_mooncodeindent + "% convert int to string for output\n";
            execCode += m_mooncodeindent + "jl r15, intstr\n";
            execCode += m_mooncodeindent + "sw -8(r14),r13\n";
            execCode += m_mooncodeindent + "% output to console\n";
            execCode += m_mooncodeindent + "jl r15, putstr\n";
            execCode += "\n";
        }


    }
}
