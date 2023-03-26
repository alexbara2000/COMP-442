package SemanticAnalysis.Visitor;

import Common.Token;
import Common.TokenType;
import Nodes.*;

import java.io.FileWriter;
import java.io.IOException;

public class CodeGenVisitor implements Visitor{
    FileWriter outMoonCode;
    public CodeGenVisitor(String path) throws IOException {
        String pathPrefix = path.split("\\.")[0];
        outMoonCode = new FileWriter(pathPrefix+".m", true);
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
        for (Node child : node.getChildren() ) {
            //make all children use this scopes' symbol table
            child.setTable(node.getTable());
            child.accept(this);
        }
    }

    @Override
    public void visit(FunctionHeadNode node) {
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
        var entry = node.getEntry();
        var dimsList = entry.m_dims;
        var type = entry.m_type;
        var name = entry.m_name;
        System.out.println(type);
        System.out.println(dimsList);
        if(type.equals("integer")){
            try {
                outMoonCode.write("% space for variable "+name+"\n");
                outMoonCode.write(String.format("%-7s" ,name) + " res 4\n");

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
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
        for (Node child : node.getChildren() ) {
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
        for (Node child : node.getChildren() ) {
            //make all children use this scopes' symbol table
            child.setTable(node.getTable());
            child.accept(this);
        }
        try {
            outMoonCode.close();
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
        if(node.getChildren().size() >= 2 && ((Token)node.getChildren().get(1).getConcept()).getType() == TokenType.ASSIGN){
            var assignName = ((Token)node.getChildren().get(0).getConcept()).getLexeme();
//            var expression =
//           outMoonCode.write("% processing: "  + assignName + " := " + p_node.getChildren().get(1).m_moonVarName + "\n");
//            m_moonExecCode += m_mooncodeindent + "lw " + localRegister + "," + p_node.getChildren().get(1).m_moonVarName + "(r0)\n";
//            m_moonExecCode += m_mooncodeindent + "sw " + p_node.getChildren().get(0).m_moonVarName + "(r0)," + localRegister + "\n";
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
