package Common.Errors;

public class CompilerError {
    private ErrorType errorType;
    private String message;
    private int lineNumber;

    public CompilerError(ErrorType errorType, String message, int lineNumber) {
        this.errorType = errorType;
        this.message = message;
        this.lineNumber = lineNumber;
    }

    public ErrorType getErrorType() {
        return errorType;
    }

    public int getLineNumber() {
        return lineNumber;
    }

    @Override
    public String toString() {
        return errorType+": "+message+" At line: "+lineNumber;
    }
}
