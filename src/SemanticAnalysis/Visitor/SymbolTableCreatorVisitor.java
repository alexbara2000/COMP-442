package SemanticAnalysis.Visitor;

import Nodes.*;
import SemanticAnalysis.Table.SymbolTable;

public class SymbolTableCreatorVisitor implements Visitor{
    @Override
    public void visit(ArgumentParamsNode node) {

    }

    @Override
    public void visit(ArithmeticExpressionNode node) {

    }

    @Override
    public void visit(ArraySizeNode node) {

    }

    @Override
    public void visit(ClassDefinitionNode node) {

    }

    @Override
    public void visit(ExpressionNode node) {

    }

    @Override
    public void visit(FactorNode node) {

    }

    @Override
    public void visit(FunctionBodyNode node) {

    }

    @Override
    public void visit(FunctionDefinitionNode node) {

    }

    @Override
    public void visit(FunctionHeadNode node) {

    }

    @Override
    public void visit(FunctionHeadTailNode node) {

    }

    @Override
    public void visit(FunctionParamsNode node) {

    }

    @Override
    public void visit(FunctionParamsTailNode node) {

    }

    @Override
    public void visit(IdnestNode node) {

    }

    @Override
    public void visit(IfStatementNode node) {

    }

    @Override
    public void visit(IndiceNode node) {

    }

    @Override
    public void visit(InheritanceNode node) {

    }

    @Override
    public void visit(LocalVariableNode node) {

    }

    @Override
    public void visit(MemberDeclarationNode node) {

    }

    @Override
    public void visit(MemberFunctionDeclarationNode node) {

    }

    @Override
    public void visit(MemberVariableDeclarationNode node) {

    }

    @Override
    public void visit(Node p_node) {

    }

    @Override
    public void visit(ProgramNode node) {
        SymbolTable sm = new SymbolTable(0, "global", null);
        System.out.println(sm);
    }

    @Override
    public void visit(ReadNode node) {

    }

    @Override
    public void visit(RecursiveArithmeticExpressionNode node) {

    }

    @Override
    public void visit(RecursiveTermNode node) {

    }

    @Override
    public void visit(RelExpressionNode node) {

    }

    @Override
    public void visit(RecursiveMemberDeclarationNode node) {

    }

    @Override
    public void visit(ReturnNode node) {

    }

    @Override
    public void visit(StartNode node) {
        SymbolTable sm = new SymbolTable(0, "global", null);
        System.out.println(sm);
    }

    @Override
    public void visit(StatementNode node) {

    }

    @Override
    public void visit(StatementIdnestNode node) {

    }

    @Override
    public void visit(TermNode node) {

    }

    @Override
    public void visit(VariableNode node) {

    }

    @Override
    public void visit(VariableIdnestNode node) {

    }

    @Override
    public void visit(WhileLoopNode node) {

    }

    @Override
    public void visit(WriteNode node) {

    }
}
