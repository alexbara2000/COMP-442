package SemanticAnalysis.Visitor;

import Nodes.*;

public abstract interface Visitor {
    public abstract void visit(ArgumentParamsNode node);
    public abstract void visit(ArithmeticExpressionNode node);
    public abstract void visit(ArraySizeNode node);
    public abstract void visit(ClassDefinitionNode node);
    public abstract void visit(ExpressionNode node);
    public abstract void visit(FactorNode node);
    public abstract void visit(FunctionBodyNode node);
    public abstract void visit(FunctionDefinitionNode node);
    public abstract void visit(FunctionHeadNode node);
    public abstract void visit(FunctionHeadTailNode node);
    public abstract void visit(FunctionParamsNode node);
    public abstract void visit(FunctionParamsTailNode node);
    public abstract void visit(IdnestNode node);
    public abstract void visit(IfStatementNode node);
    public abstract void visit(IndiceNode node);
    public abstract void visit(InheritanceNode node);
    public abstract void visit(LocalVariableNode node);
    public abstract void visit(MemberDeclarationNode node);
    public abstract void visit(MemberFunctionDeclarationNode node);
    public abstract void visit(MemberVariableDeclarationNode node);
    public abstract void visit(Node p_node);
    public abstract void visit(ProgramNode node);
    public abstract void visit(ReadNode node);
    public abstract void visit(RecursiveArithmeticExpressionNode node);
    public abstract void visit(RecursiveTermNode node);
    public abstract void visit(RelExpressionNode node);
    public abstract void visit(RecursiveMemberDeclarationNode node);
    public abstract void visit(ReturnNode node);
    public abstract void visit(StartNode node);
    public abstract void visit(StatementNode node);
    public abstract void visit(StatementIdnestNode node);
    public abstract void visit(TermNode node);
    public abstract void visit(VariableNode node);
    public abstract void visit(VariableIdnestNode node);
    public abstract void visit(WhileLoopNode node);
    public abstract void visit(WriteNode node);
}
