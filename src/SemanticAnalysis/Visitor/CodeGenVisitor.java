package SemanticAnalysis.Visitor;

import Common.Token;
import Common.TokenType;
import Nodes.*;
import SemanticAnalysis.Table.DataEntry;

import java.io.FileWriter;
import java.io.IOException;

public class CodeGenVisitor implements Visitor{
    Node headNode = null;
    FileWriter outMoonCode;
    String dataCode = "";
    String execCode = "";
    String m_mooncodeindent = "           ";
    int currTempVar = 1;
    int currZeroVal = 1;
    int currStatBlock = 1;
    int currEndRel = 1;
    int currGoWhile = 1;
    int currFuncReturn = 1;
    int currFuncParam = 1;

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
                execCode += m_mooncodeindent + "% processing: " + tempvar + " = " + firstName + " + " + secondName + "\n";
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
                execCode += m_mooncodeindent + "% processing: " + tempvar + " = " + firstName + " - " + secondName + "\n";
                execCode += m_mooncodeindent + "lw r1," + firstName + "(r0)\n";
                execCode += m_mooncodeindent + "lw r2," + secondName + "(r0)\n";
                execCode += m_mooncodeindent + "sub r3,r1,r2\n";
                execCode += m_mooncodeindent + "sw " + tempvar + "(r0),r3\n";

                dataCode += "% space for " + firstName + " - " + secondName + "\n";
                dataCode += String.format("%-10s", tempvar) + " res 4\n";
                execCode += "\n";

