package SemanticAnalysis.Visitor;

import SemanticAnalysis.Actions.*;

public interface Visitor {
    public void visit(SemanticAction action);
    public void visit(ArgumentParamsAction action);
    public void visit(ArithmeticExpressionAction action);
    public void visit(ArraySizeAction action);
    public void visit(ClassDefinitionAction action);
    public void visit(ExpressionAction action);
    public void visit(FactorAction action);
    public void visit(FunctionBodyAction action);
    public void visit(FunctionDefinitionAction action);
    public void visit(FunctionHeadAction action);
    public void visit(FunctionHeadTailAction action);
    public void visit(FunctionParamsAction action);
    public void visit(FunctionParamTailAction action);
    public void visit(IdnestAction action);
    public void visit(IfStatementAction action);
    public void visit(IndiceAction action);
    public void visit(InheritanceAction action);
    public void visit(LocalVariableAction action);
    public void visit(MemberDeclarationAction action);
    public void visit(MemberFunctionDeclarationAction action);
    public void visit(MemberVariableDeclarationAction action);
    public void visit(ProgramAction action);
    public void visit(ReadAction action);
    public void visit(RecursiveArithmeticExpressionAction action);
    public void visit(RecursiveTermAction action);
    public void visit(RelExpressionAction action);
    public void visit(RepeatMemberDeclarationAction action);
    public void visit(ReturnAction action);
    public void visit(StartAction action);
    public void visit(StatementAction action);
    public void visit(StatementIdnestAction action);
    public void visit(TermAction action);
    public void visit(VariableAction action);
    public void visit(VariableIdnestAction action);
    public void visit(WhileLoopAction action);
    public void visit(WriteAction action);
}