                node.setMoonVarName(tempvar);
            }
            else if (operatorType == TokenType.OR) {
                String tempZero = "zero" + currZeroVal;
                currZeroVal++;

                String tempEnd = "endRel" + currEndRel;
                currEndRel++;

                execCode += "\n";
                execCode += m_mooncodeindent + "% processing: " + tempvar + " = " + firstName + " and " + secondName + "\n";
                execCode += m_mooncodeindent + "lw r1," + firstName + "(r0)\n";
                execCode += m_mooncodeindent + "lw r2," + secondName + "(r0)\n";
                execCode += m_mooncodeindent + "bnz r1," + tempZero + "\n";
                execCode += m_mooncodeindent + "bnz r2," + tempZero + "\n";
                execCode += m_mooncodeindent + "add r3,r0,r0\n";
                execCode += m_mooncodeindent + "j " + tempEnd;
                execCode += "\n";

                execCode += "\n";
                execCode += String.format("%-10s", tempZero) + " addi r3,r0,1\n";
                execCode += String.format("%-10s", tempEnd) + "sw " + tempvar + "(r0),r3\n";
                execCode += "\n";

                dataCode += "% space for " + firstName + " and " + secondName + "\n";
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
        else{
            var firstName = node.getChildren().get(0).getMoonVarName();
            var secondName = node.getChildren().get(2).getMoonVarName();
            var operatorType = ((Token) node.getChildren().get(1).getConcept()).getType();
            String tempvar = "t" + currTempVar;
            currTempVar++;
            if (operatorType == TokenType.EQ) {

                execCode += "\n";
                execCode += m_mooncodeindent + "% processing: " + tempvar + " = " + firstName + " == " + secondName + "\n";
                execCode += m_mooncodeindent + "lw r1," + firstName + "(r0)\n";
                execCode += m_mooncodeindent + "lw r2," + secondName + "(r0)\n";
                execCode += m_mooncodeindent + "ceq r3,r1,r2\n";
                execCode += m_mooncodeindent + "sw " + tempvar + "(r0),r3\n";

                dataCode += "% space for " + firstName + " == " + secondName + "\n";
                dataCode += String.format("%-10s", tempvar) + " res 4\n";
                execCode += "\n";

                node.setMoonVarName(tempvar);
            }
            else if (operatorType == TokenType.LT) {

                execCode += "\n";
                execCode += m_mooncodeindent + "% processing: " + tempvar + " = " + firstName + " < " + secondName + "\n";
                execCode += m_mooncodeindent + "lw r1," + firstName + "(r0)\n";
                execCode += m_mooncodeindent + "lw r2," + secondName + "(r0)\n";
                execCode += m_mooncodeindent + "clt r3,r1,r2\n";
                execCode += m_mooncodeindent + "sw " + tempvar + "(r0),r3\n";

                dataCode += "% space for " + firstName + " < " + secondName + "\n";
                dataCode += String.format("%-10s", tempvar) + " res 4\n";
                execCode += "\n";

                node.setMoonVarName(tempvar);
            }
            else if (operatorType == TokenType.LEQ) {

                execCode += "\n";
                execCode += m_mooncodeindent + "% processing: " + tempvar + " = " + firstName + " <= " + secondName + "\n";
                execCode += m_mooncodeindent + "lw r1," + firstName + "(r0)\n";
                execCode += m_mooncodeindent + "lw r2," + secondName + "(r0)\n";
                execCode += m_mooncodeindent + "cle r3,r1,r2\n";
                execCode += m_mooncodeindent + "sw " + tempvar + "(r0),r3\n";

                dataCode += "% space for " + firstName + " < " + secondName + "\n";
                dataCode += String.format("%-10s", tempvar) + " res 4\n";
                execCode += "\n";

                node.setMoonVarName(tempvar);
            }
            else if (operatorType == TokenType.GT) {

                execCode += "\n";
                execCode += m_mooncodeindent + "% processing: " + tempvar + " = " + firstName + " > " + secondName + "\n";
                execCode += m_mooncodeindent + "lw r1," + firstName + "(r0)\n";
                execCode += m_mooncodeindent + "lw r2," + secondName + "(r0)\n";
                execCode += m_mooncodeindent + "cgt r3,r1,r2\n";
                execCode += m_mooncodeindent + "sw " + tempvar + "(r0),r3\n";

                dataCode += "% space for " + firstName + " < " + secondName + "\n";
                dataCode += String.format("%-10s", tempvar) + " res 4\n";
                execCode += "\n";

                node.setMoonVarName(tempvar);
            }
            else if (operatorType == TokenType.GEQ) {

                execCode += "\n";
                execCode += m_mooncodeindent + "% processing: " + tempvar + " = " + firstName + " > " + secondName + "\n";
                execCode += m_mooncodeindent + "lw r1," + firstName + "(r0)\n";
                execCode += m_mooncodeindent + "lw r2," + secondName + "(r0)\n";
                execCode += m_mooncodeindent + "cge r3,r1,r2\n";
                execCode += m_mooncodeindent + "sw " + tempvar + "(r0),r3\n";

                dataCode += "% space for " + firstName + " < " + secondName + "\n";
                dataCode += String.format("%-10s", tempvar) + " res 4\n";
                execCode += "\n";

                node.setMoonVarName(tempvar);
            }
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
        else if(node.getChildren().size() == 2 && node.getChildren().get(0).getConcept() instanceof Token && ((Token)node.getChildren().get(0).getConcept()).getType() == TokenType.NOT){
            var name = node.getChildren().get(1).getMoonVarName();
            String tempvar = "t"+currTempVar;
            currTempVar++;

            String tempZero = "zero" + currZeroVal;
            currZeroVal++;

            String tempEnd = "endRel" + currEndRel;
            currEndRel++;

            execCode += "\n";
            execCode += m_mooncodeindent + "% processing: " + tempvar + " = not " + name + "\n";
            execCode += m_mooncodeindent + "lw r1," + name + "(r0)\n";
            execCode += m_mooncodeindent + "bnz r1," + tempZero + "\n";
            execCode += m_mooncodeindent + "addi r2,r0,1\n";
            execCode += m_mooncodeindent + "sw " + tempvar + "(r0),r2 \n";
            execCode += m_mooncodeindent + "j " + tempEnd;
            execCode += "\n";

            execCode += "\n";
            execCode += String.format("%-10s", tempZero) + "sw " + tempvar + "(r0),r0 \n";
            execCode += String.format("%-10s", tempEnd);
            execCode += "\n";

            dataCode += "% space for not " + name + "\n";
            dataCode += String.format("%-10s", tempvar) + " res 4\n";


            node.setMoonVarName(tempvar);
        }
        else if(node.getChildren().size() == 2 && node.getChildren().get(1).getConcept().equals("argument params")){
            handleFunctionCall(node);
        }
        else if(node.getChildren().size() == 3){
            int rPostion = 0;
            var name = node.getChildren().get(0).getMoonVarName();
            var memberName = node.getChildren().get(2).getMoonVarName();
            var entry = headNode.getTable().GetDataMember(memberName);
            rPostion = entry.m_offset;

            String tempvar = "t"+currTempVar;
            currTempVar++;

            execCode += "\n";
            execCode += m_mooncodeindent + "%assigning values in factor for members\n";
            execCode += m_mooncodeindent + "sub r9,r9,r9\n";
            execCode += m_mooncodeindent + "addi r9,r9,"+rPostion+"\n";
            execCode += m_mooncodeindent + "lw r1,"+name+"(r9)\n";
            execCode += m_mooncodeindent + "sw "+tempvar+"(r0),r1\n";
            execCode += "\n";

            dataCode +=  "% space for array value\n";
            dataCode += String.format("%-10s",tempvar) + " res 4\n";
            execCode += "\n";
            node.setMoonVarName(tempvar);

        }
        else if(node.getChildren().size() == 4){
            int rPostion = 0;
            var name = node.getChildren().get(0).getMoonVarName();
            var memberName = node.getChildren().get(3).getMoonVarName();
            var entry = headNode.getTable().GetDataMember(memberName);
            rPostion = entry.m_offset;

            String tempvar = "t"+currTempVar;
            currTempVar++;

            var arrayplaceName = node.getChildren().get(1).getMoonVarName();
            var objectType = node.getTable().lookupName(((Token)node.getChildren().get(0).getConcept()).getLexeme()).m_type;
            int objectSize = 0;
            for(var classEntries: headNode.getTable().m_symlist){
                if(classEntries.m_name.equals(objectType)){
                    objectSize = classEntries.m_size;
                    break;
                }
            }

            execCode += "\n";
            execCode += m_mooncodeindent + "%assigning values in factor for members\n";
            execCode += m_mooncodeindent + "lw r3,"+arrayplaceName+"(r0)\n";
            execCode += m_mooncodeindent + "muli r2,r3,"+objectSize+"\n";
            execCode += m_mooncodeindent + "sub r8,r8,r8\n";
            execCode += m_mooncodeindent + "addi r8,r2,"+rPostion+"\n";
            execCode += m_mooncodeindent + "lw r1,"+name+"(r8)\n";
            execCode += m_mooncodeindent + "sw "+tempvar+"(r0),r1\n";
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
        boolean isMain = false;
        if(node.getEntry().m_name.equals("main")){
            isMain=true;
            execCode += "% start\n";
            execCode += m_mooncodeindent+ "entry\n";
            execCode += m_mooncodeindent + String.format("%-7s" ,"addi") + "r14,r0,topaddr  % Set stack pointer\n";
        }
        else{
            execCode += "% start of function\n";
            execCode += node.getEntry().m_name+"\n";

            for(var entry: node.getEntry().m_subtable.m_symlist){
                if(entry.m_kind.equals("param")){
                    var name = entry.m_name;
                    var size = entry.m_size;

                    //TODO maybe remove the size thing
                    if(size <=4){
                        dataCode += "% space for function parameter "+name+"\n";
                        dataCode += String.format("%-7s" ,name) + " res "+size+"\n";
                    }
                }
            }
        }
        for (Node child : node.getChildren() ) {
            //make all children use this scopes' symbol table
            child.accept(this);
        }
        if(isMain)
            execCode+=m_mooncodeindent+"hlt\n";
        else{
            execCode+=m_mooncodeindent+"jr r12\n";

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

        String tempEnd = "endRel" + currEndRel;
        currEndRel++;

        var expr = node.getChildren().get(0);
        var statBlock2 = node.getChildren().get(2);

        node.getChildren().get(0).accept(this);

        execCode += "\n";
        execCode += "%checking if condition\n";
        execCode += m_mooncodeindent + "lw r1, "+expr.getMoonVarName()+"(r0)\n";
        execCode += m_mooncodeindent + "bz r1, "+statBlock2.getMoonVarName();
        execCode += "\n";

        node.getChildren().get(1).accept(this);

        execCode += "\n";
        execCode += "%finished doing stat block 1\n";
        execCode += m_mooncodeindent + "j "+tempEnd;
        execCode += "\n";

        node.getChildren().get(2).accept(this);


        execCode += "\n";
        execCode += tempEnd+"\n";

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
        dataCode += "% space for variable "+name+"\n";
        dataCode += String.format("%-7s" ,name) + " res "+entry.m_size+"\n";
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
        if(node.getChildren().size() == 1){
            node.setMoonVarName(node.getChildren().get(0).getMoonVarName());
        }

        execCode += "\n";
        execCode += m_mooncodeindent+"%reading variable from the keyboard\n";
        execCode += m_mooncodeindent+"addi r1, r0, buf\n";
        execCode += m_mooncodeindent+"sw -8(r14),r1\n";
        execCode += m_mooncodeindent+"jl r3,getstr\n";
        execCode += m_mooncodeindent+"jl r12,strint\n";
        execCode += m_mooncodeindent+"sw "+node.getMoonVarName()+"(r0),r12 \n";
        execCode += "\n";
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
                execCode += m_mooncodeindent + "% processing: " + tempvar + " = " + firstName + " + " + secondName + "\n";
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
                execCode += m_mooncodeindent + "% processing: " + tempvar + " = " + firstName + " - " + secondName + "\n";
                execCode += m_mooncodeindent + "lw r1," + firstName + "(r0)\n";
                execCode += m_mooncodeindent + "lw r2," + secondName + "(r0)\n";
                execCode += m_mooncodeindent + "sub r3,r1,r2\n";
                execCode += m_mooncodeindent + "sw " + tempvar + "(r0),r3\n";

                dataCode += "% space for " + firstName + " - " + secondName + "\n";
                dataCode += String.format("%-10s", tempvar) + " res 4\n";
                execCode += "\n";

                node.setMoonVarName(tempvar);
            }
            else if (operatorType == TokenType.OR) {
                String tempZero = "zero" + currZeroVal;
                currZeroVal++;

                String tempEnd = "endRel" + currEndRel;
                currEndRel++;

                execCode += "\n";
                execCode += m_mooncodeindent + "% processing: " + tempvar + " = " + firstName + " or " + secondName + "\n";
                execCode += m_mooncodeindent + "lw r1," + firstName + "(r0)\n";
                execCode += m_mooncodeindent + "lw r2," + secondName + "(r0)\n";
                execCode += m_mooncodeindent + "bnz r1," + tempZero + "\n";
                execCode += m_mooncodeindent + "bnz r2," + tempZero + "\n";
                execCode += m_mooncodeindent + "add r3,r0,r0\n";
                execCode += m_mooncodeindent + "j " + tempEnd;
                execCode += "\n";

                execCode += "\n";
                execCode += String.format("%-10s", tempZero) + " addi r3,r0,1\n";
                execCode += String.format("%-10s", tempEnd) + "sw " + tempvar + "(r0),r3\n";
                execCode += "\n";

                dataCode += "% space for " + firstName + " or " + secondName + "\n";
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
                execCode += m_mooncodeindent + "% processing: " + tempvar + " = " + firstName + " * " + secondName + "\n";
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
                execCode += m_mooncodeindent + "% processing: " + tempvar + " = " + firstName + " / " + secondName + "\n";
                execCode += m_mooncodeindent + "lw r1," + firstName + "(r0)\n";
                execCode += m_mooncodeindent + "lw r2," + secondName + "(r0)\n";
                execCode += m_mooncodeindent + "div r3,r1,r2\n";
                execCode += m_mooncodeindent + "sw " + tempvar + "(r0),r3\n";

                dataCode += "% space for " + firstName + " / " + secondName + "\n";
                dataCode += String.format("%-10s", tempvar) + " res 4\n";
                execCode += "\n";

                node.setMoonVarName(tempvar);
            }
            else if (operatorType == TokenType.AND) {
                String tempZero = "zero" + currZeroVal;
                currZeroVal++;

                String tempEnd = "endrel" + currEndRel;
                currEndRel++;

                execCode += "\n";
                execCode += m_mooncodeindent + "% processing: " + tempvar + " = " + firstName + " and " + secondName + "\n";
                execCode += m_mooncodeindent + "lw r1," + firstName + "(r0)\n";
                execCode += m_mooncodeindent + "lw r2," + secondName + "(r0)\n";
                execCode += m_mooncodeindent + "bz r1," + tempZero + "\n";
                execCode += m_mooncodeindent + "bz r2," + tempZero + "\n";
                execCode += m_mooncodeindent + "addi r3,r0,1\n";
                execCode += m_mooncodeindent + "j " + tempEnd;
                execCode += "\n";

                execCode += "\n";
                execCode += String.format("%-10s", tempZero) + " addi r3,r0,0\n";
                execCode += String.format("%-10s", tempEnd) + "sw " + tempvar + "(r0),r3\n";
                execCode += "\n";

                dataCode += "% space for " + firstName + " and " + secondName + "\n";
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
        else{
            var firstName = node.getChildren().get(0).getMoonVarName();
            var secondName = node.getChildren().get(2).getMoonVarName();
            var operatorType = ((Token) node.getChildren().get(1).getConcept()).getType();
            String tempvar = "t" + currTempVar;
            currTempVar++;
            if (operatorType == TokenType.EQ) {

                execCode += "\n";
                execCode += m_mooncodeindent + "% processing: " + tempvar + " = " + firstName + " == " + secondName + "\n";
                execCode += m_mooncodeindent + "lw r1," + firstName + "(r0)\n";
                execCode += m_mooncodeindent + "lw r2," + secondName + "(r0)\n";
                execCode += m_mooncodeindent + "ceq r3,r1,r2\n";
                execCode += m_mooncodeindent + "sw " + tempvar + "(r0),r3\n";

                dataCode += "% space for " + firstName + " == " + secondName + "\n";
                dataCode += String.format("%-10s", tempvar) + " res 4\n";
                execCode += "\n";

                node.setMoonVarName(tempvar);
            }
            else if (operatorType == TokenType.LT) {

                execCode += "\n";
                execCode += m_mooncodeindent + "% processing: " + tempvar + " = " + firstName + " < " + secondName + "\n";
                execCode += m_mooncodeindent + "lw r1," + firstName + "(r0)\n";
                execCode += m_mooncodeindent + "lw r2," + secondName + "(r0)\n";
                execCode += m_mooncodeindent + "clt r3,r1,r2\n";
                execCode += m_mooncodeindent + "sw " + tempvar + "(r0),r3\n";

                dataCode += "% space for " + firstName + " < " + secondName + "\n";
                dataCode += String.format("%-10s", tempvar) + " res 4\n";
                execCode += "\n";

                node.setMoonVarName(tempvar);
            }
            else if (operatorType == TokenType.LEQ) {

                execCode += "\n";
                execCode += m_mooncodeindent + "% processing: " + tempvar + " = " + firstName + " <= " + secondName + "\n";
                execCode += m_mooncodeindent + "lw r1," + firstName + "(r0)\n";
                execCode += m_mooncodeindent + "lw r2," + secondName + "(r0)\n";
                execCode += m_mooncodeindent + "cle r3,r1,r2\n";
                execCode += m_mooncodeindent + "sw " + tempvar + "(r0),r3\n";

                dataCode += "% space for " + firstName + " < " + secondName + "\n";
                dataCode += String.format("%-10s", tempvar) + " res 4\n";
                execCode += "\n";

                node.setMoonVarName(tempvar);
            }
            else if (operatorType == TokenType.GT) {

                execCode += "\n";
                execCode += m_mooncodeindent + "% processing: " + tempvar + " = " + firstName + " > " + secondName + "\n";
                execCode += m_mooncodeindent + "lw r1," + firstName + "(r0)\n";
                execCode += m_mooncodeindent + "lw r2," + secondName + "(r0)\n";
                execCode += m_mooncodeindent + "cgt r3,r1,r2\n";
                execCode += m_mooncodeindent + "sw " + tempvar + "(r0),r3\n";

                dataCode += "% space for " + firstName + " < " + secondName + "\n";
                dataCode += String.format("%-10s", tempvar) + " res 4\n";
                execCode += "\n";

                node.setMoonVarName(tempvar);
            }
            else if (operatorType == TokenType.GEQ) {

                execCode += "\n";
                execCode += m_mooncodeindent + "% processing: " + tempvar + " = " + firstName + " > " + secondName + "\n";
                execCode += m_mooncodeindent + "lw r1," + firstName + "(r0)\n";
                execCode += m_mooncodeindent + "lw r2," + secondName + "(r0)\n";
                execCode += m_mooncodeindent + "cge r3,r1,r2\n";
                execCode += m_mooncodeindent + "sw " + tempvar + "(r0),r3\n";

                dataCode += "% space for " + firstName + " < " + secondName + "\n";
                dataCode += String.format("%-10s", tempvar) + " res 4\n";
                execCode += "\n";

                node.setMoonVarName(tempvar);
            }
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
        if(node.getChildren().size() == 1){
            node.setMoonVarName(node.getChildren().get(0).getMoonVarName());

            execCode += "\n";
            execCode += m_mooncodeindent + "%putting return value of function\n";
            execCode += m_mooncodeindent + "lw r1,"+node.getMoonVarName()+"(r0)\n";
            execCode += m_mooncodeindent + "sw fnres(r0), r1\n";
            execCode += m_mooncodeindent + "jr r12\n";

        }
    }

    @Override
    public void visit(StartNode node) {
        headNode = node;
        dataCode += "% space for variable buffer\n";
        dataCode += String.format("%-7s" ,"buf") + " res 20\n";
        dataCode += String.format("%-7s" ,"fnres") + " res 4\n";
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

        boolean isObject = false;
        int positionOfObject = 0;
        for(var childs: node.getChildren()){
            if(childs.getConcept() instanceof Token && ((Token)childs.getConcept()).getType() == TokenType.DOT){
                isObject = true;
                break;
            }
            positionOfObject++;
        }

        boolean isFunction = false;
        for(var childs: node.getChildren()){
            if(childs.getConcept().equals("argument params")){
                isFunction = true;
                break;
            }
        }

        if(isEqualSign){
            var assignName = node.getChildren().get(0).getMoonVarName();
            var nameToAssign = node.getChildren().get(node.getChildren().size()-1).getMoonVarName();
            int rPostion = 0;
            if(isObject){
                var memberName = node.getChildren().get(positionOfObject+1).getMoonVarName();
                var entry = headNode.getTable().GetDataMember(memberName);
                rPostion = entry.m_offset;

                if(node.getChildren().get(1).getConcept().equals("indice")){
                    var arrayplaceName = node.getChildren().get(1).getMoonVarName();
                    var objectType = node.getTable().lookupName(((Token)node.getChildren().get(0).getConcept()).getLexeme()).m_type;
                    int objectSize = 0;
                    for(var classEntries: headNode.getTable().m_symlist){
                        if(classEntries.m_name.equals(objectType)){
                            objectSize = classEntries.m_size;
                            break;
                        }
                    }
                    execCode += "\n";
                    execCode += m_mooncodeindent + "%assigning values to member\n";
                    execCode += m_mooncodeindent + "lw r1,"+arrayplaceName+"(r0)\n";
                    execCode += m_mooncodeindent + "muli r2,r1,"+objectSize+"\n";
                    execCode += m_mooncodeindent + "sub r8,r8,r8\n";
                    execCode += m_mooncodeindent + "addi r8,r2,"+rPostion+"\n";
                    execCode += m_mooncodeindent + "lw r9,"+nameToAssign+"(r0)\n";
                    execCode += m_mooncodeindent + "sw "+assignName+"(r8),r9\n";
                    execCode += "\n";
                }
                else{
                    execCode += "\n";
                    execCode += m_mooncodeindent + "%assigning values to member\n";
                    execCode += m_mooncodeindent + "sub r8,r8,r8\n";
                    execCode += m_mooncodeindent + "addi r8,r8,"+rPostion+"\n";
                    execCode += m_mooncodeindent + "lw r9,"+nameToAssign+"(r0)\n";
                    execCode += m_mooncodeindent + "sw "+assignName+"(r8),r9\n";
                    execCode += "\n";
                }

            }
            else{
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
        if(isFunction) {
            handleFunctionCall(node);
        }
    }

    @Override
    public void visit(StatementIdnestNode node) {
        String tempStatBlock = "statblock"+currStatBlock;
        currStatBlock++;
        execCode += tempStatBlock+"\n";
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
                execCode += m_mooncodeindent + "% processing: " + tempvar + " = " + firstName + " * " + secondName + "\n";
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
                execCode += m_mooncodeindent + "% processing: " + tempvar + " = " + firstName + " / " + secondName + "\n";
                execCode += m_mooncodeindent + "lw r1," + firstName + "(r0)\n";
                execCode += m_mooncodeindent + "lw r2," + secondName + "(r0)\n";
                execCode += m_mooncodeindent + "div r3,r1,r2\n";
                execCode += m_mooncodeindent + "sw " + tempvar + "(r0),r3\n";

                dataCode += "% space for " + firstName + " / " + secondName + "\n";
                dataCode += String.format("%-10s", tempvar) + " res 4\n";
                execCode += "\n";

                node.setMoonVarName(tempvar);
            }
            else if (operatorType == TokenType.AND) {
                String tempZero = "zero" + currZeroVal;
                currZeroVal++;

                String tempEnd = "endrel" + currEndRel;
                currEndRel++;

                execCode += "\n";
                execCode += m_mooncodeindent + "% processing: " + tempvar + " = " + firstName + " and " + secondName + "\n";
                execCode += m_mooncodeindent + "lw r1," + firstName + "(r0)\n";
                execCode += m_mooncodeindent + "lw r2," + secondName + "(r0)\n";
                execCode += m_mooncodeindent + "bz r1," + tempZero + "\n";
                execCode += m_mooncodeindent + "bz r2," + tempZero + "\n";
                execCode += m_mooncodeindent + "addi r3,r0,1\n";
                execCode += m_mooncodeindent + "j " + tempEnd;
                execCode += "\n";

                execCode += "\n";
                execCode += String.format("%-10s", tempZero) + " addi r3,r0,0\n";
                execCode += String.format("%-10s", tempEnd) + "sw " + tempvar + "(r0),r3\n";
                execCode += "\n";

                dataCode += "% space for " + firstName + " and " + secondName + "\n";
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
        if(node.getChildren().size() == 1){
            node.setMoonVarName(node.getChildren().get(0).getMoonVarName());
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
        String tempEnd = "endRel" + currEndRel;
        currEndRel++;

        String tempGoWhile = "gowhile" + currGoWhile;
        currGoWhile++;

        var expr = node.getChildren().get(0);

        execCode += "\n";
        execCode += "%checking while loop condition\n";
        execCode += tempGoWhile+"\n";
        node.getChildren().get(0).accept(this);
        execCode += m_mooncodeindent + "lw r1, "+expr.getMoonVarName()+"(r0)\n";
        execCode += m_mooncodeindent + "bz r1, "+tempEnd;
        execCode += "\n";

        node.getChildren().get(1).accept(this);

        execCode += "\n";
        execCode += "%finished doing stat block going back to top\n";
        execCode += m_mooncodeindent + "j "+tempGoWhile;
        execCode += "\n";

        execCode += "\n";
        execCode += tempEnd+"\n";
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
            execCode += m_mooncodeindent + "sub r6,r6,r6\n";
            execCode += m_mooncodeindent + "addi r6,r6,10\n";
            execCode += m_mooncodeindent + "putc r6\n";
        }
    }


    private void handleFunctionCall(Node node){
        //function call
        var name = node.getChildren().get(0).getMoonVarName();

        execCode += "\n";
        execCode += m_mooncodeindent + "% calling function: " + name +"\n";

        var funcName = ((Token)node.getChildren().get(0).getConcept()).getLexeme();
        var funcEntry = headNode.getTable().isFuncCallReturnTables(funcName);

        if(funcEntry != null && funcEntry.size() >= 1){
            var params = funcEntry.get(0).m_params;
            var paramsNodeList = node.getChildren().get(1).getChildren();
            for(int i =0; i< params.size(); i++){
                var funcParamName = params.get(i).m_name;
                var localParamName = paramsNodeList.get(i).getMoonVarName();

                int numberOfIterations = params.get(i).m_size/4;

                execCode += "\n";
                execCode += m_mooncodeindent + "% defining params of function and propagating it for the size: " + funcParamName +"\n";

                //TODO figure out if this is needed
                if(numberOfIterations >= 2){
                    continue;
                }

                for(int j=0; j<numberOfIterations; j++){
                    execCode += m_mooncodeindent + "addi r4, r0, "+j*4+"\n";
                    execCode += m_mooncodeindent + "lw r1,"+localParamName+"(r4)\n";
                    execCode += m_mooncodeindent + "sw " + funcParamName + "(r4),r1 \n";
                    execCode += "\n";
                }
            }
        }

        execCode += m_mooncodeindent + "jl r12," + name + "\n";



        if(funcEntry != null && funcEntry.size() >= 1){
            if(!funcEntry.get(0).m_type.equals("void")){
                String tempvar = "t"+currTempVar;
                currTempVar++;

                execCode += "\n";
                execCode += m_mooncodeindent + "% getting return value\n";
                execCode += m_mooncodeindent + "lw r1,fnres(r0)\n";
                execCode += m_mooncodeindent + "sw " + tempvar + "(r0),r1 \n";
                execCode += "\n";

                dataCode += "% space for return value of function: " + name + "\n";
                dataCode += String.format("%-10s", tempvar) + " res 4\n";
                node.setMoonVarName(tempvar);
            }
        }
    }
}
